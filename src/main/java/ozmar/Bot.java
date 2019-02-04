package ozmar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.TwitchHelix;
import com.github.twitch4j.helix.TwitchHelixBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ozmar.database.DatabaseHandler;
import ozmar.features.OnCommandReceived;
import ozmar.features.WriteChannelChatToConsole;
import ozmar.helix.HelixCommands;
import ozmar.timers.ChatListTimer;
import ozmar.timers.WordCountTimer;

import java.io.InputStream;


public class Bot {
    public static HelixCommands helixCommands;

    private Configuration configuration;
    private TwitchClient twitchClient;
    private WordCountTimer wordCountTimer;
    private ChatListTimer chatListTimer;

    @Autowired
    private OnCommandReceived onCommandReceived;

    public Bot() {
        initialize();
    }

    public Bot(OnCommandReceived onCommandReceived) {
        this.onCommandReceived = onCommandReceived;
        initialize();
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
                .withEnableGraphQL(true)
                .withEnableKraken(true);

        // Add commands to clientBuilder
        final DatabaseHandler db = new DatabaseHandler();
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
        twitchClient.getEventManager().registerListener(new WriteChannelChatToConsole());
        twitchClient.getEventManager().registerListener(onCommandReceived);
//        twitchClient.getEventManager().registerListener(new ChannelNotificationOnFollow());
//        twitchClient.getEventManager().registerListener(new ChannelNotificationOnSubscription());
//        twitchClient.getEventManager().registerListener(new ChannelNotificationOnGiftSubscription());
//        twitchClient.getEventManager().registerListener(new ChannelNotificationOnDonation());
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

    private void startTimer() {
        wordCountTimer = new WordCountTimer();
        wordCountTimer.startTimer();

        chatListTimer = new ChatListTimer();
        chatListTimer.startTimer();
    }

    public void start() {
//        startTimer();

        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel.toLowerCase());
            //twitchClient.getChat().sendMessage(channel.toLowerCase(), "Bot Joined");
        }
    }
}
