package twitch4j_packages.chat.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event is a base for events that originate from a channel
 */
@Data
@Getter
@EqualsAndHashCode(callSuper = false)
public class AbstractChannelEvent extends TwitchEvent {

    /**
     * Event Channel
     */
    private final EventChannel channel;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     */
    public AbstractChannelEvent(EventChannel channel) {
        super();
        this.channel = channel;
    }
}
