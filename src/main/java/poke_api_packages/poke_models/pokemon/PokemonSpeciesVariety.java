package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;

public class PokemonSpeciesVariety {
    // Whether this variety is the default variety
    private boolean is_default;

    // The Pokémon variety
    private Pokemon pokemon;

    public boolean isDefault() {
        return is_default;
    }

    public PokemonSpeciesVariety setIsDefault(boolean is_default) {
        this.is_default = is_default;
        return this;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public PokemonSpeciesVariety setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}