package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.commands.HandleCommand;

public class OnCommandReceived {

    private HandleCommand handleCommand;


    public OnCommandReceived(HandleCommand handleCommand) {
        this.handleCommand = handleCommand;
    }

    @EventSubscriber
    public void onCommand(CommandEvent event) {

        handleCommand.setCommandEvent(event);
        String output = handleCommand.decideCommand();

        if (!output.isEmpty()) {
            event.respondToUser(output);
        }
        System.out.println(event);
    }

}
