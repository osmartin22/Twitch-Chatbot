package twitch4j_packages.chat.events.roomstate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * R9K State Event
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class Robot9000Event extends ChannelStatesEvent {

    /**
     * Constructor
     *
     * @param channel ChatChannel
     * @param active  State active?
     */
    public Robot9000Event(EventChannel channel, boolean active) {
        super(channel, active);
    }
}
