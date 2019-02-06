package poke_models.pokemon;

import com.google.gson.Gson;

public class PokemonSpeciesGender {
    // The chance of this Pokémon being female, in eighths; or -1 for genderless
    private int rate;

    // A Pokémon species that can be the referenced gender
    private PokemonSpecies pokemon_species;

    public int getRate() {
        return rate;
    }

    public PokemonSpeciesGender setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public PokemonSpecies getPokemonSpecies() {
        return pokemon_species;
    }

    public PokemonSpeciesGender setPokemonSpecies(PokemonSpecies pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}