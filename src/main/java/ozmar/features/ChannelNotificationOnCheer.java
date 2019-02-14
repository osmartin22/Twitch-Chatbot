package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.CheerEvent;

public class ChannelNotificationOnCheer {
    @EventSubscriber
    public void onCheer(CheerEvent event) {
        String message = String.format(
                "%s has cheered %s bit(s)\n%s",
                event.getUser().getName(),
                event.getBits(),
                event.getMessage()
        );

        System.out.println("OnCheer: " + message);
//        event.getTwitchChat().sendMessage(event.getChannel().getName(), message);
    }
}
