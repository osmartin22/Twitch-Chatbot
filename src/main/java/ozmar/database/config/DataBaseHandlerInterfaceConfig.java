package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ozmar.database.DatabaseHandler;
import ozmar.database.interfaces.ChatTableInterface;
import ozmar.database.interfaces.CommandsTableInterface;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.database.interfaces.WordCountTableInterface;

@Configuration
@Import({CommandsTableInterfaceConfig.class, ChatTableInterfaceConfig.class, WordCountTableInterfaceConfig.class})
public class DataBaseHandlerInterfaceConfig {

    @Bean
    public DatabaseHandlerInterface databaseHandlerInterfaceBean(CommandsTableInterface commandsTable,
                                                                 WordCountTableInterface wordCountTable, ChatTableInterface chatTable) {
        return new DatabaseHandler(commandsTable, wordCountTable, chatTable);
    }
}
