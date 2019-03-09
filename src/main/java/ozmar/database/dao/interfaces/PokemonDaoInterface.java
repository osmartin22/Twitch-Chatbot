package ozmar.database.dao.interfaces;

import ozmar.PokemonPoke;

import javax.annotation.Nonnull;
import java.util.List;

public interface PokemonDaoInterface {

    int getUsersPokemonCount(long userId);

    @Nonnull
    List<PokemonPoke> getPokemon(long userId);

    void insertPokemon(long userId, @Nonnull PokemonPoke poke);

    void updatePokemon(long userId, @Nonnull PokemonPoke poke, int pokeToReplace);
}
