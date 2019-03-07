package ozmar.database.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.dao.PokemonDao;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.database.tables.interfaces.PokemonTableInterface;

@Configuration
public class PokemonDaoConfig {

    @Bean
    public PokemonDaoInterface pokemonDaoInterfaceBean(PokemonTableInterface pokemonTable) {
        return new PokemonDao(pokemonTable);
    }
}
