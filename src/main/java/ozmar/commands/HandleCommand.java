package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ozmar.WordFilter;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.commands.interfaces.*;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.enums.CommandNumPermission;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Write command to update command cooldowns or permissions while the bot is running
// To add/delete commands without recompiling would require rewriting part of the twitch4j library
public class HandleCommand implements HandleCommandInterface {

    private CommandEvent commandEvent;
    private final DatabaseHandlerInterface db;
    private final CalculatorInterface calculator;
    private final DiceRollerInterface diceRoller;
    private final CatchPokeInterface catchPoke;
    private final LootBoxInterface lootBox;
    private final RecentChattersInterface recentChatters;
    private final TwitchCallsInterface twitchCalls;
    private List<Command> commandsList;
    private boolean commandsEnabled = true;

    private WebDriver driver;
    private Map<Integer, Pair<Command, Long>> cooldownMap;

    public HandleCommand(DatabaseHandlerInterface db, CalculatorInterface calculator, DiceRollerInterface diceRoller,
                         CatchPokeInterface catchPoke, LootBoxInterface lootBox, RecentChattersInterface recentChatters,
                         TwitchCallsInterface twitchCalls) {
        this.db = db;
        this.calculator = calculator;
        this.diceRoller = diceRoller;
        this.catchPoke = catchPoke;
        this.lootBox = lootBox;
        this.recentChatters = recentChatters;
        this.twitchCalls = twitchCalls;
        this.commandsList = db.getCommandsDao().queryCommands();

        this.cooldownMap = new HashMap<>();
        for (Command command : commandsList) {
            cooldownMap.put(command.getId(), new Pair<>(command, 0L));
        }


        ChromeOptions chromeOptions = new ChromeOptions();
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
        String result = null;
        Command command = null;

        if (commandsEnabled) {
            if (isCommandHelper(0, commandEvent)) {
                command = commandsList.get(0);
                result = diceRollCommand(commandEvent);

            } else if (isCommandHelper(1, commandEvent)) {
                command = commandsList.get(1);
                result = spitCommand(commandEvent, command);

            } else if (isCommandHelper(2, commandEvent)) {
                command = commandsList.get(2);
                result = uptimeCommand(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(3, commandEvent)) {
                command = commandsList.get(3);
                result = calcCommand(commandEvent);

            } else if (isCommandHelper(4, commandEvent)) {
                command = commandsList.get(4);
                result = followageCommand(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(5, commandEvent)) {
                command = commandsList.get(5);
                result = wordCountCommand(commandEvent);

            } else if (isCommandHelper(6, commandEvent)) {
                command = commandsList.get(6);
                result = messageCountCommand(commandEvent);

            } else if (isCommandHelper(7, commandEvent)) {
                command = commandsList.get(7);
                result = pointsCommand(commandEvent);

            } else if (isCommandHelper(8, commandEvent)) {
                command = commandsList.get(8);
                result = catchPokeCommand(commandEvent);

            } else if (isCommandHelper(9, commandEvent)) {
                command = commandsList.get(9);
                result = flipCoinCommand(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(10, commandEvent)) {
                command = commandsList.get(10);
                result = openLootCommand(commandEvent);

            } else if (isCommandHelper(11, commandEvent)) {
                command = commandsList.get(11);
                result = newPartnerCommand(commandEvent);

            } else if (isCommandHelper(12, commandEvent)) {
                command = commandsList.get(12);
                result = myPartnerCommand(commandEvent);

            } else if (isCommandHelper(13, commandEvent)) {
                command = commandsList.get(13);
                result = getStock(commandEvent);

            } else if (isCommandHelper(14, commandEvent)) {
                command = commandsList.get(14);
                modifyCommandsCommand(commandEvent);
            }

        } else if (isCommandHelper(14, commandEvent)) {
            command = commandsList.get(14);
            modifyCommandsCommand(commandEvent);
        }

        if (command != null) {      // Update usage and set cooldown timer
            command.incrementUsage();
            db.getCommandsDao().updateCommandUsage(command);
            cooldownMap.put(command.getId(), new Pair<>(command, System.currentTimeMillis()));
        }

        System.out.println(result);
        return result;
    }

    /**
     * Checks if the command from twitch chat matches one of the bots commands
     * Also checks if the user has permission
     *
     * @param index command index in the list
     * @param event User info and command data
     * @return boolean
     */
    private boolean isCommandHelper(int index, @Nonnull CommandEvent event) {
        return event.getCommandPrefix().equals(commandsList.get(index).getCommand()) &&
                hasPermission(index, event) && isCooldownReady(index);
    }

    /**
     * Checks if the user has permission to use the command
     *
     * @param index command index in the list
     * @param event User info and command data
     * @return boolean
     */
    private boolean hasPermission(int index, @Nonnull CommandEvent event) {
        boolean hasPermission;
        CommandNumPermission permission = commandsList.get(index).getPermission();
        CommandNumPermission userPermission = CommandNumPermission.convertToNumPermission(commandEvent.getPermissions());

        if (event.getUser().getName().equals("namedauto")) {    // Allow my account to use any command
            hasPermission = true;
        } else {
            hasPermission = userPermission.getCommandLevel() >= permission.getCommandLevel();
        }

        return hasPermission;
    }

    /**
     * Checks if the command cooldown has passed
     *
     * @param index command index in the list
     * @return boolean
     */
    private boolean isCooldownReady(int index) {
        boolean isReady = false;
        Command command = commandsList.get(index);
        long diff = System.currentTimeMillis() - cooldownMap.get(command.getId()).getValue();
        if (diff > command.getCooldown()) {
            isReady = true;
        }

        return isReady;
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
        String command = event.getCommand().trim();
        Integer rollResult;
        if (!command.isEmpty()) {
            String[] dieSettings = command.split("\\s+", 3);
            rollResult = (dieSettings.length == 1) ? diceRoller.roll(dieSettings[0], 1) :
                    diceRoller.roll(dieSettings[0], dieSettings[1]);
        } else {
            rollResult = diceRoller.roll(20, 1);
        }

        if (rollResult == null) {
            return somethingWentWrong(event.getUser().getName());
        }

        return String.format("%s rolled a %s", event.getUser().getName(), rollResult);
    }

    /**
     * @param event   User info and command data
     * @param command command
     * @return String
     */
    @Nonnull
    private String spitCommand(@Nonnull CommandEvent event, @Nonnull Command command) {
        String randomChatter = recentChatters.getRandomRecentChatter();
        if (randomChatter == null) {
            randomChatter = event.getUser().getName();
        }
        return String.format("☄ moon2DEV %s made %s drink their spit moon2D %s people have drank spit",
                event.getUser().getName(), randomChatter, (command.getUsage() + 1));
    }

    /**
     * Gets the uptime of a stream if currently streaming
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String uptimeCommand(@Nonnull CommandEvent event) {
        return twitchCalls.uptime(event);
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
        try {
            calculator.setOperation(event.getCommand());
            double result = calculator.parse();

            // Round to n decimal places, remove decimal if it is a whole number
            BigDecimal bigDecimal = BigDecimal.valueOf(result).setScale(10, BigDecimal.ROUND_UNNECESSARY);
            Double value = bigDecimal.doubleValue();
            String numOutput = (result % 1 == 0) ? String.valueOf(value.intValue()) : String.valueOf(value);

            return String.format("%s, %s", event.getUser().getName(), numOutput);

        } catch (Exception e) {
            return String.format("%s, %s", event.getUser().getName(), e.getMessage());
        }
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
    private String followageCommand(@Nonnull CommandEvent event) {
        return twitchCalls.followage(event, db);
    }

    /**
     * Gets the usage of a word
     *
     * @return String
     */
    // TODO: Parse the word, the same way I parse messages in OnChatChannelMessage
    @Nonnull
    private String wordCountCommand(@Nonnull CommandEvent event) {
        String result;
        String word = event.getCommand().trim();

        if (word.isEmpty()) {
            result = String.format("%s, try again using !wordcount <word>", event.getUser().getName());
        } else {
            List<String> badWordsFound = WordFilter.badWordsFound(word);
            if (badWordsFound.isEmpty()) {
                word = StringHelper.getFirstWord(word);

                int count;
                List<String> emojiList = WordFilter.extractEmojis(word);
                if (!emojiList.isEmpty()) {
                    int temp1 = db.getWordCountDao().querySpecificWordCount(emojiList.get(0));
                    if (emojiList.size() == 1) {
                        count = temp1;
                        word = emojiList.get(0);
                    } else {
                        int temp2 = db.getWordCountDao().querySpecificWordCount(emojiList.get(1));
                        count = Math.min(temp1, temp2);
                        word = emojiList.get(0) + emojiList.get(1);
                    }
                } else {
                    word = WordFilter.transformWord(word);
                    count = db.getWordCountDao().querySpecificWordCount(word);
                }

                word = WordFilter.timeoutWordFound(word);
                result = String.format("%s, %s has been used %s times in my lifetime",
                        event.getUser().getName(), word, count);

            } else {
                result = String.format("%s, D: you can't say that", event.getUser().getName());
            }
        }

        return result;
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
        String result;
        String message = event.getCommand().trim().toLowerCase();

        if (message.isEmpty()) {
            count = db.getChatDao().getMessageCount(event.getUser().getId()) + 1;
            result = (count != -1) ? String.format("%s, you have sent %s messages", event.getUser().getName(), count) :
                    somethingWentWrong(event.getUser().getName());
        } else {
            message = StringHelper.getFirstWord(message);
            count = db.getChatDao().getMessageCount(message);
            result = (count != -1) ? String.format("%s has sent %s messages ", message, count) :
                    String.format("%s, %s does not exist in my database", event.getUser().getName(), message);
        }

        return result;
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
        String result;
        String message = event.getCommand().trim().toLowerCase();

        if (message.isEmpty()) {
            points = db.getChatDao().getPoints(event.getUser().getId());
            result = (points != -1) ? String.format("%s, you have %s points", event.getUser().getName(), points) :
                    somethingWentWrong(event.getUser().getName());
        } else {
            message = StringHelper.getFirstWord(message);
            points = db.getChatDao().getPoints(message);
            result = (points != -1) ? String.format("%s has %s points  ", message, points) :
                    String.format("%s, %s does not exist in my database", event.getUser().getName(), message);
        }

        return result;
    }

    /**
     * Gets a random pokemon with a name, nature, and gender and decides if it was caught or not
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    private String catchPokeCommand(@Nonnull CommandEvent event) {
        String input = event.getCommand().trim().toLowerCase();

        // Shortest Pokemon name is 3 letters long (Mew, Muk)
        // Only 807 pokemon currently exist
        String pokeName = (input.length() < 3) ? null : StringHelper.getFirstWord(input);
        int initializeResult = (pokeName != null) ? catchPoke.initialize(pokeName) :
                catchPoke.initialize(RandomHelper.getRandNumInRange(1, 807));

        String output;
        if (initializeResult != -1) {
            String result = catchPoke.attemptCatch();
            output = (result != null) ? event.getUser().getName() + result :
                    somethingWentWrong(event.getUser().getName());
        } else {
            output = String.format("%s, that is not a Pokemon, try !catchpoke <optionalName>", event.getUser().getName());
        }

        return output;
    }

    /**
     * Flips a coin and returns the result
     *
     * @param event User id and info
     * @return String
     */
    @Nonnull
    private String flipCoinCommand(@Nonnull CommandEvent event) {
        boolean heads = RandomHelper.getRandNumInRange(0, 1) == 1;
        return (heads) ? String.format("%s flipped heads", event.getUser().getName()) :
                String.format("%s flipped tails", event.getUser().getName());
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
        return (!result.isEmpty()) ? event.getUser().getName() + result : somethingWentWrong(event.getUser().getName());
    }

    /**
     * Gets a random user from the list of recent chatters and replaces them in the database
     *
     * @param event User data and command info
     * @return String
     */
    @Nonnull
    private String newPartnerCommand(@Nonnull CommandEvent event) {
        if (recentChatters.getRecentChatters().size() == 0) {
            return somethingWentWrong(event.getUser().getName());
        }

        String newPartner = recentChatters.getRandomRecentChatter();
        if (newPartner == null) {
            return somethingWentWrong(event.getUser().getName());
        }

        String output;
        String user = event.getUser().getName();
        String oldPartner = db.getChatDao().getPartnerById(event.getUser().getId());
        if (newPartner.equals(user)) {
            output = String.format("%s could only get themselves moon2PH", user);

        } else if (oldPartner == null) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            output = String.format("%s, your partner is %s billyReady", user, newPartner);

        } else if (!newPartner.equals(oldPartner)) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            output = String.format("%s left %s D: for %s pepeBASS", user, oldPartner, newPartner);

        } else {
            output = String.format("%s got the same partner, %s moon2N", user, newPartner);
        }

        return output;
    }

    /**
     * Gets the users current partner
     *
     * @param event User data and command info
     * @return String
     */
    @Nonnull
    private String myPartnerCommand(@Nonnull CommandEvent event) {
        String user = event.getUser().getName();
        String currPartner = db.getChatDao().getPartnerById(event.getUser().getId());

        return (currPartner != null) ? String.format("%s, your current partner is %s pepeBASS", user, currPartner) :
                String.format("%s, you do not have a partner, try !newpartner to get one", user);
    }

    /**
     * Returns default error message
     *
     * @param userName name of the user that used the command
     * @return String
     */
    @Nonnull
    private String somethingWentWrong(@Nonnull String userName) {
        return String.format("%s, something went wrong moon2WAH", userName);
    }

    /**
     * Checks the website and gets the stock info
     * NOTE: website has no api so everything must be done through web scraping
     *
     * @param event User data anc command info
     * @return String
     */
    // TODO: Remove to separate class
    @Nullable
    private String getStock(@Nonnull CommandEvent event) {
        String stockSymbol = StringHelper.getFirstWord(event.getCommand().trim().toLowerCase());
        String url = "https://twitchstocks.com/stock/";
        String result;
        if (stockSymbol.isEmpty()) {
            result = "Try again using !checkstocks <StockSymbol>";
        } else {
            try {
                driver.get(url + stockSymbol);
                String xPath = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-stock-home/div/mat-grid-list/div/mat-grid-tile[1]/figure/mat-card";

                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

                WebElement price = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[1]");
                WebElement name = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-header[1]/div/mat-card-title");
                WebElement diff = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[2]");
                result = price.getText() + " " + name.getText() + " " + diff.getText();
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }

    // TODO: possibly make it it's own class
    private void modifyCommandsCommand(@Nonnull CommandEvent event) {
        String[] info = event.getCommand().trim().split("\\s+");
        String commandAction = info[0];
        if (commandAction.equals("disable")) {
            disableCommands();

        } else if (commandAction.equals("enable")) {
            enableCommands();

        } else if (commandAction.equals("cd") && info.length > 2) {
            updateCommandCooldown(info[1], info[2]);
        }

    }

    private void updateCommandCooldown(@Nonnull String commandName, @Nonnull String cooldown) {
        String result;
        try {
            long newCooldown = Long.valueOf(cooldown);
            if (db.getCommandsDao().updateCommandCooldown(commandName, newCooldown)) {
                commandsList = db.getCommandsDao().queryCommands();
                Map<Integer, Pair<Command, Long>> tempMap = new HashMap<>();
                for (Command command : commandsList) {
                    tempMap.put(command.getId(), new Pair<>(command, 0L));
                }
                cooldownMap = tempMap;
                result = String.format("Update successful for %s", commandName);

            } else {
                result = String.format("Failed to update command %s", commandName);
            }
        } catch (NumberFormatException e) {
            System.out.println("New cooldown was not a number: " + e.getMessage());
            result = String.format("Failed to update command %s", commandName);
        }

        System.out.println(result);
    }

    private void disableCommands() {
        commandsEnabled = false;
    }

    private void enableCommands() {
        commandsEnabled = true;
    }
}
