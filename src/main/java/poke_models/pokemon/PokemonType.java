package poke_models.pokemon;

import com.google.gson.Gson;

public class PokemonType {
    // The order the Pokémon's types are listed in
    private int slot;

    // The type the referenced Pokémon has
    private Type type;

    public int getSlot() {
        return slot;
    }

    public PokemonType setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public Type getType() {
        return type;
    }

    public PokemonType setType(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}