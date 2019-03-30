package twitch4j_packages.common.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.TwitchEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event gets called when a channel goes live
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ChannelGoLiveEvent extends TwitchEvent {

    /**
     * Channel
     */
    private final EventChannel channel;

    /**
     * Title
     */
    private final String title;

    /**
     * GameId
     */
    private final Long gameId;

    /**
     * Event Constructor
     *
     * @param channel The channel that went live
     * @param title   The stream title
     * @param gameId  The gameId
     */
    public ChannelGoLiveEvent(EventChannel channel, String title, Long gameId) {
        this.channel = channel;
        this.title = title;
        this.gameId = gameId;
    }
}
