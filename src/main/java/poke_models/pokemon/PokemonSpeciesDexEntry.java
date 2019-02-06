package poke_models.pokemon;

import com.google.gson.Gson;
import poke_models.games.Pokedex;

public class PokemonSpeciesDexEntry {
    // The index number within the Pokédex
    private int entry_number;

    // The Pokédex the referenced Pokémon species can be found in
    private Pokedex pokedex;

    public int getEntryNumber() {
        return entry_number;
    }

    public PokemonSpeciesDexEntry setEntryNumber(int entry_number) {
        this.entry_number = entry_number;
        return this;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public PokemonSpeciesDexEntry setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}