package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;

public class OnCommandReceived {
    @EventSubscriber
    public void onCommand(CommandEvent event) {
     System.out.println("GOT A COMMAND");
     System.out.println(event);
    }
}
