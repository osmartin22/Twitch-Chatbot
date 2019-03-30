package twitch4j_packages.chat.events.roomstate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * Subscribers Only State Event
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class SubscribersOnlyEvent extends ChannelStatesEvent {

    /**
     * Constructor
     *
     * @param channel ChatChannel
     * @param active  State active?
     */
    public SubscribersOnlyEvent(EventChannel channel, boolean active) {
        super(channel, active);
    }
}
