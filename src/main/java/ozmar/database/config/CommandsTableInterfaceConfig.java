package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.CommandsTable;
import ozmar.database.interfaces.CommandsTableInterface;

@Configuration
public class CommandsTableInterfaceConfig {

    @Bean
    CommandsTableInterface commandsTableInterfaceBean() {
        return new CommandsTable();
    }
}
