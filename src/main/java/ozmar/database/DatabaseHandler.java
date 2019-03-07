package ozmar.database;

import ozmar.database.dao.interfaces.ChatDaoInterface;
import ozmar.database.dao.interfaces.CommandsDaoInterface;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.database.dao.interfaces.WordCountDaoInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

import javax.annotation.Nonnull;

public class DatabaseHandler implements DatabaseHandlerInterface {

    private CommandsDaoInterface commandsDao;
    private WordCountDaoInterface wordCountDao;
    private ChatDaoInterface chatDao;
    private PokemonDaoInterface pokemonDao;


    public DatabaseHandler(CommandsDaoInterface commandsDao, WordCountDaoInterface wordCountDao,
                           ChatDaoInterface chatDao, PokemonDaoInterface pokemonDao) {
        this.commandsDao = commandsDao;
        this.wordCountDao = wordCountDao;
        this.chatDao = chatDao;
        this.pokemonDao = pokemonDao;
    }

    @Nonnull
    @Override
    public CommandsDaoInterface getCommandsDao() {
        return commandsDao;
    }

    @Nonnull
    @Override
    public WordCountDaoInterface getWordCountDao() {
        return wordCountDao;
    }

    @Nonnull
    @Override
    public ChatDaoInterface getChatDao() {
        return chatDao;
    }

    @Nonnull
    @Override
    public PokemonDaoInterface getPokemonDao() {
        return pokemonDao;
    }
}
