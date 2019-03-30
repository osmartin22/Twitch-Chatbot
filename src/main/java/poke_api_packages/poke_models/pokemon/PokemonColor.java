package poke_api_packages.poke_models.pokemon;
/*
{
    "id": 1,
    "name": "black",
    "names": [{
        "name": "é»’ã„",
        "language": {
            "name": "ja",
            "url": "http://pokeapi.co/api/v2/language/1/"
        }
    }],
    "pokemon_species": [{
        "name": "snorlax",
        "url": "http://pokeapi.co/api/v2/pokemon-species/143/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class PokemonColor extends NamedAPIResource {
    // The identifier for this Pokémon color resource
    private int id;

    // The name of this Pokémon color listed in different languages
    private List<Name> names;

    // A list of the Pokémon species that have this color
    private List<PokemonSpecies> pokemon_species;

    private static PokemonColor get(String url) {
        String json = Information.fromInternet(url);
        PokemonColor obj = new Gson().fromJson(json, PokemonColor.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static PokemonColor getById(int id) {
        return get("https://pokeapi.co/api/v2/pokemon-color/" + id);
    }

    public static PokemonColor getByName(String name) {
        return get("https://pokeapi.co/api/v2/pokemon-color/" + name);
    }

    public int getId() {
        return id;
    }

    public PokemonColor setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public PokemonColor setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public PokemonColor setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}