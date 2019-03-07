package ozmar.database.tables.interfaces;

import ozmar.PokemonPoke;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface PokemonTableInterface {

    @Nullable
    PokemonPoke getPokemon(long userId);

    void insertPokemon(long userId, @Nonnull PokemonPoke poke);

    void updatePokemon(long userId, @Nonnull PokemonPoke poke);

    int getUsersPokemonCount(long userId);
}
