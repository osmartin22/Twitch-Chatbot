package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommandList {

    private static List<String> commandTriggerList = new ArrayList<>(
            Arrays.asList("!dice", "!hello")
    );

    private CommandList() {

    }

    public static List<String> getCommandTriggerList() {
        return commandTriggerList;
    }

    public static String decideCommand(CommandEvent event) {
        String preCommand = event.getCommandPrefix();
        String result = "";

        if(preCommand.equals(commandTriggerList.get(0))) {
            result = diceRollCommand(event);

        } else if(preCommand.equals(commandTriggerList.get(1))) {
            result = helloCommand(event);
        }

        return result;
    }


    /**
     * Returns a random number from user input or uses a predefined random number if none is chosen
     * If an invalid command is passed, an empty string is returned
     *
     * @param event User name and command data
     * @return String
     */
    private static String diceRollCommand(@Nonnull CommandEvent event) {
        String output = "You rolled a ";
        int num = 20;

        String command = event.getCommand();
        if(command.isEmpty()) {
            return output + getRandomNumber(num);
        }

        char temp = command.charAt(0);
        if(Character.isDigit(temp) || temp == ' ') {

            command = event.getCommand().trim();

                if(command.contains(" ")) {
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

        if(num < 0) {
            num *= -1;
            roll = random.nextInt(num - 1) * -1;

        } else {
            roll = random.nextInt(num + 1);
        }

        return roll;
    }

    /**
     *
     * Returns message greeting the user
     * Returns empty string if invalid command
     *
     * @param event User name and command data
     * @return String
     */
    private static String helloCommand(@Nonnull CommandEvent event) {
        String command = event.getCommand();

        if(command.isEmpty() || command.charAt(0) == ' ') {
            return "Hello there " + event.getUser().getName();
        }

        return "";
    }
}
