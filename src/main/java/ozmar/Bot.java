package ozmar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import ozmar.commands.CommandList;
import ozmar.database.DatabaseHelper;
import ozmar.features.OnCommandReceived;
import ozmar.features.WriteChannelChatToConsole;
import ozmar.helix.HelixCommands;

import java.io.InputStream;


public class Bot {

    private Configuration configuration;
    private TwitchClient twitchClient;

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
        for (String command : CommandList.getCommandTriggerList()) {
            clientBuilder = clientBuilder.withCommandTrigger(command);
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

    private void connectDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.open();
        databaseHelper.initializeDb();
//        databaseHelper.close();
    }

    public void start() {
        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel.toLowerCase());
            //twitchClient.getChat().sendMessage(channel.toLowerCase(), "Bot Joined");
        }

        HelixCommands.setTwitchClient(twitchClient);
    }
}
