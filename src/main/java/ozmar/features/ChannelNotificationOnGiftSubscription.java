package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import ozmar.enums.SubPlan;
import twitch4j_packages.chat.events.channel.GiftSubscriptionsEvent;

@Slf4j
public class ChannelNotificationOnGiftSubscription {
    @EventSubscriber
    public void onGiftSubscription(GiftSubscriptionsEvent event) {

        SubPlan plan = SubPlan.getValue(event.getSubscriptionPlan());

        String message;

        if (event.getCount() > 1) {
            message = String.format(
                    "%s, so nice of you to gift %s %s subs moon2CUTE",
                    event.getUser().getName(),
                    event.getCount(),
                    plan.getSubPlanName()
            );

        } else {
            message = String.format(
                    "%s, so nice of you to gift %s %s sub moon2CUTE",
                    event.getUser().getName(),
                    event.getCount(),
                    plan.getSubPlanName()
            );
        }

        log.info("OnGiftSub: {}", message);
        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
