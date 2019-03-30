package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a user gets banned.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class UserBanEvent extends AbstractChannelEvent {

    /**
     * Event Target User
     */
    private EventUser user;

    /**
     * Reason for Punishment
     */
    private String reason;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     * @param user    The user who triggered the event.
     * @param reason  Reason for Ban.
     */
    public UserBanEvent(EventChannel channel, EventUser user, String reason) {
        super(channel);
        this.user = user;
        this.reason = reason;
    }
}
