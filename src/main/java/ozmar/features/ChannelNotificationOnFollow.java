package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.chat.events.channel.FollowEvent;

@Slf4j
public class ChannelNotificationOnFollow {
    @EventSubscriber
    public void onFollow(FollowEvent event) {
        String message = String.format(
                "%s is now following %s!",
                event.getUser().getName(),
                event.getChannel().getName()
        );

        log.info("OnFollow: {}", message);
//        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
