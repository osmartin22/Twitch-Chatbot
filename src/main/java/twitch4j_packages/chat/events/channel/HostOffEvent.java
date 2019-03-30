package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.TwitchEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event gets called when the user stops hosting someone.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class HostOffEvent extends TwitchEvent {

    /**
     * Event ChatChannel
     */
    private EventChannel channel;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     */
    public HostOffEvent(EventChannel channel) {
        this.channel = channel;
    }

}
