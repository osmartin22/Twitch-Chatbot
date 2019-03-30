package twitch4jTests.tmi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import twitch4j_packages.tmi.TwitchMessagingInterface;
import twitch4j_packages.tmi.TwitchMessagingInterfaceBuilder;
import twitch4j_packages.tmi.domain.Chatters;

@Slf4j
@Tag("integration")
public class TMIServiceTest {

    /**
     * Gets a instance of the Client
     *
     * @return TwitchMessagingInterface
     */
    public static TwitchMessagingInterface getClient() {
        return TwitchMessagingInterfaceBuilder.builder().build();
    }

    /**
     * Get Chatters
     */
    @Test
    @DisplayName("Get all viewers of channel")
    public void getViewers() {
        // TestCase
        Chatters chatters = getClient().getChatters("lirik").execute();
    }

}
