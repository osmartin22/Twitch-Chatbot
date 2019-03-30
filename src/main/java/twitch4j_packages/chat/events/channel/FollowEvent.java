package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a user gets a new followers
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class FollowEvent extends AbstractChannelEvent {

    /**
     * User
     */
    private EventUser user;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     * @param user    The user who triggered the event.
     */
    public FollowEvent(EventChannel channel, EventUser user) {
        super(channel);
        this.user = user;
    }
}
