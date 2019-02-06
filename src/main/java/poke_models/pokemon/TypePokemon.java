package poke_models.pokemon;

import com.google.gson.Gson;

public class TypePokemon {
    // The order the Pokémon's types are listed in
    private int slot;

    // The Pokémon that has the referenced type
    private Pokemon pokemon;

    public int getSlot() {
        return slot;
    }

    public TypePokemon setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public TypePokemon setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}