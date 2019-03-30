package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.chat.events.channel.CheerEvent;

@Slf4j
public class ChannelNotificationOnCheer {
    @EventSubscriber
    public void onCheer(CheerEvent event) {
        String message = String.format(
                "%s, thanks for the %s bits HACKERMANS moon2CUTE",
                event.getUser().getName(),
                event.getBits()
        );

        log.info("OnCheer: {}", message);
        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
