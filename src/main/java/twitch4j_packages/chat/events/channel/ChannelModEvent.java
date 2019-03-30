package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a client gains/loses mod status.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class ChannelModEvent extends AbstractChannelEvent {

    /**
     * User
     */
    private EventUser user;

    /**
     * Is Moderator?
     */
    private boolean isMod;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     * @param user    The user that gained/lost mod status.
     * @param isMod   Did the use gain or lose mod status?
     */
    public ChannelModEvent(EventChannel channel, EventUser user, boolean isMod) {
        super(channel);
        this.user = user;
        this.isMod = isMod;
    }
}
