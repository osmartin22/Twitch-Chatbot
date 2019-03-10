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
    public HandleCommandInterface HandleCommandInterfaceBean(DatabaseHandlerInterface databaseHandlerInterface,
                                                             PokeCommandInterface pokeCommandInterface,
                                                             TwitchStockCommandInterface twitchStockCommandInterface,
                                                             TwitchCallCommandInterface twitchCallCommandInterface,
                                                             CalculatorInterface calculatorInterface,
                                                             DiceRollerInterface diceRollerInterface,
                                                             LootBoxInterface lootBoxInterface,
                                                             RecentChattersInterface recentChattersInterface) {

        return new HandleCommand(databaseHandlerInterface, pokeCommandInterface,
                twitchStockCommandInterface, twitchCallCommandInterface, calculatorInterface,
                diceRollerInterface, lootBoxInterface, recentChattersInterface);
    }
}
