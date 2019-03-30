package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a user get a timeout.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class UserTimeoutEvent extends AbstractChannelEvent {

    /**
     * Event Target User
     */
    private EventUser user;

    /**
     * Duration in Minutes
     */
    private Integer duration;

    /**
     * Reason for Punishment
     */
    private String reason;

    /**
     * Event Constructor
     *
     * @param channel  The channel that this event originates from.
     * @param user     The user who triggered the event.
     * @param duration Timeout Duration in Minutes.
     * @param reason   Reason for Ban.
     */
    public UserTimeoutEvent(EventChannel channel, EventUser user, Integer duration, String reason) {
        super(channel);
        this.user = user;
        this.duration = duration;
        this.reason = reason;
    }
}
