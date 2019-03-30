package poke_api_packages.poke_models.locations;
/*
{
    "id": 1,
    "name": "kanto",
    "locations": [{
        "name": "celadon-city",
        "url": "http://pokeapi.co/api/v2/location/67/"
    }],
    "main_generation": {
        "name": "generation-i",
        "url": "http://pokeapi.co/api/v2/generation/1/"
    },
    "names": [{
        "name": "Kanto",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "pokedexes": [{
        "name": "kanto",
        "url": "http://pokeapi.co/api/v2/pokedex/2/"
    }],
    "version_groups": [{
        "name": "red-blue",
        "url": "http://pokeapi.co/api/v2/version-group/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.games.Generation;
import poke_api_packages.poke_models.games.Pokedex;
import poke_api_packages.poke_models.games.VersionGroup;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Region extends NamedAPIResource {
    // The identifier for this region resource
    private int id;

    // A list of locations that can be found in this region
    private List<Location> locations;

    // The generation this region was introduced in
    private Generation main_generation;

    // The name of this region listed in different languages
    private List<Name> names;

    // A list of pokédexes that catalogue Pokémon in this region
    private List<Pokedex> pokedexes;

    // A list of version groups where this region can be visited
    private List<VersionGroup> version_groups;

    private static Region get(String url) {
        String json = Information.fromInternet(url);
        Region obj = new Gson().fromJson(json, Region.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Region getById(int id) {
        return get("https://pokeapi.co/api/v2/region/" + id);
    }

    public static Region getByName(String name) {
        return get("https://pokeapi.co/api/v2/region/" + name);
    }

    public int getId() {
        return id;
    }

    public Region setId(int id) {
        this.id = id;
        return this;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Region setLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    public Generation getMainGeneration() {
        return main_generation;
    }

    public Region setMainGeneration(Generation main_generation) {
        this.main_generation = main_generation;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Region setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<Pokedex> getPokedexes() {
        return pokedexes;
    }

    public Region setPokedexes(List<Pokedex> pokedexes) {
        this.pokedexes = pokedexes;
        return this;
    }

    public List<VersionGroup> getVersionGroups() {
        return version_groups;
    }

    public Region setVersionGroups(List<VersionGroup> version_groups) {
        this.version_groups = version_groups;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}