package ozmar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import ozmar.features.ChannelNotificationOnGiftSubscription;
import ozmar.features.ChannelNotificationOnSubscription;

import java.io.InputStream;


public class Bot {

    private Configuration configuration;
    private TwitchClient twitchClient;

    public Bot() {
        loadConfiguration();

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();


        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                configuration.getCredentials().get("irc")
        );

        clientBuilder = clientBuilder
                .withChatAccount(credential)
                .withEnableChat(true);

        clientBuilder = clientBuilder
                .withClientId(configuration.getApi().get("twitch_client_id"))
                .withClientSecret(configuration.getApi().get("twitch_client_secret"))
                .withEnableHelix(true)
                .withEnableGraphQL(true)
                .withEnableKraken(true);


        twitchClient = clientBuilder.build();
        twitchClient.getEventManager().registerListener(this);
    }


    public void registerFeatures() {
//        twitchClient.getEventManager().registerListener(new WriteChannelChatToConsole());
//        twitchClient.getEventManager().registerListener(new ChannelNotificationOnFollow());
        twitchClient.getEventManager().registerListener(new ChannelNotificationOnSubscription());
        twitchClient.getEventManager().registerListener(new ChannelNotificationOnGiftSubscription());
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

    public void start() {
        for(String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel);
        }
    }
}
