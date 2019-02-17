package ozmar.database.tables.interfaces;

import ozmar.database.dao.interfaces.ChatDaoInterface;
import ozmar.database.dao.interfaces.CommandsDaoInterface;
import ozmar.database.dao.interfaces.WordCountDaoInterface;

import javax.annotation.Nonnull;

public interface DatabaseHandlerInterface {

    @Nonnull
    CommandsDaoInterface getCommandsDao();

    @Nonnull
    WordCountDaoInterface getWordCountDao();

    @Nonnull
    ChatDaoInterface getChatDao();
}
