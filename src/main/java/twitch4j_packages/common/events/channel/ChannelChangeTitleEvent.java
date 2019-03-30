package twitch4j_packages.common.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.TwitchEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event gets called when a channel changes the game
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ChannelChangeTitleEvent extends TwitchEvent {

    /**
     * Channel
     */
    private final EventChannel channel;

    /**
     * Title
     */
    private final String title;

    /**
     * Event Constructor
     *
     * @param channel The channel that went live
     * @param title   The stream title
     */
    public ChannelChangeTitleEvent(EventChannel channel, String title) {
        this.channel = channel;
        this.title = title;
    }
}
