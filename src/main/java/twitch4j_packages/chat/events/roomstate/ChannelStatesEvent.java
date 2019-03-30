package twitch4j_packages.chat.events.roomstate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * Abstract Channel State Event
 */
@Data
@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class ChannelStatesEvent extends AbstractChannelEvent {

    private final boolean active;

    public ChannelStatesEvent(EventChannel channel, boolean active) {
        super(channel);
        this.active = active;
    }
}
