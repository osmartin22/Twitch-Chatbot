package twitch4j_packages.chat.events.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.enums.SubscriptionPlan;
import twitch4j_packages.chat.events.AbstractChannelEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;

import java.util.Optional;

/**
 * This event gets called when a user gets a new subscriber or a user resubscribes.
 * <p>
 * This event will be called simultaneously with the chat announcement,
 * not when the user presses his subscription button.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class SubscriptionEvent extends AbstractChannelEvent {

    /**
     * Event Target User
     */
    private EventUser user;

    /**
     * Subscription Plan
     */
    private String subscriptionPlan;

    /**
     * Subscription Message
     */
    private Optional<String> message;

    /**
     * X Months subscription streak
     */
    private Integer months;

    /**
     * Was this sub gifted?
     */
    private Boolean gifted;

    /**
     * User that gifted the sub
     */
    private EventUser giftedBy;

    /**
     * Event Constructor
     *
     * @param channel  ChatChannel the user subscribed to
     * @param user     User that subscribed
     * @param subPlan  Sub Plan
     * @param message  Sub Message
     * @param months   Months
     * @param gifted   Is gifted?
     * @param giftedBy User that gifted the sub
     */
    public SubscriptionEvent(EventChannel channel, EventUser user, String subPlan, Optional<String> message, Integer months, Boolean gifted, EventUser giftedBy) {
        super(channel);
        this.user = user;
        this.subscriptionPlan = subPlan;
        this.message = message;
        this.months = months;
        this.gifted = gifted;
        this.giftedBy = giftedBy;
    }

    /**
     * Gets the Subscription Plan
     *
     * @return SubscriptionPlan
     */
    public SubscriptionPlan getSubscriptionPlanName() {
        return SubscriptionPlan.fromString(this.subscriptionPlan);
    }

}
