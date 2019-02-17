package ozmar.database.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.dao.CommandsDao;
import ozmar.database.tables.interfaces.CommandsTableInterface;

@Configuration
public class CommandsDaoConfig {

    @Bean
    public CommandsDao commandsDaoBean(CommandsTableInterface commandsTable) {
        return new CommandsDao(commandsTable);
    }
}
