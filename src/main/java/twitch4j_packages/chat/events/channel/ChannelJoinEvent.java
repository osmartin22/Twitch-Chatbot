package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a client joins a channel.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ChannelJoinEvent extends AbstractChannelEvent {

    /**
     * User
     */
    private EventUser user;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     * @param user    The user triggering the event.
     */
    public ChannelJoinEvent(EventChannel channel, EventUser user) {
        super(channel);
        this.user = user;
    }
}
