package ozmar.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ozmar.database.dao.interfaces.ChatDaoInterface;
import ozmar.database.dao.interfaces.CommandsDaoInterface;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.database.dao.interfaces.WordCountDaoInterface;
import ozmar.database.tables.config.ChatTableInterfaceConfig;
import ozmar.database.tables.config.CommandsTableInterfaceConfig;
import ozmar.database.tables.config.WordCountTableInterfaceConfig;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

@Configuration
@Import({CommandsTableInterfaceConfig.class, ChatTableInterfaceConfig.class, WordCountTableInterfaceConfig.class})
public class DataBaseHandlerInterfaceConfig {

    @Bean
    public DatabaseHandlerInterface databaseHandlerInterfaceBean(CommandsDaoInterface commandsDao,
                                                                 WordCountDaoInterface wordCountDao,
                                                                 ChatDaoInterface chatDao,
                                                                 PokemonDaoInterface pokemonDao) {
        return new DatabaseHandler(commandsDao, wordCountDao, chatDao, pokemonDao);
    }
}
