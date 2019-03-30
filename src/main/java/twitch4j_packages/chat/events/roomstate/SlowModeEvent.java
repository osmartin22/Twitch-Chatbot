package twitch4j_packages.chat.events.roomstate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * Slow Mode State Event
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class SlowModeEvent extends ChannelStatesEvent {
    /**
     * time in seconds
     */
    private final long time;

    /**
     * Constructor
     *
     * @param channel ChatChannel
     * @param time    seconds
     */
    public SlowModeEvent(EventChannel channel, long time) {
        super(channel, time > 0);
        this.time = time;
    }
}
