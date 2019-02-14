package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.DonationEvent;

public class ChannelNotificationOnDonation {
    @EventSubscriber
    public void onDonation(DonationEvent event) {
        String message = String.format(
                "%s just donated %s using %s!",
                event.getUser().getName(),
                event.getAmount(),
                event.getSource()
        );

        System.out.println("OnDonation: " + message);
//        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
