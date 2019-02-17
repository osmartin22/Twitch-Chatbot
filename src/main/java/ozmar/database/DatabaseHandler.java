package ozmar.database;

import ozmar.database.dao.interfaces.ChatDaoInterface;
import ozmar.database.dao.interfaces.CommandsDaoInterface;
import ozmar.database.dao.interfaces.WordCountDaoInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

import javax.annotation.Nonnull;

public class DatabaseHandler implements DatabaseHandlerInterface {

    private CommandsDaoInterface commandsDao;
    private WordCountDaoInterface wordCountDao;
    private ChatDaoInterface chatDao;


    public DatabaseHandler(CommandsDaoInterface commandsDao, WordCountDaoInterface wordCountDao,
                           ChatDaoInterface chatDao) {
        this.commandsDao = commandsDao;
        this.wordCountDao = wordCountDao;
        this.chatDao = chatDao;
    }

    @Nonnull
    public CommandsDaoInterface getCommandsDao() {
        return commandsDao;
    }

    @Nonnull
    public WordCountDaoInterface getWordCountDao() {
        return wordCountDao;
    }

    @Nonnull
    public ChatDaoInterface getChatDao() {
        return chatDao;
    }
}
