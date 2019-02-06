package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;
import ozmar.Bot;
import ozmar.Command;
import ozmar.database.DatabaseHandler;
import ozmar.enums.CommandNumPermission;
import ozmar.utils.RandomHelper;
import ozmar.utils.TimeHelper;
import poke_models.pokemon.Pokemon;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;


// TODO: IMPLEMENT A QUEUE TO PREVENT USERS SPAMMING THE SAME COMMAND MULTIPLE TIMES
public class CommandList {

    private CommandEvent commandEvent;
    private DatabaseHandler db;
    private Calculator calculator;


    public CommandList(DatabaseHandler db, Calculator calculator) {
        this.calculator = calculator;
        this.db = db;
    }

    public void setCommandEvent(CommandEvent commandEvent) {
        this.commandEvent = commandEvent;
    }

    /**
     * Receives command to process and calls the method associated with the command
     *
     * @return String
     */
    public String decideCommand() {
        String preCommand = commandEvent.getCommandPrefix();
        CommandNumPermission userPermission = CommandNumPermission.convertToNumPermission(commandEvent.getPermissions());

        String result = "";
        List<Command> commandsList = db.getCommands();

        if (preCommand.equals(commandsList.get(0).getCommand()) &&
                hasPermission(commandsList.get(0).getPermission(), userPermission)) {
            result = diceRollCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(1).getCommand()) &&
                hasPermission(commandsList.get(1).getPermission(), userPermission)) {
            result = helloCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(2).getCommand()) &&
                hasPermission(commandsList.get(2).getPermission(), userPermission)) {
//            countCommand();

        } else if (preCommand.equals(commandsList.get(3).getCommand()) &&
                hasPermission(commandsList.get(3).getPermission(), userPermission)) {
            result = uptimeCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(4).getCommand()) &&
                hasPermission(commandsList.get(4).getPermission(), userPermission)) {
            result = calcCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(5).getCommand()) &&
                hasPermission(commandsList.get(5).getPermission(), userPermission)) {
            result = followageCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(6).getCommand()) &&
                hasPermission(commandsList.get(6).getPermission(), userPermission)) {
            result = wordCountCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(7).getCommand()) &&
                hasPermission(commandsList.get(7).getPermission(), userPermission)) {
            clearWordCountCommand();
            System.out.println("Cleared wordCountTable");

        } else if (preCommand.equals(commandsList.get(8).getCommand()) &&
                hasPermission(commandsList.get(8).getPermission(), userPermission)) {
            result = "31's next release is on ...";

        } else if (preCommand.equals(commandsList.get(9).getCommand()) &&
                hasPermission(commandsList.get(9).getPermission(), userPermission)) {
            result = messageCountCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(10).getCommand()) &&
                hasPermission(commandsList.get(10).getPermission(), userPermission)) {
            result = pointsCommand(commandEvent);

        } else if (preCommand.equals(commandsList.get(11).getCommand()) &&
                hasPermission(commandsList.get(10).getPermission(), userPermission)) {

            result = catchPokeCommand(commandEvent);
        }

        System.out.println(result);
        return result;
    }

    /**
     * Checks if the user has permission to use the command
     *
     * @param commandLevel permisison level of the command
     * @param userLevel    permission level of user
     * @return boolean
     */
    private boolean hasPermission(CommandNumPermission commandLevel, CommandNumPermission userLevel) {
        return userLevel.getCommandLevel() >= commandLevel.getCommandLevel();
    }

    /**
     * Returns a random number from user input or uses a predefined random number if none is chosen
     * If an invalid command is passed, an empty string is returned
     *
     * @param event User info and command data
     * @return String
     */
    private String diceRollCommand(@Nonnull CommandEvent event) {
        String output = event.getUser().getName() + " rolled a ";
        int num = 20;

        String command = event.getCommand();
        if (command.isEmpty()) {
            return output + diceRollHelperD20();
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
                return output + diceRollHelperD20();
            }

            return output + RandomHelper.getRandomNumber(num);
        }

        return "";
    }

    /**
     * Return output depending on user roll, should only be used for 20 sided dice roll
     *
     * @return String
     */
    private String diceRollHelperD20() {
        int randNum = RandomHelper.getRandomNumber(20);
        if (randNum == 20) {
            return "20 POGPLANT";

        } else if (randNum == 1) {
            return "1 LUL";
        } else {
            return String.valueOf(randNum);
        }
    }

    /**
     * Returns message greeting the user
     * Returns empty string if invalid command
     *
     * @param event User info and command data
     * @return String
     */
    private String helloCommand(@Nonnull CommandEvent event) {
        String command = event.getCommand();

        if (command.isEmpty() || command.charAt(0) == ' ') {
            return "Hey there " + event.getUser().getName() + " peepoWink";
        }

        return "";
    }

    private void countCommand() {

    }

    /**
     * Gets the uptime of a stream and returns it in DD:HH:MM:SS format
     *
     * @param event User info and command data
     * @return String
     */
    private String uptimeCommand(@Nonnull CommandEvent event) {
        String channelName = event.getSourceId();
        UserList userList = Bot.helixCommands.getUsersList(null, Collections.singletonList(channelName));

        String channelId = userList.getUsers().get(0).getId();
        StreamList streamList = Bot.helixCommands.getStreams(null, null, null, null,
                null, null, Collections.singletonList(channelId), null);

        String output;
        if (!streamList.getStreams().isEmpty()) {
            System.out.println(streamList);
            Calendar startTime = streamList.getStreams().get(0).getStartedAt();
            Calendar currentTime = Calendar.getInstance();

            output = channelName + " has been live for " + TimeHelper.getTimeDiff(startTime, currentTime);
        } else {
            output = channelName + " is offline";
        }

        return output;
    }

    /**
     * Calculates user operation input
     * returns empty string if input is invalid
     *
     * @param event User info and command data
     * @return String
     */
    private String calcCommand(@Nonnull CommandEvent event) {
        calculator.setOperation(event.getCommand());
        Double result;
        try {
            result = calculator.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        } finally {
            calculator.resetCalc();
        }

        return String.valueOf(result);
    }

    /**
     * Gets follow length between user and channel message was sent from
     * Optional username can be entered to get the follow length between
     * the optional username and current channel.
     * <p>
     * returns empty string if username was not found
     *
     * @param event USer info and command data
     * @return String
     */
    private String followageCommand(@Nonnull CommandEvent event) {
        String followedChannel = event.getSourceId();
        boolean emptyCommand = event.getCommand().isEmpty();

        List<String> userChannelNameList = new ArrayList<>();
        userChannelNameList.add(followedChannel);
        if (!emptyCommand) {
            userChannelNameList.add(event.getCommand().trim());
        }

        UserList userList = Bot.helixCommands.getUsersList(null, userChannelNameList);

        String userFollowingId;
        if (emptyCommand) {
            userFollowingId = event.getUser().getId().toString();

            // Check that user input username was found
        } else if (userList.getUsers().size() == 2) {
            userFollowingId = userList.getUsers().get(1).getId();

            // User input username was not found
        } else {
            return "";
        }

        String followedChannelId = userList.getUsers().get(0).getId();
        FollowList followList = Bot.helixCommands.getFollowers(userFollowingId, followedChannelId, null, 1);
        String userFollowingName = (emptyCommand) ? event.getUser().getName() :
                userList.getUsers().get(1).getDisplayName();

        String output;
        if (!followList.getFollows().isEmpty()) {
            output = userFollowingName + " has been following since "
                    + followList.getFollows().get(0).getFollowedAt();
        } else {
            output = userFollowingName + " is not following " + followedChannel;
        }

        return output;
    }

    /**
     * Gets the top 10 words in the table
     * or the specified count for the given word
     *
     * @return String
     */
    private String wordCountCommand(@Nonnull CommandEvent event) {
        String word = event.getCommand().trim();
        String result;
        if (word.isEmpty()) {
            result = "The top 10 words used so far are " + turnMapToString(db.getTop10Words());
        } else {
            int count = db.getSpecificWordCount(word);
            if (count == -1) {
                result = "0";
            } else {
                result = "The count for " + word + " is " + count;
            }
        }

        return result;
    }

    /**
     * Converts a map into a string
     *
     * @param map map to convert to string
     * @return String
     */
    private String turnMapToString(@Nonnull Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .map(e -> e.getKey() + " " + e.getValue())
                .collect(Collectors.joining(", "));
    }


    /**
     * Clears the table containing the count of words used in the database
     */
    private void clearWordCountCommand() {
        db.clearWordCount();
    }

    private String messageCountCommand(@Nonnull CommandEvent event) {
        int count;
        String message = event.getCommand().trim().toLowerCase();
        String result;

        if (message.isEmpty()) {
            count = db.getMessageCount(event.getUser().getId()) + 1;
            result = event.getUser().getName();
        } else {
            count = db.getMessageCount(message);
            result = message;
        }

        return (count != -1) ? result + " has sent " + count + " messages" : "";
    }

    private String pointsCommand(@Nonnull CommandEvent event) {
        int points;
        String message = event.getCommand().trim().toLowerCase();
        String result;

        if (message.isEmpty()) {
            points = db.getPoints(event.getUser().getId());
            result = event.getUser().getName();
        } else {
            points = db.getPoints(message);
            result = message;
        }

        return (points != -1) ? result + " has " + points + " points" : "";
    }

    private String catchPokeCommand(@Nonnull CommandEvent event) {
        int id = RandomHelper.getRandomNumberNotZero(807);
        Pokemon pokemon = Pokemon.getById(id);

        return event.getUser().getName() + " caught a " + pokemon.getName();
    }
}
