package twitch4jTests.pubsub.pubsub;

import com.github.philippheuer.events4j.EventManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import twitch4jTests.pubsub.util.TestUtils;
import twitch4j_packages.pubsub.TwitchPubSub;
import twitch4j_packages.pubsub.TwitchPubSubBuilder;

@Slf4j
@Tag("integration")
public class TwitchPubSubTest {

    private static TwitchPubSub twitchPubSub;

    @BeforeAll
    public static void connectToChat() {
        // external event manager
        EventManager eventManager = new EventManager();

        // construct twitchChat
        twitchPubSub = TwitchPubSubBuilder.builder()
                .withEventManager(eventManager)
                .build();

        // sleep for a few seconds so that we're connected
        TestUtils.sleepFor(5000);
    }

    @Test
    @DisplayName("Local test to keep it running for debugging")
    @Disabled
    public void localTestRun() {
        // listen for events in channel
        twitchPubSub.listenForCheerEvents(TestUtils.getCredential(), 149223493l);
        twitchPubSub.listenForCommerceEvents(TestUtils.getCredential(), 149223493l);
        twitchPubSub.listenForSubscriptionEvents(TestUtils.getCredential(), 149223493l);
        twitchPubSub.listenForWhisperEvents(TestUtils.getCredential(), 149223493l);

        // sleep a second and look of the message was sended
        TestUtils.sleepFor(60000);
    }

}
