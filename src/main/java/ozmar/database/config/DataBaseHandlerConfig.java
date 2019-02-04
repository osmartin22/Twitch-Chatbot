package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ozmar.database.ChatTable;
import ozmar.database.CommandsTable;
import ozmar.database.DatabaseHandler;
import ozmar.database.WordCountTable;

@Configuration
@Import({CommandsTableConfig.class, ChatTableConfig.class, WordCountTableConfig.class})
public class DataBaseHandlerConfig {

    @Bean
    public DatabaseHandler databaseHandlerBean(CommandsTable commandsTable,
                                               WordCountTable wordCountTable, ChatTable chatTable) {
        return new DatabaseHandler(commandsTable, wordCountTable, chatTable);
    }
}
