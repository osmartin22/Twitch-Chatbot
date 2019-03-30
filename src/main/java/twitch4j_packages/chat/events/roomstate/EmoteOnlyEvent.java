package twitch4j_packages.chat.events.roomstate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * Emote Only State Event
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class EmoteOnlyEvent extends ChannelStatesEvent {

    /**
     * Constructor
     *
     * @param channel ChatChannel
     * @param active  State active?
     */
    public EmoteOnlyEvent(EventChannel channel, boolean active) {
        super(channel, active);
    }
}
