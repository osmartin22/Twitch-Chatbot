package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.commands.HandleCommand;

public class OnCommandReceived {

    private HandleCommand handleCommand;
    private static long lastCommand = 0;


    public OnCommandReceived(HandleCommand handleCommand) {
        this.handleCommand = handleCommand;
    }

    @EventSubscriber
    public void onCommand(CommandEvent event) {

        // TODO: Temp solution to prevent chat spamming messages
        long currTime = System.currentTimeMillis();
        if (currTime - lastCommand >= 2000) {
            lastCommand = currTime;

            handleCommand.setCommandEvent(event);
            String output = handleCommand.decideCommand();

            if (!output.isEmpty()) {
                event.respondToUser(output);
            }
            System.out.println(event);
        } else {
            System.out.println("NOT ALLOWING COMMAND");
        }


//        handleCommand.setCommandEvent(event);
//        String output = handleCommand.decideCommand();
//
//        if (!output.isEmpty()) {
//            event.respondToUser(output);
//        }
//        System.out.println(event);
    }

}
