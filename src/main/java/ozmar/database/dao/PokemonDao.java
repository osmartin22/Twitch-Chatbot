package ozmar.database.dao;

import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.database.tables.interfaces.PokemonTableInterface;

import javax.annotation.Nonnull;
import java.util.List;

public class PokemonDao implements PokemonDaoInterface {

    private PokemonTableInterface pokemonTable;

    public PokemonDao(PokemonTableInterface pokemonTable) {
        this.pokemonTable = pokemonTable;
    }

    @Override
    public int getUsersPokemonCount(long userId) {
        return pokemonTable.getUsersPokemonCount(userId);
    }

    @Nonnull
    @Override
    public List<PokemonPoke> getPokemon(long userId) {
        return pokemonTable.getPokemon(userId);
    }

    @Override
    public void insertPokemon(long userId, @Nonnull PokemonPoke poke) {
        pokemonTable.insertPokemon(userId, poke);
    }

    @Override
    public void updatePokemon(long userId, @Nonnull PokemonPoke poke, int pokeToReplace) {
        pokemonTable.updatePokemon(userId, poke, pokeToReplace);
    }
}
