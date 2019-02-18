package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.commands.interfaces.HandleCommandInterface;

public class OnCommandReceived {

    private HandleCommandInterface handleCommand;
    private static long lastCommand = 0;


    public OnCommandReceived(HandleCommandInterface handleCommand) {
        this.handleCommand = handleCommand;
    }

    @EventSubscriber
    public void onCommand(CommandEvent event) {

        // TODO: Temp solution to prevent chat spamming messages

        long currTime = System.currentTimeMillis();
        if (currTime - lastCommand >= 3000) {
            lastCommand = currTime;

            handleCommand.setCommandEvent(event);
            String output = handleCommand.decideCommand();

            if (output != null) {
                event.respondToUser(output);
            }
            System.out.println(event);
        } else {
            System.out.println("NOT ALLOWING COMMAND: " + event.getCommandPrefix());
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
