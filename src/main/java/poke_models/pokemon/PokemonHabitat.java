package poke_models.pokemon;
/*
{
    "id": 1,
    "name": "cave",
    "names": [
        {
            "name": "grottes",
            "language": {
                "name": "fr",
                "url": "http://pokeapi.co/api/v2/language/5/"
            }
        }
    ],
    "pokemon_species": [
        {
            "name": "zubat",
            "url": "http://pokeapi.co/api/v2/pokemon-species/41/"
        }
    ]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class PokemonHabitat extends NamedAPIResource {
    // The identifier for this Pokémon habitat resource
    private int id;

    // The name of this Pokémon habitat listed in different languages
    private List<Name> names;

    // A list of the Pokémon species that can be found in this habitat
    private List<PokemonSpecies> pokemon_species;

    private static PokemonHabitat get(String url) {
        String json = Information.fromInternet(url);
        PokemonHabitat obj = new Gson().fromJson(json, PokemonHabitat.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static PokemonHabitat getById(int id) {
        return get("https://pokeapi.co/api/v2/pokemon-habitat/" + id);
    }

    public static PokemonHabitat getByName(String name) {
        return get("https://pokeapi.co/api/v2/pokemon-habitat/" + name);
    }

    public int getId() {
        return id;
    }

    public PokemonHabitat setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public PokemonHabitat setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public PokemonHabitat setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}