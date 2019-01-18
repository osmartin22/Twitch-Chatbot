package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.commands.CommandList;

public class OnCommandReceived {
    @EventSubscriber
    public void onCommand(CommandEvent event) {

        String output = CommandList.decideCommand(event);

        if(!output.isEmpty()) {
            event.respondToUser(output);
        }

        System.out.println("GOT A COMMAND");
        System.out.println(event);
    }
}
