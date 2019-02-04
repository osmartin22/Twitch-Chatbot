package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.CommandsTable;

@Configuration
public class CommandsTableConfig {

    @Bean
    CommandsTable commandsTableBean() {
        return new CommandsTable();
    }
}
