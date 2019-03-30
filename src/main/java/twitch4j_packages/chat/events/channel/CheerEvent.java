package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

/**
 * This event gets called when a user receives bits.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class CheerEvent extends AbstractChannelEvent {

    /**
     * Event Target User
     */
    private EventUser user;

    /**
     * Message
     */
    private String message;

    /**
     * Amount of Bits
     */
    private Integer bits;

    /**
     * Event Constructor
     *
     * @param channel The channel that this event originates from.
     * @param user    The donating user.
     * @param message The donation message.
     * @param bits    The amount of bits.
     */
    public CheerEvent(EventChannel channel, EventUser user, String message, Integer bits) {
        super(channel);
        this.user = user;
        this.message = message;
        this.bits = bits;
    }
}
