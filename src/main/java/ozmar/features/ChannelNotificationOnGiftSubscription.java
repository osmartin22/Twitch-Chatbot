package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.GiftSubscriptionsEvent;
import ozmar.enums.SubPlan;

public class ChannelNotificationOnGiftSubscription {
    @EventSubscriber
    public void onGiftSubscription(GiftSubscriptionsEvent event) {

        SubPlan plan = SubPlan.getValue(event.getSubscriptionPlan());

        String message;

        if (event.getCount() > 1) {
            message = String.format(
                    "%s, so nice of you to donate %s %s subs moon2CUTE",
                    event.getUser().getName(),
                    event.getCount(),
                    plan.getSubPlanName()
            );

        } else {
            message = String.format(
                    "%s, so nice of you to donate %s %s sub moon2CUTE",
                    event.getUser().getName(),
                    event.getCount(),
                    plan.getSubPlanName()
            );
        }

        System.out.println("OnGiftSub: " + message);
        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
