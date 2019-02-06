package poke_models.pokemon;
/*
{
    "id": 1,
    "name": "monster",
    "names": [{
        "name": "ã‹ã„ã˜ã‚…ã†",
        "language": {
            "name": "ja",
            "url": "http://pokeapi.co/api/v2/language/1/"
        }
    }],
    "pokemon_species": [{
        "name": "bulbasaur",
        "url": "http://pokeapi.co/api/v2/pokemon-species/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class EggGroup extends NamedAPIResource {
    // The identifier for this egg group resource
    private int id;

    // The name of this egg group listed in different languages
    private List<Name> names;

    // A list of all Pokémon species that are members of this egg group
    private List<PokemonSpecies> pokemon_species;

    private static EggGroup get(String url) {
        String json = Information.fromInternet(url);
        EggGroup obj = new Gson().fromJson(json, EggGroup.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EggGroup getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/egg-group/" + id);
    }

    public static EggGroup getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/egg-group/" + name);
    }

    public int getId() {
        return id;
    }

    public EggGroup setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public EggGroup setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public EggGroup setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}