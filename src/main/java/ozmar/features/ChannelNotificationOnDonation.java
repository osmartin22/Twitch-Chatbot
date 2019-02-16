package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.DonationEvent;

public class ChannelNotificationOnDonation {
    @EventSubscriber
    public void onDonation(DonationEvent event) {
        String message = String.format(
                "%s, thanks for the money HACKERMANS moon2CUTE",
                event.getUser().getName()
        );

        System.out.println("OnDonation: " + message);
        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
