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
public class ChannelChangeGameEvent extends TwitchEvent {

    /**
     * Channel
     */
    private final EventChannel channel;

    /**
     * GameId
     */
    private final Long gameId;

    /**
     * Event Constructor
     *
     * @param channel The channel that went live
     * @param gameId  The gameId
     */
    public ChannelChangeGameEvent(EventChannel channel, Long gameId) {
        this.channel = channel;
        this.gameId = gameId;
    }
}
