package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;

/**
 * This event gets called when the user starts hosting someone.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class HostOnEvent extends AbstractChannelEvent {

    /**
     * Event Target ChatChannel
     */
    private EventChannel targetChannel;

    /**
     * Event Constructor
     *
     * @param channel       The channel that this event originates from.
     * @param targetChannel The channel that was hosted.
     */
    public HostOnEvent(EventChannel channel, EventChannel targetChannel) {
        super(channel);
        this.targetChannel = targetChannel;
    }

}
