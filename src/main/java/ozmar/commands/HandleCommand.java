package ozmar.commands;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import ozmar.WordFilter;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.commands.interfaces.*;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.enums.CommandNumPermission;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;
import twitch4j_packages.chat.events.CommandEvent;
import twitch4j_packages.common.enums.CommandPermission;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
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

    private final MessageSource source;
    private final Locale defaultLocale;

    public HandleCommand(MessageSource messageSource, DatabaseHandlerInterface db,
                         PokeCommandInterface pokeCommand,
                         TwitchStockCommandInterface twitchStockCommand,
                         TwitchCallCommandInterface twitchCalls,
                         CalculatorInterface calculator, DiceRollerInterface diceRoller,
                         LootBoxInterface lootBox, RecentChattersInterface recentChatters) {
        this.source = messageSource;
        this.defaultLocale = new Locale("en");
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
                command = getCommandFromList(count);
                result = diceRollCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = spitCommand(commandEvent, command);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = twitchCalls.uptime(commandEvent);
                disabledCommands(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = calcCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = twitchCalls.followage(commandEvent);
                disabledCommands(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = commandsList.get(count);
                result = wordCountCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = messageCountCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = pointsCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = pokeCommand.catchPokeCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = flipCoinCommand(commandEvent);
                disabledCommands(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = openLootCommand(commandEvent);
                disabledCommands(result);
                result = null;

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = newPartnerCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = myPartnerCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = twitchStockCommand.getStock(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                modifyCommandsCommand(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = pokeCommand.myPoke(commandEvent);

            } else if (isCommandHelper(++count, commandEvent)) {
                command = getCommandFromList(count);
                result = pokeCommand.replacePoke(commandEvent);
            }

        } else if (isCommandHelper(14, commandEvent)) {
            command = getCommandFromList(14);
            modifyCommandsCommand(commandEvent);
        }

        // Log the output and the command it responded from
        if (command != null && result != null) {
            log.info(result);
            log.info(commandEvent.toString());
        }

        return result;
    }

    private void disabledCommands(@Nonnull String result) {
        log.info("Not Sent: {}", result);
        log.info(this.commandEvent.toString());
    }

    @Nonnull
    private Command getCommandFromList(int commandIndex) {
        Command command = this.commandsList.get(commandIndex);
        putOnCooldown(command);
        return command;
    }

    private void putOnCooldown(@Nonnull Command command) {
        command.incrementUsage();
        this.db.getCommandsDao().updateCommandUsage(command);
        this.cooldownMap.put(command.getId(), new Pair<>(command, System.currentTimeMillis()));
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
        if (event.getUser().getName().equals(source.getMessage("cmd.owner", null, defaultLocale))) {
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

        return source.getMessage("cmd.dice.roll",
                new String[]{event.getUser().getName(), String.valueOf(rollResult)}, defaultLocale);
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
            randomChatter = source.getMessage("cmd.default.spit.user", null, defaultLocale);
        }

        return source.getMessage("cmd.spit.message",
                new String[]{event.getUser().getName(), randomChatter, String.valueOf(command.getUsage() + 1)}, defaultLocale);
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
            String numOutput = (result % 1 == 0) ? String.valueOf(value.longValue()) : String.valueOf(value);

            return source.getMessage("cmd.calc.message",
                    new String[]{event.getUser().getName(), numOutput}, defaultLocale);

        } catch (Exception e) {
            return source.getMessage("cmd.calc.message",
                    new String[]{event.getUser().getName(), e.getMessage()}, defaultLocale);
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
            result = source.getMessage("cmd.word.count.error", new String[]{event.getUser().getName()}, defaultLocale);
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
                result = source.getMessage("cmd.word.count",
                        new String[]{event.getUser().getName(), word, String.valueOf(count)}, defaultLocale);

            } else {
                // SHOULD PROBABLY REMOVE THIS WHEN THE METHOD IS MADE PUBLIC
                result = source.getMessage("cmd.word.count.filter",
                        new String[]{event.getUser().getName()}, defaultLocale);
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
            result = (count != -1) ? source.getMessage("cmd.msg.user",
                    new String[]{event.getUser().getName(), String.valueOf(count)}, defaultLocale) :
                    somethingWentWrong(event.getUser().getName());
        } else {
            message = StringHelper.getFirstWord(message);
            count = db.getChatDao().getMessageCount(message);

            result = (count != -1) ?
                    source.getMessage("cmd.msg.other", new String[]{message, String.valueOf(count)}, defaultLocale) :
                    source.getMessage("cmd.user.not.found", new String[]{event.getUser().getName(), message}, defaultLocale);
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
            result = (points != -1) ? source.getMessage("cmd.points.user",
                    new String[]{event.getUser().getName(), String.valueOf(points)}, defaultLocale) :
                    somethingWentWrong(event.getUser().getName());
        } else {
            message = StringHelper.getFirstWord(message);
            points = db.getChatDao().getPoints(message);
            result = (points != -1) ?
                    source.getMessage("cmd.points.other", new String[]{message, String.valueOf(points)}, defaultLocale) :
                    source.getMessage("cmd.user.not.found", new String[]{event.getUser().getName(), message}, defaultLocale);
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
        return (heads) ? source.getMessage("cmd.flip.heads", new String[]{event.getUser().getName()}, defaultLocale) :
                source.getMessage("cmd.flip.tails", new String[]{event.getUser().getName()}, defaultLocale);
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
            output = source.getMessage("cmd.partner.self", new String[]{user}, defaultLocale);

        } else if (oldPartner == null) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            output = source.getMessage("cmd.partner.new", new String[]{user, newPartner}, defaultLocale);

        } else if (!newPartner.equals(oldPartner)) {
            db.getChatDao().updatePartner(event.getUser().getId(), newPartner);
            output = source.getMessage("cmd.partner.old", new String[]{user, oldPartner, newPartner}, defaultLocale);

        } else {
            output = source.getMessage("cmd.partner.same", new String[]{user, newPartner}, defaultLocale);
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
        return (currPartner != null) ?
                source.getMessage("cmd.partner.curr", new String[]{user, currPartner}, defaultLocale) :
                source.getMessage("cmd.partner.none", new String[]{user}, defaultLocale);
    }

    /**
     * Returns default error message
     *
     * @param userName name of the user that used the command
     * @return String
     */
    @Nonnull
    private String somethingWentWrong(@Nonnull String userName) {
        return source.getMessage("cmd.error", new String[]{userName}, defaultLocale);
    }

    // TODO: possibly make it it's own class
    private void modifyCommandsCommand(@Nonnull CommandEvent event) {
        String[] info = event.getCommand().trim().split("\\s+");
        String commandAction = info[0];
        if (commandAction.equals(source.getMessage("cmd.mcs.disable", null, defaultLocale))) {
            disableCommands();

        } else if (commandAction.equals(source.getMessage("cmd.mcs.enable", null, defaultLocale))) {
            enableCommands();

        } else if (commandAction.equals(source.getMessage("cmd.mcs.cd", null, defaultLocale)) && info.length > 2) {
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
                result = source.getMessage("cmd.mcs.update.success", new String[]{commandName}, defaultLocale);

            } else {
                result = source.getMessage("cmd.mcs.update.failed", new String[]{commandName}, defaultLocale);
            }
        } catch (NumberFormatException e) {
            log.error("New cooldown was not a number: {}", e.getMessage());
            result = source.getMessage("cmd.mcs.update.failed", new String[]{commandName}, defaultLocale);
        }

        log.info("Not Sent: {}", result);
    }

    private void disableCommands() {
        commandsEnabled = false;
    }

    private void enableCommands() {
        commandsEnabled = true;
    }
}
