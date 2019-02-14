package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.GiftSubscriptionsEvent;
import ozmar.enums.SubPlan;

public class ChannelNotificationOnGiftSubscription {
    @EventSubscriber
    public void onGiftSubscription(GiftSubscriptionsEvent event) {

        SubPlan plan = SubPlan.getValue(event.getSubscriptionPlan());
        String message = String.format(
                "%s has donated %s %s sub(s)",
                event.getUser(),
                event.getCount(),
                plan.getSubPlanName()
        );

        System.out.println("OnGiftSub: " + message);
//        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
