package ozmar.pokemonBattle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeController;

import javax.annotation.Nonnull;

@Configuration
public class PokeControllerConfig {

    @Bean
    PokeController pokeControllerBean(@Nonnull PokemonDaoInterface pokemonDaoInterface) {
        return new PokeController(pokemonDaoInterface);
    }
}
