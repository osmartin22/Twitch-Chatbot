package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.chat.events.channel.DonationEvent;

@Slf4j
public class ChannelNotificationOnDonation {
    @EventSubscriber
    public void onDonation(DonationEvent event) {
        String message = String.format(
                "%s, thanks for the money HACKERMANS moon2CUTE",
                event.getUser().getName()
        );

        log.info("OnDonation: {}", message);
        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
