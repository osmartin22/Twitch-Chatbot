package ozmar.database.tables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.tables.PokemonTable;
import ozmar.database.tables.interfaces.PokemonTableInterface;

@Configuration
public class PokemonTableInterfaceConfig {

    @Bean
    public PokemonTableInterface pokemonTableInterfaceBean() {
        return new PokemonTable();
    }
}
