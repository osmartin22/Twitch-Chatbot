package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.commands.interfaces.*;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.enums.CommandNumPermission;
import ozmar.setup.Bot;
import ozmar.utils.RandomHelper;
import ozmar.utils.TimeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    private final RecentChattersInterface recentChatters;
    private List<Command> commandsList;

    ChromeOptions chromeOptions;
    WebDriver driver;


    public HandleCommand(DatabaseHandlerInterface db, CalculatorInterface calculator, DiceInterface dice,
                         CatchPokeInterface catchPoke, LootBoxInterface lootBox, RecentChattersInterface recentChatters) {
        this.db = db;
        this.calculator = calculator;
        this.dice = dice;
        this.catchPoke = catchPoke;
        this.lootBox = lootBox;
        this.recentChatters = recentChatters;

        chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
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
    @Nullable
    @Override
    public String decideCommand() {
        String preCommand = commandEvent.getCommandPrefix();
        CommandNumPermission userPermission = CommandNumPermission.convertToNumPermission(commandEvent.getPermissions());

        String result = "";
        commandsList = db.getCommandsDao().queryCommands();
        Command command = null;

        if (isCommandHelper(preCommand, 0, -1) && hasPermission(0, userPermission)) {
            command = commandsList.get(0);
            result = diceRollCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 1, -1) && hasPermission(1, userPermission)) {
            command = commandsList.get(1);
//            result = helloCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 2, -1) && hasPermission(2, userPermission)) {
            command = commandsList.get(2);
            result = spitCommand(commandEvent, commandsList.get(2));

        } else if (isCommandHelper(preCommand, 3, -1) && hasPermission(3, userPermission)) {
            command = commandsList.get(3);
            result = uptimeCommand(commandEvent);
            System.out.println(result);
            result = null;

        } else if (isCommandHelper(preCommand, 4, -1) && hasPermission(4, userPermission)) {
            command = commandsList.get(4);
            result = calcCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 5, -1) && hasPermission(5, userPermission)) {
            command = commandsList.get(5);
            result = followageCommand(commandEvent);    // TODO: OFF MAYBE ?
            System.out.println(result);
            result = null;

        } else if (isCommandHelper(preCommand, 6, 7) && hasPermission(6, userPermission)) {
            command = commandsList.get(6);
//            if (!commandEvent.getUser().getName().equals("namedauto")) {
//                return null;
//            }
            result = wordCountCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 8, 9) && hasPermission(8, userPermission)) {
            command = commandsList.get(8);
            clearWordCountCommand();

        } else if (isCommandHelper(preCommand, 10, -1) && hasPermission(10, userPermission)) {
            command = commandsList.get(10);
            result = getStock();

        } else if (isCommandHelper(preCommand, 11, 12) && hasPermission(11, userPermission)) {
            command = commandsList.get(11);
//            if (!commandEvent.getUser().getName().equals("namedauto")) {
//                return null;
//            }
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
            result = null;

        } else if (isCommandHelper(preCommand, 18, 19) && hasPermission(18, userPermission)) {
            command = commandsList.get(18);
            result = openLootCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 20, 21) && hasPermission(20, userPermission)) {
            command = commandsList.get(20);
            result = newPartnerCommand(commandEvent);

        } else if (isCommandHelper(preCommand, 22, 23) && hasPermission(22, userPermission)) {
            command = commandsList.get(22);
            result = myPartnerCommand(commandEvent);

        }

        if (command != null) {
            command.incrementUsage();
            db.getCommandsDao().updateCommandUsage(command);
        }

        System.out.println(result);
        return result;
    }

    private boolean isCommandHelper(@Nonnull String command, int index1, int index2) {
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
     * @param commandIndex index of the command to get the permission level
     * @param userLevel    permission level of user
     * @return boolean
     */
    private boolean hasPermission(int commandIndex, @Nonnull CommandNumPermission userLevel) {
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
        String output = String.format("%s rolled a ", event.getUser().getName());

        String command = event.getCommand().trim();
        if (command.isEmpty()) {
            return output + diceRollHelperD20();
        }

        String[] commandArray = command.split(" ", 3);

        int sides = 20;
        int dieCount = 1;
        try {
            sides = Integer.parseInt(commandArray[0]);
            if (commandArray.length > 1) {
                dieCount = Integer.parseInt(commandArray[1]);
            }
        } catch (NumberFormatException e) {
            // only the parse that failed will use the default
            System.out.println("Input was incorrect, defaulting to preset value: " + e.getMessage());
        }

        if (sides == 0 || dieCount <= 0) {
            return String.format("%s, you rolled a 0", event.getUser().getName());
        }

        System.out.println("Sides: " + sides + " Dies: " + dieCount);

        dice.setSides(sides);
        if (sides == 20 && dieCount == 1) {
            output += diceRollHelperD20();
        } else {
            Integer result = dice.rollNDie(dieCount);
            if (result == null) {
                return somethingWentWrong(event.getUser().getName());
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
            return "20 moon2POGGYWOGGY";
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
            return String.format("Hey there %s peepoWink", event.getUser().getName());
        }

        return "";
    }

    /**
     * @param event   User info and command data
     * @param command command
     * @return String
     */
    @Nullable
    private String spitCommand(@Nonnull CommandEvent event, @Nonnull Command command) {
        String randomChatter = getRandomRecentChatter();
        if (randomChatter == null) {
            return null;
        }

        // TODO: Currently people can get themselves
//        if(randomChatter.equals(event.getUser().getName())) {
//            return String.format(":comet: moon2DEV %s made %s drink their spit moon2D %s people have drank spit",
//                    event.getUser().getName(), randomChatter, (command.getUsage() + 1));
//        }

        return String.format("☄️ moon2DEV %s made %s drink their spit moon2D %s people have drank spit",
                event.getUser().getName(), randomChatter, (command.getUsage() + 1));
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
        if (userList == null) {
            return somethingWentWrong(event.getUser().getName());
        }

        Long channelId = userList.getUsers().get(0).getId();
        StreamList streamList = Bot.helixCommands.getStreams(null, null, null, null,
                null, null, Collections.singletonList(channelId), null);
        if (streamList == null) {
            return somethingWentWrong(event.getUser().getName());
        }


        if (!streamList.getStreams().isEmpty()) {
            System.out.println(streamList.getStreams().get(0).getStartedAt());
            Calendar startTime = streamList.getStreams().get(0).getStartedAt();
            Calendar currentTime = Calendar.getInstance();
            return String.format("%s has been live for %s", channelName, TimeHelper.getTimeDiff(startTime, currentTime));
        }
        return String.format("%s is currently offline", channelName);
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
            return somethingWentWrong(event.getUser().getName());
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
     * returns empty string if username was not found
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    // TODO: FIX EXCEPTION ON FAKE NAMES
    private String followageCommand(@Nonnull CommandEvent event) {
        List<String> usersInfoList = new ArrayList<>();
        String channelName = event.getSourceId();
        long channelId = db.getChatDao().getUserId(channelName);
        if (channelId == -1) {
            usersInfoList.add(channelName);
        }

        String name = event.getCommand().trim();
        String userToCheckName;
        if (name.isEmpty()) {
            userToCheckName = event.getUser().getName();
        } else {
            userToCheckName = getFirstWord(name);
        }

        long userToCheckId = (event.getCommand().trim().isEmpty()) ?
                event.getUser().getId() : db.getChatDao().getUserId(userToCheckName);
        if (userToCheckId == -1) {
            usersInfoList.add(userToCheckName);
        }

        UserList userList = (!usersInfoList.isEmpty()) ? Bot.helixCommands.getUsersList(null, usersInfoList) : null;
        if (userList != null && !userList.getUsers().isEmpty()) {
            if (channelId == -1) {
                channelId = userList.getUsers().get(0).getId();
                if (userToCheckId == -1) {
                    userToCheckId = userList.getUsers().get(1).getId();
                }
            } else {
                userToCheckId = userList.getUsers().get(0).getId();
            }
        }

        // Not a real user name
        if (userToCheckId == -1) {
            return String.format("%s, %s does not exist", event.getUser().getName(), userToCheckName);
        }

        FollowList followList = Bot.helixCommands.getFollowers(userToCheckId, channelId, null, 1);
        if (followList == null) {
            return String.format("%s, %s does not exist", event.getUser().getName(), userToCheckName);
        }

        if (!followList.getFollows().isEmpty()) {
            return String.format("%s has been following %s since %s",
                    userToCheckName, channelName, followList.getFollows().get(0).getFollowedAt());
        }

        return String.format("%s is not following %s", userToCheckName, channelName);
    }

    /**
     * Gets the top 10 words in the table
     * or the specified count for the given word
     *
     * @return String
     */
    @Nonnull
    // TODO: Change get top 10 to another command
    private String wordCountCommand(@Nonnull CommandEvent event) {
        String result;
        String word = event.getCommand().trim();

        if (word.isEmpty()) {
            result = String.format("%s, try again using !wordcount <word>", event.getUser().getName());
        } else {
            word = getFirstWord(word);
            int count = db.getWordCountDao().querySpecificWordCount(word);
            if (count == -1) {
                count = 0;
            }

            word = bannedWordsFilter(word);
            if (word == null) {
                return String.format("%s, Can't get me moon2DEV", event.getUser().getName());
            }

            result = String.format("%s, %s has been used %s times in my lifetime",
                    event.getUser().getName(), word, count);
        }

        return result;
    }

    @Nonnull
    private Set<String> bannedWords() {
        Set<String> set = new HashSet<>();
        set.add("residentsleeper");
        set.add("cirSlain");
        set.add("narostaryn");
        set.add("meguface");
        set.add("cmonbruh");
        set.add("\uD83D\uDCDD");    // :memo:
        set.add("nam");
        set.add("seal");
        set.add("tdogwiz");
        set.add("c9");
        set.add("yiff");
        set.add("pikagirl");
        set.add("overwatch");
        set.add("ark");
        set.add("arc");
        set.add("kirby");
        set.add("seppuku");
        set.add("staryn");
        return set;
    }

    @Nullable
    private String bannedWordsFilter(@Nonnull String word) {
        String lowerCase = word.toLowerCase();
        if (bannedWords().contains(lowerCase)) {
            word = insertSpecialChars(word);

        } else if (lowerCase.startsWith("nigg") || lowerCase.startsWith("fag")) {
            return null;

        } else if (lowerCase.equals("retard")) {
            word = word + " D: ";
        }

        return word;
    }

    /**
     * Inserts special characters so that banned words can be posted
     * NOTE: Channels can still have these chars banned as well
     * NOTE: NOT meant to be used to circumvent filters
     *
     * @param word word to modify
     * @return String
     */
    @Nonnull
    private String insertSpecialChars(@Nonnull String word) {
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
        db.getWordCountDao().clearTable();
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

        if (message.isEmpty()) {
            count = db.getChatDao().getMessageCount(event.getUser().getId()) + 1;
            if (count != -1) {
                return String.format("%s, you have sent %s messages", event.getUser().getName(), count);
            }
        } else {
            message = getFirstWord(message);
            count = db.getChatDao().getMessageCount(message);
            if (count != -1) {
                return String.format("%s has sent %s messages ", message, count);
            }

            return String.format("%s, %s does not exist in my database", event.getUser().getName(), message);
        }

        return somethingWentWrong(event.getUser().getName());
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

        if (message.isEmpty()) {
            points = db.getChatDao().getPoints(event.getUser().getId());
            if (points != -1) {
                return String.format("%s, you have %s points", event.getUser().getName(), points);
            }
        } else {
            message = getFirstWord(message);
            points = db.getChatDao().getPoints(message);
            if (points != -1) {
                return String.format("%s has %s points  ", message, points);
            }

            return String.format("%s, %s does not exist in my database", event.getUser().getName(), message);
        }

        return somethingWentWrong(event.getUser().getName());
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

        if (pokeName.length() < 3) { // Shortest Pokemon name is 3 letters long (Mew, Muk)
            pokeName = null;
        } else {
            pokeName = getFirstWord(pokeName);
        }

        int initializeResult;
        if (pokeName != null) {
            initializeResult = catchPoke.initialize(pokeName);
        } else {
            // Only 807 pokemon exist currently
            initializeResult = catchPoke.initialize(RandomHelper.getRandNumInRange(1, 807));
        }

        if (initializeResult != -1) {
            String result = catchPoke.attemptCatch();
            if (result == null) {
                return somethingWentWrong(event.getUser().getName());
            }
            return event.getUser().getName() + result;
        }

        return String.format("%s, that is not a Pokemon, try !catchpoke <optionalName>", event.getUser().getName());
    }

    /**
     * Flips a coin and returns the result
     *
     * @param event User id and info
     * @return String
     */
    @Nonnull
    private String flipCoinCommand(@Nonnull CommandEvent event) {
        if (RandomHelper.getRandNumInRange(0, 1) == 1) {
            return String.format("%s flipped heads", event.getUser().getName());

        }

        return String.format("%s flipped tails", event.getUser().getName());
    }

    /**
     * Gets a random loot
     *
     * @param event User info
     * @return String
     */
    @Nonnull
    // TODO: FIX RETURN
    private String openLootCommand(@Nonnull CommandEvent event) {
        String result = lootBox.getLoot();
        if (result.isEmpty()) {
            return somethingWentWrong(event.getUser().getName());
        }

        return event.getUser().getName() + result;
    }

    // TODO: FIX RETURN
    @Nonnull
    private String newPartnerCommand(@Nonnull CommandEvent event) {
        if (recentChatters.getRecentChatters().size() == 0) {
            return somethingWentWrong(event.getUser().getName());
        }

        String newPartner = getRandomRecentChatter();
        if (newPartner == null) {
            return somethingWentWrong(event.getUser().getName());
        }
        String oldPartner = db.getChatDao().getPartnerById(event.getUser().getId());
        String user = event.getUser().getName();
        String output;

        // Don't update database
        if (newPartner.equals(event.getUser().getName())) {
            return String.format("%s could only get themselves moon2PH", user);
        }

        if (oldPartner == null) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            if (newPartner.equals(botName)) {
                output = String.format("%s, your partner is me MrDestructoid peepoWink", user);
            } else {
                output = String.format("%s, your partner is %s billyReady", user, newPartner);
            }

        } else if (!newPartner.equals(oldPartner)) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            if (oldPartner.equals(botName)) {
                output = String.format("%s how could you leave me for %s moon2A", user, newPartner);
            } else {
                output = String.format("%s left %s D: for %s pepeBASS", user, oldPartner, newPartner);
            }

        } else {
            output = String.format("%s got the same partner, %s moon2N", user, newPartner);
        }

        return output;
    }

    @Nonnull
    private String myPartnerCommand(@Nonnull CommandEvent event) {
        String currPartner = db.getChatDao().getPartnerById(event.getUser().getId());
        if (currPartner == null) {
            return String.format("%s, you do not have a partner, try !newpartner to get one",
                    event.getUser().getName());
        }

        return String.format("%s, your current partner is %s pepeBASS", event.getUser().getName(), currPartner);
    }

    @Nullable
    private String getRandomRecentChatter() {
        Map<String, Long> map = recentChatters.getRecentChatters();
        if (map.isEmpty()) {
            System.out.println("RECENT CHATTERS IS EMPTY");
            return null;

        } else {
            Object randomName = map.entrySet().toArray()[new Random().nextInt(map.entrySet().toArray().length)];
            return randomName.toString().substring(0, randomName.toString().indexOf("="));
        }
    }

    /**
     * gets the first word of a string
     *
     * @param word string to parse
     * @return String
     */
    @Nonnull
    private String getFirstWord(@Nonnull String word) {
        if (word.contains(" ")) {
            word = word.substring(0, word.indexOf(" "));
        }

        return word;
    }

    /**
     * Returns default error message
     *
     * @param userName name of the user that used the command
     * @return String
     */
    @Nonnull
    private String somethingWentWrong(@Nonnull String userName) {
        return String.format("%s, something went wrong :(", userName);
    }

    // Temp
    @Nullable
    private String getStock() {
        try {
            driver.get("https://twitchstocks.com/stock/mnmn");
            String xPath = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-stock-home/div/mat-grid-list/div/mat-grid-tile[1]/figure/mat-card";

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

            WebElement price = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[1]");
            WebElement name = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-header[1]/div/mat-card-title");
            WebElement diff = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[2]");
            return price.getText() + " " + name.getText() + " " + diff.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
