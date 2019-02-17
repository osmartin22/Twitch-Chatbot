package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.commands.HandleCommand;
import ozmar.commands.interfaces.*;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

@Configuration
public class HandleCommandInterfaceConfig {

    @Bean
    public HandleCommandInterface HandleCommandInterfaceBean(DatabaseHandlerInterface databaseHandler,
                                                             CalculatorInterface calculator,
                                                             DiceInterface dice,
                                                             CatchPokeInterface catchPoke,
                                                             LootBoxInterface lootBox,
                                                             RecentChattersInterface recentChatters) {
        return new HandleCommand(databaseHandler, calculator, dice, catchPoke, lootBox, recentChatters);
    }
}
