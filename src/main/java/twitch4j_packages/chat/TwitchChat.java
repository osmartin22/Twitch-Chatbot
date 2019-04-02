package twitch4j_packages.chat;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.EventManager;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import twitch4j_packages.chat.enums.CommandSource;
import twitch4j_packages.chat.events.CommandEvent;
import twitch4j_packages.chat.events.IRCEventHandler;
import twitch4j_packages.chat.events.channel.ChannelMessageEvent;
import twitch4j_packages.chat.events.channel.IRCMessageEvent;
import twitch4j_packages.pubsub.enums.TMIConnectionState;

import java.time.Duration;
import java.util.*;

@Slf4j
public class TwitchChat {

    /**
     * EventManager
     */
    @Getter
    private final EventManager eventManager;

    /**
     * CredentialManager
     */
    @Getter
    private final CredentialManager credentialManager;

    /**
     * OAuth2Credential, used to sign in to twitch chat
     */
    @Getter(AccessLevel.NONE)
    private Optional<OAuth2Credential> chatCredential = Optional.empty();

    /**
     * The WebSocket Server
     */
    private final String webSocketServer = "wss://irc-ws.chat.twitch.tv:443";

    /**
     * WebSocket Client
     */
    @Setter(AccessLevel.NONE)
    private WebSocket webSocket;

    /**
     * The connection state
     * Default: ({@link TMIConnectionState#DISCONNECTED})
     */
    private TMIConnectionState connectionState = TMIConnectionState.DISCONNECTED;

    /**
     * Enable Channel Cache
     */
    private Boolean enableChannelCache = false;

    /**
     * Channel Cache
     */
    protected final Map<String, Boolean> channelCache = new HashMap<>();

    /**
     * IRC Message Bucket
     */
    protected final Bucket ircMessageBucket;

    /**
     * IRC Command Queue
     */
    public final CircularFifoQueue<String> ircCommandQueue = new CircularFifoQueue<>(200);

    /**
     * IRC Command Queue Thread
     */
    protected final Thread queueThread;

    /**
     * IRC Command Handlers
     */
    protected final List<String> commandPrefixes;

    /**
     * Constructor
     *
     * @param eventManager       EventManager
     * @param credentialManager  CredentialManager
     * @param chatCredential     Chat Credential
     * @param enableChannelCache Enable channel cache?
     * @param commandPrefixes    Command Prefixes
     */
    public TwitchChat(EventManager eventManager, CredentialManager credentialManager, OAuth2Credential chatCredential, Boolean enableChannelCache, List<String> commandPrefixes) {
        this.eventManager = eventManager;
        this.credentialManager = credentialManager;
        this.chatCredential = Optional.ofNullable(chatCredential);
        this.enableChannelCache = enableChannelCache;
        this.commandPrefixes = commandPrefixes;

        // credential validation
        if (this.chatCredential.isPresent() == false) {
            log.error("TwitchChat: No ChatAccount provided, Chat will not be available! Please look at the docs Twitch4J -> Chat");
        } else if (this.chatCredential.get().getUserName() == null) {
            log.info("TwitchChat: AccessToken does not contain any user information, fetching using the CredentialManager ...");

            // credential manager
            Optional<OAuth2Credential> credential = credentialManager.getOAuth2IdentityProviderByName("twitch").get().getAdditionalCredentialInformation(this.chatCredential.get());
            if (credential.isPresent()) {
                this.chatCredential = credential;
            } else {
                log.error("TwitchChat: Failed to get AccessToken Information, the token is probably not valid. Please check the docs Twitch4J -> Chat on how to obtain a valid token.");
            }
        }

        // register with serviceMediator
        this.eventManager.getServiceMediator().addService("twitch4j-chat", this);

        // register event listeners
        IRCEventHandler ircEventHandler = new IRCEventHandler(this);

        // initialize rate-limiting
        this.ircMessageBucket = Bucket4j.builder()
                .addLimit(Bandwidth.simple(20, Duration.ofSeconds(30)))
                .build();

        // connect to irc
        this.connect();

        // queue command worker
        this.queueThread = new Thread(() -> {
            while (true) {
                try {
                    // If connected, consume 1 token
                    if (ircCommandQueue.size() > 0) {
                        if (connectionState.equals(TMIConnectionState.CONNECTED)) {
                            // block thread, until we can continue
                            ircMessageBucket.asScheduler().consume(1);

                            // pop one command from the queue and execute it
                            String command = ircCommandQueue.remove();
                            sendCommand(command);

                            // Logging
                            log.debug("Processed command from queue: [{}].", command.startsWith("PASS") ? "***OAUTH TOKEN HIDDEN***" : command);
                            log.debug("{} messages left before hitting the rate-limit!", ircMessageBucket.getAvailableTokens());
                        }
                    }

                    // sleep one second
                    Thread.sleep(250);
                } catch (Exception ex) {
                    log.error("Failed to process message from command queue: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        queueThread.start();
        log.debug("Started IRC Queue Worker");

        // Event Handlers
        log.debug("Registering the following command triggers: " + commandPrefixes.toString());
        eventManager.onEvent(ChannelMessageEvent.class).subscribe(event -> onChannelMessage(event));
    }

    /**
     * Connecting to IRC-WS
     */
    @Synchronized
    public void connect() {
        if (connectionState.equals(TMIConnectionState.DISCONNECTED) || connectionState.equals(TMIConnectionState.RECONNECTING)) {
            try {
                // Change Connection State
                connectionState = TMIConnectionState.CONNECTING;

                // Recreate Socket if state does not equal CREATED
                createWebSocket();

                // Connect to IRC WebSocket
                this.webSocket.connect();
            } catch (Exception ex) {
                log.error("Connection to Twitch IRC failed: {} - Retrying ...", ex.getMessage());

                // Sleep 1 second before trying to reconnect
                try {
                    Thread.sleep(1000);
                } catch (Exception et) {

                }

                // reconnect
                reconnect();
            }
        }
    }

    /**
     * Disconnecting from IRC-WS
     */
    @Synchronized
    public void disconnect() {
        if (connectionState.equals(TMIConnectionState.CONNECTED)) {
            connectionState = TMIConnectionState.DISCONNECTING;
            sendCommand("QUIT"); // safe disconnect
        }

        connectionState = TMIConnectionState.DISCONNECTED;

        // CleanUp
        this.webSocket.clearListeners();
        this.webSocket.disconnect();
        this.webSocket = null;
    }

    /**
     * Reconnecting to IRC-WS
     */
    @Synchronized
    public void reconnect() {
        connectionState = TMIConnectionState.RECONNECTING;
        disconnect();
        connect();
    }

    /**
     * Recreate the WebSocket and the listeners
     */
    private void createWebSocket() {
        try {
            // WebSocket
            this.webSocket = new WebSocketFactory().createSocket(webSocketServer);

            // WebSocket Listeners
            this.webSocket.clearListeners();
            this.webSocket.addListener(new WebSocketAdapter() {

                @Override
                public void onConnected(WebSocket ws, Map<String, List<String>> headers) {
                    log.info("Connecting to Twitch IRC {}", webSocketServer);

                    // if credentials is null, it will automatically disconnect
                    if (!chatCredential.isPresent()) {
                        log.error("Can't find credentials for the chat account!");
                        disconnect();
                        return; // do not continue script
                    }

                    // acquire capabilities
                    sendCommand("CAP REQ :twitch.tv/tags twitch.tv/commands twitch.tv/membership");
                    sendCommand("CAP END");

                    // sign in
                    sendCommand(String.format("pass oauth:%s", chatCredential.get().getAccessToken()));
                    sendCommand(String.format("nick %s", chatCredential.get().getUserName()));

                    // Join defined channels, in case we reconnect or weren't connected yet when we called joinChannel
                    if (!channelCache.isEmpty()) {
                        for (String channel : channelCache.keySet()) {
                            sendCommand("join #" + channel);
                        }
                    }

                    // then join to own channel - required for sending or receiving whispers
                    if (chatCredential.get().getUserName() != null) {
                        sendCommand("join #" + chatCredential.get().getUserName());
                    } else {
                        log.warn("Chat: The whispers feature is currently not available because the provided credential does not hold information about the user. Please check the documentation on how to pass the token to the credentialManager where it will be enriched with the required information.");
                    }

                    // Connection Success
                    connectionState = TMIConnectionState.CONNECTED;
                }

                @Override
                public void onTextMessage(WebSocket ws, String text) {
                    Arrays.asList(text.replace("\n\r", "\n")
                            .replace("\r", "\n").split("\n"))
                            .forEach(message -> {
                                if (!message.equals("")) {
                                    // Handle messages
                                    log.trace("Received WebSocketMessage: " + message);
                                    // - CAP
                                    if (message.contains(":req Invalid CAP command")) {
                                        log.error("Failed to acquire requested IRC capabilities!");
                                    } else if (message.contains(":tmi.twitch.tv CAP * ACK :")) {
                                        List<String> capabilities = Arrays.asList(message.replace(":tmi.twitch.tv CAP * ACK :", "").split(" "));
                                        capabilities.forEach(cap -> log.debug("Acquired chat capability: " + cap));
                                    }
                                    // - Ping
                                    else if (message.contains("PING :tmi.twitch.tv")) {
                                        sendCommand("PONG :tmi.twitch.tv");
                                        log.debug("Responding to PING request!");
                                    }
                                    // - Login failed.
                                    else if (message.equals(":tmi.twitch.tv NOTICE * :Login authentication failed")) {
                                        log.error("Invalid IRC Credentials. Login failed!");
                                    }
                                    // - Parse IRC Message
                                    else {
                                        try {
                                            IRCMessageEvent event = new IRCMessageEvent(message);

                                            if (event.isValid()) {
                                                eventManager.dispatchEvent(event);
                                            } else {
                                                log.trace("Can't parse {}", event.getRawMessage());
                                            }
                                        } catch (Exception ex) {
                                            log.error(ex.getMessage(), ex);
                                        }
                                    }
                                }
                            });
                }

                @Override
                public void onDisconnected(WebSocket websocket,
                                           WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame,
                                           boolean closedByServer) {
                    if (!connectionState.equals(TMIConnectionState.DISCONNECTING)) {
                        log.info("Connection to Twitch IRC lost (WebSocket)! Retrying ...");

                        // connection lost - reconnecting
                        reconnect();
                    } else {
                        connectionState = TMIConnectionState.DISCONNECTED;
                        log.info("Disconnected from Twitch IRC (WebSocket)!");
                    }
                }

            });


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Send IRC Command
     *
     * @param command IRC Command
     * @param args    command arguments
     */
    protected void sendCommand(String command, String... args) {
        ircCommandQueue.add(String.format("%s %s", command.toUpperCase(), String.join(" ", args)));
    }

    /**
     * Send IRC Command
     *
     * @param command IRC Command
     */
    private void sendCommand(String command) {
        // will send command if connection has been established
        if (connectionState.equals(TMIConnectionState.CONNECTED) || connectionState.equals(TMIConnectionState.CONNECTING)) {
            // command will be uppercase.
            this.webSocket.sendText(command);
        } else {
            log.warn("Can't send IRC-WS Command [{}]", command);
        }
    }

    /**
     * Joining the channel
     *
     * @param channelName channel name
     */
    public void joinChannel(String channelName) {
        if (!channelCache.containsKey(channelName.toLowerCase())) {
            sendCommand("join", "#" + channelName.toLowerCase());
            log.debug("Joining Channel [{}].", channelName.toLowerCase());
            channelCache.put(channelName.toLowerCase(), true);
        } else {
            log.warn("Already joined channel {}", channelName);
        }
    }

    /**
     * leaving the channel
     *
     * @param channelName channel name
     */
    public void leaveChannel(String channelName) {
        if (channelCache.containsKey(channelName.toLowerCase())) {
            sendCommand("part", "#" + channelName.toLowerCase());
            log.debug("Leaving Channel [{}].", channelName.toLowerCase());
            channelCache.remove(channelName.toLowerCase());
        } else {
            log.warn("Already left channel {}", channelName);
        }
    }

    /**
     * Sending message to the joined channel
     *
     * @param channel channel name
     * @param message message
     */
    public void sendMessage(String channel, String message) {
        log.debug("Adding message for channel [{}] with content [{}] to the queue.", channel, message);
        ircCommandQueue.add(String.format("PRIVMSG #%s :%s", channel, message));
    }

    /**
     * Sends a user a private message
     *
     * @param targetUser username
     * @param message    message
     */
    public void sendPrivateMessage(String targetUser, String message) {
        log.debug("Adding private message for user [{}] with content [{}] to the queue.", targetUser, message);
        ircCommandQueue.add(String.format("PRIVMSG #%s :/w %s %s", chatCredential.get().getUserName(), targetUser, message));
    }

    /**
     * On Channel Message
     *
     * @param event ChannelMessageEvent
     */
    private void onChannelMessage(ChannelMessageEvent event) {
        Optional<String> prefix = Optional.empty();
        Optional<String> commandWithoutPrefix = Optional.empty();

//        Command is "!command"
//        This command will respond to !command or !cOmmANd but not to !commandUselessInfo
        String prefixTemp = event.getMessage();
        prefixTemp = prefixTemp.replaceAll("\u206D", "");   // Remove character from Chatterino

        if (prefixTemp.contains(" ")) {
            prefixTemp = prefixTemp.substring(0, prefixTemp.indexOf(" "));
        }
        prefixTemp = prefixTemp.toLowerCase();

        for (String commandPrefix : this.commandPrefixes) {
            if (commandPrefix.equals(prefixTemp)) {
                prefix = Optional.ofNullable(commandPrefix);
                commandWithoutPrefix = Optional.ofNullable(event.getMessage().substring(prefixTemp.length()));
            }
        }

        // ORIGINAL
        // try to find a `command` based on the prefix
        /*
//        Command is "!command"
//        This command will respond to !command or !commandUselessInfo but not to !COMMAND
//        The second one can cause overlap for other bots that use similar commands
        for (String commandPrefix : this.commandPrefixes) {
            if (event.getMessage().startsWith(commandPrefix)) {
                prefix = Optional.ofNullable(commandPrefix);
                commandWithoutPrefix = Optional.ofNullable(event.getMessage().substring(commandPrefix.length()));
            }
        }
        */

        // is command?
        if (commandWithoutPrefix.isPresent()) {
            log.debug("Detected a command in channel {} with content: {}", event.getChannel().getName(), commandWithoutPrefix.get());

            // dispatch command event
            CommandEvent commandEvent = new CommandEvent(CommandSource.CHANNEL, event.getChannel().getName(), event.getUser(), prefix.get(), commandWithoutPrefix.get(), event.getPermissions());
            eventManager.dispatchEvent(commandEvent);
        }
    }

}
