package ozmar.database;

import com.github.twitch4j.helix.domain.User;
import ozmar.commands.Command;
import ozmar.database.interfaces.ChatTableInterface;
import ozmar.database.interfaces.CommandsTableInterface;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.database.interfaces.WordCountTableInterface;
import ozmar.user.ChatUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class DatabaseHandler implements DatabaseHandlerInterface {

    private CommandsTableInterface commandsTable;
    private WordCountTableInterface wordCountTable;
    private ChatTableInterface chatTable;

    public DatabaseHandler(CommandsTableInterface commandsTable, WordCountTableInterface wordCountTable,
                           ChatTableInterface chatTable) {
        this.commandsTable = commandsTable;
        this.wordCountTable = wordCountTable;
        this.chatTable = chatTable;
    }


    // CommandsTable
    @Override
    public List<Command> getCommands() {
        return commandsTable.queryCommands();
    }

    @Override
    public void insertCommand(@Nonnull Command command) {
        commandsTable.insertCommand(command);
    }

    @Override
    public void updateCommandUsage(@Nonnull Command command) {
        commandsTable.updateCommandUsage(command);
    }


    // WordCountTable
    @Override
    public Map<String, Integer> getWordCount() {
        return wordCountTable.queryWordCount();
    }

    @Override
    public Map<String, Integer> getTop10Words() {
        return wordCountTable.getTop10Words();
    }

    @Override
    public int getSpecificWordCount(@Nonnull String word) {
        return wordCountTable.getSpecificWordCount(word);
    }

    @Override
    public void updateOrInsertWordCount(@Nonnull Map<String, Integer> map) {
        if (!map.isEmpty()) {
            wordCountTable.updateOrInsert(map);
        }
    }

    @Override
    public void clearWordCount() {
        wordCountTable.clearTable();
    }


    // ChatTable
    @Override
    public long getUserId(@Nonnull String userName) {
        return chatTable.getUserId(userName);
    }

    @Override
    public void addUserList(@Nonnull List<User> userList) {
        if (!userList.isEmpty()) {
            chatTable.addUsersToTable(userList);
        }
    }

    @Override
    public void checkIfNamesExist(@Nonnull List<String> list) {
        chatTable.checkIfNameExists(list);
    }

    @Override
    public void updatePoints(@Nonnull Map<Long, ChatUser> map) {
        if (!map.isEmpty()) {
            chatTable.updatePoints(map);
        }
    }

    @Override
    public int getMessageCount(long userId) {
        return chatTable.getMessageCountByUserId(userId);
    }

    @Override
    public int getMessageCount(@Nonnull String userName) {
        return chatTable.getMessageCountByUserName(userName);
    }

    @Override
    public int getPoints(long userId) {
        return chatTable.getPointsByUserId(userId);
    }

    @Override
    public int getPoints(@Nonnull String userName) {
        return chatTable.getPointsByUserName(userName);
    }

    @Nullable
    @Override
    public String getPartner(long userId) {
        return chatTable.getPartnerById(userId);
    }

    @Override
    public void updatePartner(long userId, @Nonnull String newValentine) {
        chatTable.updatePartner(userId, newValentine);
    }
}
