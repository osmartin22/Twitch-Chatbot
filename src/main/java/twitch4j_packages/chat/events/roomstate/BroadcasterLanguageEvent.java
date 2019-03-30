package twitch4j_packages.chat.events.roomstate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.common.events.domain.EventChannel;

import java.util.Locale;

/**
 * Broadcaster Language Event
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class BroadcasterLanguageEvent extends ChannelStatesEvent {

    /**
     * Language
     */
    private final Locale language;

    /**
     * Constructor
     *
     * @param channel  ChatChannel
     * @param language Locale
     */
    public BroadcasterLanguageEvent(EventChannel channel, Locale language) {
        super(channel, language != null);
        this.language = language;
    }
}
