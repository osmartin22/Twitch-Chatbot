package twitch4jTests.twitch4j;

import com.github.philippheuer.events4j.EventManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import twitch4j_packages.common.events.user.PrivateMessageEvent;
import twitch4j_packages.twitch4j.TwitchClient;
import twitch4j_packages.twitch4j.TwitchClientBuilder;

@Slf4j
@Tag("unittest")
public class TwitchClientTest {

    /**
     * Twitch Client Test
     */
    @Test
    @DisplayName("Tests the TwitchClientBuilder")
    public void buildTwitch4J() {
        // external event manager (for shared module usage - streamlabs4j)
        EventManager eventManager = new EventManager();

        // construct twitchClient
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEventManager(eventManager)
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withEnableChat(false)
                .build();
    }

    /**
     * Debugging
     */
    @Test
    @DisplayName("Test for local execution in error diagnostics")
    @Disabled
    public void localTest() {
        // external event manager (for shared module usage - streamlabs4j)
        EventManager eventManager = new EventManager();

        // construct twitchClient
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEventManager(eventManager)
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withEnableTMI(true)
                .withEnableChat(false)
                .withEnablePubSub(true)
                .withEnableGraphQL(true)
                .build();

        // register all event listeners
        eventManager.onEvent(PrivateMessageEvent.class).subscribe(event -> {
            System.out.println("[Whisper] " + event.getUser().getName() + ": " + event.getMessage());
        });
    }

}
