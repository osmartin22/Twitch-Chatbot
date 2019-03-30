package poke_api_packages.poke_models.locations;

import com.google.gson.Gson;
import poke_api_packages.poke_models.common.VersionEncounterDetail;
import poke_api_packages.poke_models.pokemon.Pokemon;

import java.util.List;

public class PokemonEncounter {
    // The Pokémon being encountered
    private Pokemon pokemon;

    // A list of versions and encounters with Pokémon that might happen in the referenced location area
    private List<VersionEncounterDetail> version_details;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public PokemonEncounter setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    public List<VersionEncounterDetail> getVersionDetails() {
        return version_details;
    }

    public PokemonEncounter setVersionDetails(List<VersionEncounterDetail> version_details) {
        this.version_details = version_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}