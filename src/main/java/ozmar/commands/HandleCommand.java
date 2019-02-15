package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;
import ozmar.Bot;
import ozmar.Command;
import ozmar.commands.interfaces.*;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.enums.CommandNumPermission;
import ozmar.timers.RecentChatterTimer;
import ozmar.utils.RandomHelper;
import ozmar.utils.TimeHelper;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;


// TODO: IMPLEMENT A QUEUE TO PREVENT USERS SPAMMING THE SAME COMMAND MULTIPLE TIMES
public class HandleCommand implements HandleCommandInterface {

    private static final String botName = "genbot22";

    private CommandEvent commandEvent;
    private final DatabaseHandlerInterface db;
    private final CalculatorInterface calculator;
    private final DiceInterface dice;
    private final CatchPokeInterface catchPoke;
    private final LootBoxInterface lootBox;
    private List<Command> commandsList;


    public HandleCommand(DatabaseHandlerInterface db, CalculatorInterface calculator, DiceInterface dice,
                         CatchPokeInterface catchPoke, LootBoxInterface lootBox) {
        this.db = db;
        this.calculator = calculator;
        this.dice = dice;
        this.catchPoke = catchPoke;
        this.lootBox = lootBox;
    }

    @Override
    public void setCommandEvent(@Nonnull CommandEvent commandEvent) {
        this.commandEvent = commandEvent;
    }

    /**
     * Receives command to process and calls the method associated with the command
     *
     * @return String
     */
    @Nonnull
    @Override
    public String decideCommand() {
        String preCommand = commandEvent.getCommandPrefix();
        CommandNumPermission userPermission = CommandNumPermission.convertToNumPermission(commandEvent.getPermissions());

        String result = "";
        commandsList = db.getCommands();
        Command command = null;

        if (isCommandHelper(preCommand, 0, -1) && hasPermission(0, userPermission)) {
            command = commandsList.get(0);
            result = diceRollCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 1, -1) && hasPermission(1, userPermission)) {
            command = commandsList.get(1);
//            result = helloCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 2, -1) && hasPermission(2, userPermission)) {
            command = commandsList.get(2);
            result = spitCommand(commandsList.get(2));

        } else if (isCommandHelper(preCommand, 3, -1) && hasPermission(3, userPermission)) {
            command = commandsList.get(3);
            result = uptimeCommand(commandEvent);
            System.out.println(result);
            result = "";

        } else if (isCommandHelper(preCommand, 4, -1) && hasPermission(4, userPermission)) {
            command = commandsList.get(4);
            result = calcCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 5, -1) && hasPermission(5, userPermission)) {
            command = commandsList.get(5);
            result = followageCommand(commandEvent);    // TODO: OFF MAYBE ?
            System.out.println(result);
            result = "";

        } else if (isCommandHelper(preCommand, 6, 7) && hasPermission(6, userPermission)) {
            command = commandsList.get(6);
            if (!commandEvent.getUser().getName().equals("namedauto")) {
                return "";
            }
            result = wordCountCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 8, 9) && hasPermission(8, userPermission)) {
            command = commandsList.get(8);
            clearWordCountCommand();

        } else if (isCommandHelper(preCommand, 10, -1) && hasPermission(10, userPermission)) {
            command = commandsList.get(10);
//            result = "31's next release is on ...";

        } else if (isCommandHelper(preCommand, 11, 12) && hasPermission(11, userPermission)) {
            command = commandsList.get(11);
            result = messageCountCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 13, -1) && hasPermission(13, userPermission)) {
            command = commandsList.get(13);
            result = pointsCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 14, 15) && hasPermission(14, userPermission)) {
            command = commandsList.get(14);
            result = catchPokeCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 16, 17) && hasPermission(11, userPermission)) {
            command = commandsList.get(16);
            result = flipCoinCommand(commandEvent);
            System.out.println(result);
            result = "";

        } else if (isCommandHelper(preCommand, 18, 19) && hasPermission(18, userPermission)) {
            command = commandsList.get(18);
            result = openLootCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 20, 21) && hasPermission(20, userPermission)) {
            command = commandsList.get(20);
            result = secretValentineCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 22, 23) && hasPermission(22, userPermission)) {
            command = commandsList.get(22);
            result = myValentineCommand(commandEvent);

        }

        if (command != null) {
            command.incrementUsage();
            db.updateCommandUsage(command);
        }

        System.out.println(result);
        return result;
    }

    private boolean isCommandHelper(String command, int index1, int index2) {
        boolean first = command.equals(commandsList.get(index1).getCommand());
        boolean second = false;
        if (index2 != -1) {
            second = command.equals(commandsList.get(index2).getCommand());
        }

        return first || second;
    }

    /**
     * Checks if the user has permission to use the command
     *
     * @param commandLevel permission level of the command
     * @param userLevel    permission level of user
     * @return boolean
     */
    private boolean hasPermission(CommandNumPermission commandLevel, CommandNumPermission userLevel) {
        return userLevel.getCommandLevel() >= commandLevel.getCommandLevel();
    }

    private boolean hasPermission(int commandIndex, CommandNumPermission userLevel) {
        CommandNumPermission command = commandsList.get(commandIndex).getPermission();
        return userLevel.getCommandLevel() >= command.getCommandLevel();
    }

    /**
     * Returns a random number from user input or uses a predefined random number if none is chosen
     * If an invalid command is passed, an empty string is returned
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String diceRollCommand(@Nonnull CommandEvent event) {
        String output = event.getUser().getName() + " rolled a ";

        String command = event.getCommand().trim();
        if (command.isEmpty()) {
            return output + diceRollHelperD20();
        }

        String[] commandArray = command.split(" ", 3);

        int sides;
        int dieCount = 1;
        try {
            sides = Integer.parseInt(commandArray[0]);

            if (commandArray.length > 1) {
                dieCount = Integer.parseInt(commandArray[1]);
            }

        } catch (NumberFormatException e) {
            System.out.println("Not a number: " + e.getMessage());
            return "";
        }

        if (sides == 0 || dieCount <= 0) {
            return "";
        }

        System.out.println("Sides: " + sides + " Dies: " + dieCount);
        dice.setSides(sides);

        if (sides == 20 && dieCount == 1) {
            output += diceRollHelperD20();
        } else {
            Integer result = dice.rollNDie(dieCount);
            if (result == null) {
                return "";
            }

            output += result;
        }

        return output;
    }

    /**
     * Return output depending on user roll, should only be used for 20 sided dice roll
     *
     * @return String
     */
    @Nonnull
    private String diceRollHelperD20() {
        int randNum = RandomHelper.getRandNumInRange(1, 20);
        if (randNum == 20) {
            return "20 HYPEROMEGAPOGGERSCRAZY";
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
    @Nonnull
    private String helloCommand(@Nonnull CommandEvent event) {
        String command = event.getCommand();

        if (command.isEmpty() || command.charAt(0) == ' ') {
            return "Hey there " + event.getUser().getName() + " peepoWink";
        }

        return "";
    }

    @Nonnull
    private String spitCommand(@Nonnull Command command) {
        return ":comet: moon2DEV " + (command.getUsage() + 1) + " spit drinkers ";
    }

    /**
     * Gets the uptime of a stream and returns it in DD:HH:MM:SS format
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String uptimeCommand(@Nonnull CommandEvent event) {
        String channelName = event.getSourceId();
        UserList userList = Bot.helixCommands.getUsersList(null, Collections.singletonList(channelName));

        Long channelId = userList.getUsers().get(0).getId();
        StreamList streamList = Bot.helixCommands.getStreams(null, null, null, null,
                null, null, Collections.singletonList(channelId), null);

        String output;
        if (!streamList.getStreams().isEmpty()) {
            System.out.println(streamList.getStreams().get(0).getStartedAt());
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
    @Nonnull
    private String calcCommand(@Nonnull CommandEvent event) {
        calculator.setOperation(event.getCommand());
        Double result;
        try {
            result = calculator.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

        // Round to n decimal places
//        BigDecimal bigDecimal = BigDecimal.valueOf(result).setScale(5, BigDecimal.ROUND_HALF_DOWN);
//        Double l = bigDecimal.doubleValue();
        return (result % 1 == 0) ? String.valueOf(result.intValue()) : String.valueOf(result);
    }

    /**
     * Gets follow length between user and channel message was sent from
     * Optional username can be entered to get the follow length between
     * the optional username and current channel.
     * <p>
     * returns empty string if username was not found
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String followageCommand(@Nonnull CommandEvent event) {
        String followedChannel = event.getSourceId();
        boolean emptyCommand = event.getCommand().isEmpty();

        List<String> userChannelNameList = new ArrayList<>();
        userChannelNameList.add(followedChannel);
        if (!emptyCommand) {
            userChannelNameList.add(event.getCommand().trim());
        }

        UserList userList = Bot.helixCommands.getUsersList(null, userChannelNameList);

        Long userFollowingId;
        if (emptyCommand) {
            userFollowingId = event.getUser().getId();

            // Check that user input username was found
        } else if (userList.getUsers().size() == 2) {
            userFollowingId = userList.getUsers().get(1).getId();

            // User input username was not found
        } else {
            return "";
        }

        Long followedChannelId = userList.getUsers().get(0).getId();
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
    @Nonnull
    private String wordCountCommand(@Nonnull CommandEvent event) {
        String word = event.getCommand().trim();
        String result;
        if (word.isEmpty()) {
            result = "The top 10 words used in my lifetime are " + turnMapToString(db.getTop10Words());
        } else {

            int count = db.getSpecificWordCount(word);
            if (count == -1) {
                count = 0;
            }

            word = bannedWordsFilter(word);
            if (word.isEmpty()) {
                return "";
            }

            result = event.getUser().getName() + ", " + word + " has been used " + count + " times in my lifetime";
        }

        return result;
    }

    private String bannedWordsFilter(String word) {
        String temp = word.toLowerCase();
        if (word.equals("ResidentSleeper")) {
            word = insertSpecial(word);

        } else if (temp.equals("nam ")) {
            word = insertSpecial(word);

        } else if (temp.equals("overwatch")) {
            word = insertSpecial(word);

        } else if (temp.startsWith("nigg")) {
            return "";

        } else if (temp.equals("retard")) {
            word = word + " D: ";
        }

        return word;
    }

    @Nonnull
    private String insertSpecial(@Nonnull String word) {
        String special = "\u00AD\u2063";
        return word.substring(0, 1) + special + word.substring(1);
    }

    /**
     * Converts a map into a string
     *
     * @param map map to convert to string
     * @return String
     */
    @Nonnull
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
        System.out.println("Cleared wordCountTable");
    }

    /**
     * Gets the message count of the user that used the command, or the specified user
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
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

    /**
     * Gets the points of the user that used the command, or the specified user
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
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

    /**
     * Gets a random pokemon with a name, nature, and gender and decides if it was caught or not
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String catchPokeCommand(@Nonnull CommandEvent event) {
        String pokeName = event.getCommand().trim().toLowerCase();

        System.out.println("A" + pokeName + "B");

        if (pokeName.contains(" ")) {

            pokeName = pokeName.substring(0, pokeName.indexOf(" "));
        }

        int initializeResult;
        if (!pokeName.isEmpty()) {
            initializeResult = catchPoke.initialize(pokeName);
        } else {
            // Only 807 pokemon exist currently
            initializeResult = catchPoke.initialize(RandomHelper.getRandNumInRange(1, 807));
        }

        if (initializeResult != -1) {
            String result = catchPoke.attemptCatch();
            if (result.equals("")) {
                return "";
            }
            return event.getUser().getName() + result;
        }

        return "";
    }

    /**
     * Flips a coin and returns the result
     *
     * @param event User id and info
     * @return String
     */
    @Nonnull
    private String flipCoinCommand(@Nonnull CommandEvent event) {
        String output = event.getUser().getName() + " flipped ";
        if (RandomHelper.getRandNumInRange(0, 1) == 1) {
            output += "heads";

        } else {
            output += "tails";
        }

        return output;
    }

    /**
     * Gets a random loot
     *
     * @param event User info
     * @return String
     */
    @Nonnull
    private String openLootCommand(@Nonnull CommandEvent event) {
        String result = lootBox.getLoot();
        if (result.isEmpty()) {
            return "";
        }

        return event.getUser().getName() + result;
    }

    @Nonnull
    private String secretValentineCommand(@Nonnull CommandEvent event) {
        Map<String, Long> map = RecentChatterTimer.mostRecent;

        if (map.size() == 0) {
            return "";
        }

        Object randomName = map.entrySet().toArray()[new Random().nextInt(map.entrySet().toArray().length)];
        String newValentine = randomName.toString().substring(0, randomName.toString().indexOf("="));
        String oldValentine = db.getValentine(event.getUser().getId());
        String output = event.getUser().getName();

        // Don't update database
        if (newValentine.equals(event.getUser().getName())) {
            return output + " got themselves moon2PH";
        }

        if (oldValentine == null) {
            db.updateValentine(event.getUser().getId(), newValentine);
            if (newValentine.equals(botName)) {
                output += ", your valentine is me MrDestructoid moon2CUTE";
            } else {
                output += ", your valentine is " + newValentine + " moon2CUTE";
            }

        } else if (!newValentine.equals(oldValentine)) {
            db.updateValentine(event.getUser().getId(), newValentine);
            if (oldValentine.equals(botName)) {
                output += " how could you leave me for " + newValentine + " moon2PH";
            } else {
                output += " left " + oldValentine + " D: for " + newValentine + " moon2CUTE";
            }

        } else {
            output += " got the same valentine, " + newValentine;
        }

        return output;
    }

    @Nonnull
    private String myValentineCommand(@Nonnull CommandEvent event) {
        String valentine = db.getValentine(event.getUser().getId());
        if (valentine == null) {
            return event.getUser().getName() + ", you do not have a valentine, try !newValentine to get one";
        }

        return event.getUser().getName() + ", your current valentine is " + valentine + " moon2CUTE";
    }
}
