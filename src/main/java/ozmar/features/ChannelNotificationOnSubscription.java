package ozmar.features;


import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.SubscriptionEvent;
import ozmar.enums.SubPlan;

public class ChannelNotificationOnSubscription {
    @EventSubscriber
    public void onSubscription(SubscriptionEvent event) {
        String message = "";

        if(event.getGifted()) {
            message = String.format(
              "%s has been gifted a sub by %s",
              event.getUser().getName(),
              event.getGiftedBy().getName()
            );
        }

        else {
            if (event.getMonths() <= 1) {
                message = String.format(
                        "%s has subscribed to %s!",
                        event.getUser().getName(),
                        event.getChannel().getName()
                );
            }

            if (event.getMonths() > 1) {
                message = String.format(
                        "%s has subscribed to %s in their %s month!",
                        event.getUser().getName(),
                        event.getChannel().getName(),
                        event.getMonths()
                );
            }
        }

        SubPlan plan = SubPlan.getValue(event.getSubscriptionPlan());
        String message2 = message + " SubPlan = " + plan.getSubPlanName();

        System.out.println(event);
        System.out.println(message2);

//        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
