package ozmar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.TwitchHelix;
import com.github.twitch4j.helix.TwitchHelixBuilder;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.features.*;
import ozmar.helix.HelixCommands;

import java.io.InputStream;


public class Bot {
    public static HelixCommands helixCommands;

    private Configuration configuration;
    private TwitchClient twitchClient;

    private final DatabaseHandlerInterface db;

    private final ChannelNotificationOnCheer channelNotificationOnCheer;
    private final ChannelNotificationOnDonation channelNotificationOnDonation;
    private final ChannelNotificationOnFollow channelNotificationOnFollow;
    private final ChannelNotificationOnGiftSubscription channelNotificationOnGiftSubscription;
    private final ChannelNotificationOnSubscription channelNotificationOnSubscription;
    private final OnChatChannelMessage onChatChannelMessage;
    private final OnCommandReceived onCommandReceived;

    public Bot(DatabaseHandlerInterface db, ChannelNotificationOnCheer channelNotificationOnCheer,
               ChannelNotificationOnDonation channelNotificationOnDonation,
               ChannelNotificationOnFollow channelNotificationOnFollow,
               ChannelNotificationOnGiftSubscription channelNotificationOnGiftSubscription,
               ChannelNotificationOnSubscription channelNotificationOnSubscription,
               OnChatChannelMessage onChatChannelMessage, OnCommandReceived onCommandReceived) {
        this.db = db;
        this.channelNotificationOnCheer = channelNotificationOnCheer;
        this.channelNotificationOnDonation = channelNotificationOnDonation;
        this.channelNotificationOnFollow = channelNotificationOnFollow;
        this.channelNotificationOnGiftSubscription = channelNotificationOnGiftSubscription;
        this.channelNotificationOnSubscription = channelNotificationOnSubscription;
        this.onChatChannelMessage = onChatChannelMessage;
        this.onCommandReceived = onCommandReceived;
    }

    public void initialize() {
        loadConfiguration();

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                configuration.getCredentials().get("irc")
        );

        clientBuilder = clientBuilder
                .withChatAccount(credential)
                .withEnableChat(true)
                .withClientId(configuration.getApi().get("twitch_client_id"))
                .withClientSecret(configuration.getApi().get("twitch_client_secret"))
                .withEnablePubSub(true)
                .withEnableGraphQL(true)
                .withEnableKraken(true);

        // Add commands to clientBuilder
        for (Command command : db.getCommands()) {
            clientBuilder = clientBuilder.withCommandTrigger(command.getCommand());
        }

        twitchClient = clientBuilder.build();
        twitchClient.getEventManager().registerListener(this);

        TwitchHelix twitchHelixClient = TwitchHelixBuilder.builder()
                .withClientId(configuration.getApi().get("twitch_client_id"))
                .withClientSecret(configuration.getApi().get("twitch_client_secret"))
                .build();

        helixCommands = new HelixCommands(twitchHelixClient, credential.getAccessToken());
    }


    public void registerFeatures() {

//        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class).subscribe(event -> {
//            System.out.println("[" + event.getChannel().getName() + "] " + event.getUser().getName() + ": " + event.getMessage());
//        });

        twitchClient.getEventManager().registerListener(new ChannelChangeTitle());
        twitchClient.getEventManager().registerListener(channelNotificationOnCheer);
        twitchClient.getEventManager().registerListener(channelNotificationOnDonation);
        twitchClient.getEventManager().registerListener(channelNotificationOnFollow);
        twitchClient.getEventManager().registerListener(channelNotificationOnGiftSubscription);
        twitchClient.getEventManager().registerListener(channelNotificationOnSubscription);
        twitchClient.getEventManager().registerListener(onChatChannelMessage);
        twitchClient.getEventManager().registerListener(onCommandReceived);
    }

    private void loadConfiguration() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("config.yaml");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(is, Configuration.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load Configuration ... Exiting.");
            System.exit(1);
        }
    }

    public void start() {
        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel.toLowerCase());
        }
    }
}
