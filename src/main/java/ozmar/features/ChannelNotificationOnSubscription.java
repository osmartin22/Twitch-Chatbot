package ozmar.features;


import com.github.philippheuer.events4j.annotation.EventSubscriber;
import ozmar.enums.SubPlan;
import twitch4j_packages.chat.events.channel.SubscriptionEvent;

public class ChannelNotificationOnSubscription {

    private long lastMessage = 0;
    private static final long DIFF = 3000;

    @EventSubscriber
    public void onSubscription(SubscriptionEvent event) {
        SubPlan plan = SubPlan.getValue(event.getSubscriptionPlan());
        if (System.currentTimeMillis() - lastMessage > DIFF) {
            lastMessage = System.currentTimeMillis();

            if (plan.equals(SubPlan.TIER3)) {
                String message = String.format(
                        "%s, nice Tier 3 sub moon2GOOD",
                        event.getUser().getName()
                );

                event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
                System.out.print("Sent: ");
            }
            System.out.println("OnSub: " + event.getUser().getName() + " subscribed with a " + plan.getSubPlanName());

        } else {
            System.out.println("OnSub: IGNORED " + event.getUser().getName() + plan.getSubPlanName());
        }

    }
}
