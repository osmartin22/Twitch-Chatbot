package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import javafx.util.Pair;
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
import java.util.Set;

// TODO: Write command to update command cooldowns or permissions while the bot is running
// To add/delete commands without recompiling would require rewriting part of the twitch4j library
public class HandleCommand implements HandleCommandInterface {

    private CommandEvent commandEvent;
    private final DatabaseHandlerInterface db;

    private final PokeCommandInterface pokeCommand;
    private final TwitchStockCommandInterface twitchStockCommand;
    private final TwitchCallCommandInterface twitchCalls;

    private final CalculatorInterface calculator;
    private final DiceRollerInterface diceRoller;
    private final LootBoxInterface lootBox;
    private final RecentChattersInterface recentChatters;
    private List<Command> commandsList;
    private boolean commandsEnabled = true;

    private Map<Integer, Pair<Command, Long>> cooldownMap;

    public HandleCommand(DatabaseHandlerInterface db, PokeCommandInterface pokeCommand,
                         TwitchStockCommandInterface twitchStockCommand,
                         TwitchCallCommandInterface twitchCalls,
                         CalculatorInterface calculator, DiceRollerInterface diceRoller,
                         LootBoxInterface lootBox, RecentChattersInterface recentChatters) {
        this.db = db;
        this.pokeCommand = pokeCommand;
        this.twitchStockCommand = twitchStockCommand;

        this.calculator = calculator;
        this.diceRoller = diceRoller;
        this.lootBox = lootBox;
        this.recentChatters = recentChatters;
        this.twitchCalls = twitchCalls;
        this.commandsList = db.getCommandsDao().queryCommands();

        this.cooldownMap = new HashMap<>();
        for (Command command : commandsList) {
            cooldownMap.put(command.getId(), new Pair<>(command, 0L));
        }
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

            int count = -1;

            if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = diceRollCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = spitCommand(commandEvent, command);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = twitchCalls.uptime(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = calcCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = twitchCalls.followage(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = wordCountCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = messageCountCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = pointsCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = pokeCommand.catchPokeCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = flipCoinCommand(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = openLootCommand(commandEvent);
                System.out.println(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = newPartnerCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = myPartnerCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = twitchStockCommand.getStock(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                modifyCommandsCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = pokeCommand.myPoke(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = pokeCommand.replacePoke(commandEvent);
            }

        } else if (isCommandHelper(14, commandEvent)) {
            command = commandsList.get(14);
            modifyCommandsCommand(commandEvent);
        }

        if (command != null) {      // Update usage and set cooldown timer
            command.incrementUsage();
            db.getCommandsDao().updateCommandUsage(command);
            cooldownMap.put(command.getId(), new Pair<>(command, System.currentTimeMillis()));
            System.out.println(result);
        }

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
        Command command = commandsList.get(index);
        long commandLastUsed = cooldownMap.get(command.getId()).getValue();

        boolean hasPermission;
        if (event.getUser().getName().equals("namedauto")) {
            hasPermission = true;
        } else {
            hasPermission = hasPermission(command.getPermission(), event.getPermissions());
        }

        return event.getCommandPrefix().equals(command.getCommand()) &&
                hasPermission && isCooldownReady(command, commandLastUsed);
    }

    /**
     * Checks if the user has permission to use the command
     *
     * @param permission           desired command permissions
     * @param commandPermissionSet user permissions
     * @return boolean
     */
    private boolean hasPermission(@Nonnull CommandNumPermission permission,
                                  @Nonnull Set<CommandPermission> commandPermissionSet) {
        CommandNumPermission userPermission = CommandNumPermission.convertToNumPermission(commandPermissionSet);
        return userPermission.getCommandLevel() >= permission.getCommandLevel();
    }

    /**
     * Checks if the command cooldown has passed
     *
     * @param command         Command to check
     * @param commandLastUsed Time the command was last used
     * @return boolean
     */
    private boolean isCooldownReady(@Nonnull Command command, long commandLastUsed) {
        boolean isReady = false;
        long diff = System.currentTimeMillis() - commandLastUsed;
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
//            randomChatter = event.getUser().getName();
            randomChatter = "nightbot";
        }

        return String.format("☄ moon2DEV %s made %s drink their spit moon2D %s people have drank spit",
                event.getUser().getName(), randomChatter, (command.getUsage() + 1));
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
            BigDecimal bigDecimal = BigDecimal.valueOf(result).setScale(10, BigDecimal.ROUND_HALF_EVEN);
            Double value = bigDecimal.doubleValue();
            String numOutput = (result % 1 == 0) ? String.valueOf(value.intValue()) : String.valueOf(value);

            return String.format("%s, %s", event.getUser().getName(), numOutput);

        } catch (Exception e) {
            return String.format("%s, %s", event.getUser().getName(), e.getMessage());
        }
    }

    /**
     * Gets the usage of a word
     *
     * @return String
     */
    @Nullable
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
                    if (word.length() < 35) {   // Prevent long words to prevent the bot from being timed out
                        count = db.getWordCountDao().querySpecificWordCount(word);
                    } else {
                        return null;
                    }
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
            output = String.format("%s left %s for %s PEPELEPSY", user, oldPartner, newPartner);

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
