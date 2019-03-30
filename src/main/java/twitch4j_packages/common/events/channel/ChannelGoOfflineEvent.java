package twitch4j_packages.common.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.TwitchEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event gets called when a channel goes offline
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ChannelGoOfflineEvent extends TwitchEvent {

    /**
     * Channel
     */
    private final EventChannel channel;

    /**
     * Event Constructor
     *
     * @param channel The channel that went offline
     */
    public ChannelGoOfflineEvent(EventChannel channel) {
        this.channel = channel;
    }
}
