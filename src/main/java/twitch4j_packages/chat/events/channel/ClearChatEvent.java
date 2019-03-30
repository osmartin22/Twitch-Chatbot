package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;

@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ClearChatEvent extends AbstractChannelEvent {

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     */
    public ClearChatEvent(EventChannel channel) {
        super(channel);
    }
}
