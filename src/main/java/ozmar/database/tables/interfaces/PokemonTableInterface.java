package ozmar.database.tables.interfaces;

import ozmar.PokemonPoke;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.List;

public interface PokemonTableInterface {

    @NonNull
    List<PokemonPoke> getPokemon(long userId);

    void insertPokemon(long userId, @Nonnull PokemonPoke poke);

    void updatePokemon(long userId, @Nonnull PokemonPoke poke, int pokeToReplace);

    int getUsersPokemonCount(long userId);
}
