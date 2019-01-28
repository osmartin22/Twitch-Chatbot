package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.commands.CommandList;

public class OnCommandReceived {
    @EventSubscriber
    public void onCommand(CommandEvent event) {
        CommandList commandList = new CommandList(event);
        String output = commandList.decideCommand();

        if (!output.isEmpty()) {
            event.respondToUser(output);
        }

        System.out.println(event);
    }
}
