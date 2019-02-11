package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.*;
import ozmar.database.DatabaseHandler;

@Configuration
public class HandleCommandConfig {

    @Bean
    public HandleCommand HandleCommandBean(DatabaseHandler databaseHandler, Calculator calculator, Dice dice,
                                           CatchPoke catchPoke, LootBox lootBox) {
        return new HandleCommand(databaseHandler, calculator, dice, catchPoke, lootBox);
    }
}
