package poke_models.locations;
/*
{
    "id": 1,
    "name": "forest",
    "names": [{
        "name": "Forest",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "pokemon_encounters": [{
        "base_score": 30,
        "rate": 50,
        "pokemon_species": {
            "name": "caterpie",
            "url": "http://pokeapi.co/api/v2/pokemon-species/10/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class PalParkArea extends NamedAPIResource {
    // The identifier for this pal park area resource
    private int id;

    // The name of this pal park area listed in different languages
    private List<Name> names;

    // A list of Pokémon encountered in thi pal park area along with details
    private List<PalParkEncounterSpecies> pokemon_encounters;

    private static PalParkArea get(String url) {
        String json = Information.fromInternet(url);
        PalParkArea obj = new Gson().fromJson(json, PalParkArea.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static PalParkArea getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/pal-park-area/" + id);
    }

    public static PalParkArea getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/pal-park-area/" + name);
    }

    public int getId() {
        return id;
    }

    public PalParkArea setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public PalParkArea setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PalParkEncounterSpecies> getPokemonEncounters() {
        return pokemon_encounters;
    }

    public PalParkArea setPokemonEncounters(List<PalParkEncounterSpecies> pokemon_encounters) {
        this.pokemon_encounters = pokemon_encounters;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}