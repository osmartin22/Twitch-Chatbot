package ozmar.commands.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.PokeCommand;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.commands.interfaces.PokeCommandInterface;
import ozmar.database.dao.interfaces.PokemonDaoInterface;

@Configuration
public class PokeCommandInterfaceConfig {

    @Bean
    public PokeCommandInterface pokeCommandInterfaceBean(MessageSource messageSource,
                                                         PokemonDaoInterface pokemonDaoInterface,
                                                         CatchPokeInterface catchPokeInterface
    ) {
        return new PokeCommand(messageSource, pokemonDaoInterface, catchPokeInterface);
    }
}
