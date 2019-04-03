package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import twitch4j_packages.common.events.user.PrivateMessageEvent;

public class OnPrivateMessageReceived {

    @EventSubscriber
    public void onPrivateMessage(PrivateMessageEvent event) {
        System.out.println("HELLO: " + event.toString());
//        System.out.println();
//        event.getTwitchChat().sendUserMessage(event.getUser().getName(),"namedauto");
    }
}
