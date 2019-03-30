package poke_api_packages.poke_models.locations;

import com.google.gson.Gson;
import poke_api_packages.poke_models.pokemon.PokemonSpecies;

public class PalParkEncounterSpecies {
    // The base score given to the player when this Pokémon is caught during a pal park run
    private int base_score;

    // The base rate for encountering this Pokémon in this pal park area
    private int rate;

    // The Pokémon species being encountered
    private PokemonSpecies pokemon_species;

    public int getBaseScore() {
        return base_score;
    }

    public PalParkEncounterSpecies setBaseScore(int base_score) {
        this.base_score = base_score;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public PalParkEncounterSpecies setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public PokemonSpecies getPokemonSpecies() {
        return pokemon_species;
    }

    public PalParkEncounterSpecies setPokemonSpecies(PokemonSpecies pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}