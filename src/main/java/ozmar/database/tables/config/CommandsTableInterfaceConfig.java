package ozmar.database.tables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.tables.CommandsTable;
import ozmar.database.tables.interfaces.CommandsTableInterface;

@Configuration
public class CommandsTableInterfaceConfig {

    @Bean
    CommandsTableInterface commandsTableInterfaceBean() {
        return new CommandsTable();
    }
}
