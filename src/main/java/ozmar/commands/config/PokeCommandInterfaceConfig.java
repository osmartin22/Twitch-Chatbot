package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.PokeCommand;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.commands.interfaces.PokeCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

@Configuration
public class PokeCommandInterfaceConfig {

    @Bean
    public PokeCommandInterface pokeCommandInterfaceBean(DatabaseHandlerInterface databaseHandlerInterface,
                                                         CatchPokeInterface catchPokeInterface) {
        return new PokeCommand(databaseHandlerInterface, catchPokeInterface);
    }
}
