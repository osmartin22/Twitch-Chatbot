package ozmar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import ozmar.database.DatabaseHandler;
import ozmar.features.OnCommandReceived;
import ozmar.features.WriteChannelChatToConsole;
import ozmar.helix.HelixCommands;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class Bot {

    public static DatabaseHandler databaseHelper;


    public static boolean currentWordCountMap = true;   // true = wordCountMap1 false = wordCountMap2
    public static Map<String, Integer> wordCountMap1 = new HashMap<>();
    public static Map<String, Integer> wordCountMap2 = new HashMap<>();


    private Configuration configuration;
    private TwitchClient twitchClient;
    private WordCountTimer wordCountTimer;

    public Bot() {
        connectDatabase();
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
                .withEnableHelix(true)
                .withEnableGraphQL(true)
                .withEnableKraken(true);

        // Add commands to clientBuilder
        for (Command command : databaseHelper.getCommands()) {
            clientBuilder = clientBuilder.withCommandTrigger(command.getCommand());
        }

        twitchClient = clientBuilder.build();
        twitchClient.getEventManager().registerListener(this);
    }


    public void registerFeatures() {
        twitchClient.getEventManager().registerListener(new WriteChannelChatToConsole());
        twitchClient.getEventManager().registerListener(new OnCommandReceived());
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

    private boolean connectDatabase() {
        databaseHelper = new DatabaseHandler();
        boolean dbOpened = databaseHelper.open();
        databaseHelper.initializeDb();
//        databaseHelper.close();
        return dbOpened;
    }

    private void startTimer() {
        wordCountTimer = new WordCountTimer();
        wordCountTimer.startTimer();
    }

    public void start() {
        startTimer();

        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel.toLowerCase());
            //twitchClient.getChat().sendMessage(channel.toLowerCase(), "Bot Joined");
        }

        HelixCommands.setTwitchClient(twitchClient);
    }
}
