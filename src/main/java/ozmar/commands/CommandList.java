package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;
import ozmar.Bot;
import ozmar.Command;
import ozmar.Utils.RandomHelper;
import ozmar.Utils.TimeHelper;
import ozmar.helix.HelixCommands;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class CommandList {

    private CommandList() {

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

        List<Command> commandsList = Bot.databaseHelper.getCommands();

        if (preCommand.equals(commandsList.get(0).getCommand())) {
//            result = diceRollCommand(event);
            System.out.println(diceRollCommand(event));

        } else if (preCommand.equals(commandsList.get(1).getCommand())) {
//            result = helloCommand(event);
            System.out.println(helloCommand(event));

        } else if (preCommand.equals(commandsList.get(2).getCommand())) {
//            countCommand();
//            System.out.println(count);

        } else if (preCommand.equals(commandsList.get(3).getCommand())) {
//            result = uptimeCommand(event);
            System.out.println(uptimeCommand(event));

        } else if (preCommand.equals(commandsList.get(4).getCommand())) {
//            result = calcCommand(event);
            System.out.println(calcCommand(event));
        } else if (preCommand.equals(commandsList.get(5).getCommand())) {
//            result = followageCommand(event);
            System.out.println(followageCommand(event));
        } else if (preCommand.equals(commandsList.get(6).getCommand())) {
//            result = wordCountCommand();
            System.out.println(wordCountCommand());
        }

        return result;
    }

    private static boolean checkPermissions(Set<CommandPermission> commandPermissions,
                                            Set<CommandPermission> userPermissions) {
        boolean result = false;


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
            return output + RandomHelper.getRandomNumber(num);
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

            return output + RandomHelper.getRandomNumber(num);
        }

        return "";
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

    private static void countCommand() {

    }


    /**
     * Gets the uptime of a stream and returns it in DD:HH:MM:SS format
     *
     * @param event User info and command data
     * @return String
     */
    private static String uptimeCommand(@Nonnull CommandEvent event) {
        String channelName = event.getSourceId();
        UserList userList = HelixCommands.getUsersList(null, null, Collections.singletonList(channelName));

        String channelId = userList.getUsers().get(0).getId();
        StreamList streamList = HelixCommands.getStreams(null, null, null, null,
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
    private static String calcCommand(@Nonnull CommandEvent event) {
        String result = new Calculator(event.getCommand()).compute();
        return (result == null) ? "" : result;
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
    private static String followageCommand(@Nonnull CommandEvent event) {
        String followedChannel = event.getSourceId();
        boolean emptyCommand = event.getCommand().isEmpty();

        List<String> userChannelNameList = new ArrayList<>();
        userChannelNameList.add(followedChannel);
        if (!emptyCommand) {
            userChannelNameList.add(event.getCommand().trim());
        }

        UserList userList = HelixCommands.getUsersList(null, null, userChannelNameList);

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
        FollowList followList = HelixCommands.getFollowers(userFollowingId, followedChannelId, null, 1);
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

    private static String wordCountCommand() {
        Map<String, Integer> wordCountMap = new HashMap<>(10);

        int count = 0;
        for (Map.Entry<String, Integer> entry : Bot.wordCountMap1.entrySet()) {

            // Store first 10 pairs from map
            if (count < 10) {
                wordCountMap.put(entry.getKey(), entry.getValue());
                count++;

            } else {
                Integer min = wordCountMap.values().stream().min(Integer::compare).orElse(null);
                if (min != null && min < entry.getValue()) {

                    // remove first entry with the min value even if other entries have the same value
                    for (Map.Entry<String, Integer> countEntry : wordCountMap.entrySet()) {
                        if (countEntry.getValue().equals(min)) {
                            wordCountMap.remove(countEntry.getKey());
                            break;
                        }
                    }

                    wordCountMap.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return "The top words were " + getMapInString(sortMapByValue(wordCountMap));
    }


    // TODO: MOVE OUTSIDE CLASS
    private static Map<String, Integer> sortMapByValue(@Nonnull Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    private static String getMapInString(@Nonnull Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining(", "));
    }

}
