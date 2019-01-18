package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;
import ozmar.Utils.TimeHelper;
import ozmar.helix.HelixCommands;

import javax.annotation.Nonnull;
import java.util.*;

public class CommandList {

    // TODO: Redo the commands and place them in a better place with permissions for each command
    private static List<String> commandTriggerList = new ArrayList<>(
            Arrays.asList("!dice", "!hello", "!count", "!uptime")
    );

    private static int count = 0;

    private CommandList() {

    }

    public static List<String> getCommandTriggerList() {
        return commandTriggerList;
    }


    // TODO: CHECK FOR PERMISSIONS

    /**
     * Receives command to process and calls the method associated with it
     * returns a String that will be output to chat if it is not empty
     *
     * @param event User info and command data
     * @return String
     */
    public static String decideCommand(CommandEvent event) {
        String preCommand = event.getCommandPrefix();
        String result = "";

        if (preCommand.equals(commandTriggerList.get(0))) {
            result = diceRollCommand(event);

        } else if (preCommand.equals(commandTriggerList.get(1))) {
            result = helloCommand(event);

        } else if (preCommand.equals(commandTriggerList.get(2))) {
            ++count;
            System.out.println(count);

        } else if (preCommand.equals(commandTriggerList.get(3))) {
            result = uptimeCommand(event);
        }

        return result;
    }


    /**
     * Returns a random number from user input or uses a predefined random number if none is chosen
     * If an invalid command is passed, an empty string is returned
     *
     * @param event User info and command data
     * @return String
     */
    private static String diceRollCommand(@Nonnull CommandEvent event) {
        String output = "You rolled a ";
        int num = 20;

        String command = event.getCommand();
        if (command.isEmpty()) {
            return output + getRandomNumber(num);
        }

        char temp = command.charAt(0);
        if (Character.isDigit(temp) || temp == ' ') {

            command = event.getCommand().trim();

            if (command.contains(" ")) {
                command = command.substring(0, command.indexOf(" "));
            }

            try {
                num = Integer.parseInt(command);

            } catch (NumberFormatException e) {
                // variable already has a predefined value that is used when this error occurs
            }

            return output + getRandomNumber(num);
        }

        return "";
    }


    /**
     * Returns a random number from 0 to given number (inclusive)
     * Allows for negative random numbers
     *
     * @param num range of random number (inclusive)
     * @return int
     */
    private static int getRandomNumber(int num) {
        Random random = new Random();
        int roll;

        if (num < 0) {
            num *= -1;
            roll = random.nextInt(num - 1) * -1;

        } else {
            roll = random.nextInt(num + 1);
        }

        return roll;
    }


    /**
     * Returns message greeting the user
     * Returns empty string if invalid command
     *
     * @param event User info and command data
     * @return String
     */
    private static String helloCommand(@Nonnull CommandEvent event) {
        String command = event.getCommand();

        if (command.isEmpty() || command.charAt(0) == ' ') {
            return "Hello there " + event.getUser().getName();
        }

        return "";
    }


    /**
     * Gets the uptime of a stream and returns it in DD:HH:MM:SS format
     *
     * @param event User info and command data
     * @return String
     */
    private static String uptimeCommand(@Nonnull CommandEvent event) {
        String channelName = event.getSourceId();

        UserList userList = HelixCommands.getUsersList(null, null, Arrays.asList(channelName));
        String channelId = userList.getUsers().get(0).getId();
        StreamList streamList = HelixCommands.getStreams(null, null, null, null,
                null, null, Arrays.asList(channelId), null);

        String output;
        if (!streamList.getStreams().isEmpty()) {
            System.out.println(streamList);
            Calendar startTime = streamList.getStreams().get(0).getStartedAt();
            Calendar currentTime = Calendar.getInstance();

            output = channelName + " has been live for " + TimeHelper.getTimeDiff(startTime, currentTime);
        } else {
            output = channelName + " is not streaming right now";
        }

        return output;
    }
}
