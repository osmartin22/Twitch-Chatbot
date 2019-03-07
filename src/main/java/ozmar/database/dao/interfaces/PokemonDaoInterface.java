package ozmar.database.dao.interfaces;

import ozmar.PokemonPoke;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface PokemonDaoInterface {

    int getUsersPokemonCount(long userId);

    @Nullable
    PokemonPoke getPokemon(long userId);

    void insertPokemon(long userId, @Nonnull PokemonPoke poke);

    void updatePokemon(long userId, @Nonnull PokemonPoke poke);
}
