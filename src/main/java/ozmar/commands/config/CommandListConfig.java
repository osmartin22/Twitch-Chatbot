package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Calculator;
import ozmar.commands.CommandList;
import ozmar.database.DatabaseHandler;

@Configuration
public class CommandListConfig {

    @Bean
    public CommandList commandListBean(DatabaseHandler databaseHandler, Calculator calculator) {
        return new CommandList(databaseHandler, calculator);
    }

}
