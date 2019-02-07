package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Calculator;
import ozmar.commands.Dice;
import ozmar.commands.HandleCommand;
import ozmar.database.DatabaseHandler;

@Configuration
public class HandleCommandConfig {

    @Bean
    public HandleCommand HandleCommandBean(DatabaseHandler databaseHandler, Calculator calculator, Dice dice) {
        return new HandleCommand(databaseHandler, calculator, dice);
    }

}
