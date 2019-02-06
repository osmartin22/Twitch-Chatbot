package poke_models.games;
/*
{
    "id": 2,
    "name": "kanto",
    "is_main_series": true,
    "descriptions": [{
        "description": "Rot/Blau/Gelb Kanto Dex",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "names": [{
        "name": "Kanto",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "pokemon_entries": [{
        "entry_number": 1,
        "pokemon_species": {
            "name": "bulbasaur",
            "url": "http://pokeapi.co/api/v2/pokemon-species/1/"
        }
    }],
    "region": {
        "name": "kanto",
        "url": "http://pokeapi.co/api/v2/region/1/"
    },
    "version_groups": [{
        "name": "red-blue",
        "url": "http://pokeapi.co/api/v2/version-group/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Description;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.locations.Region;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Pokedex extends NamedAPIResource {
    // The identifier for this Pokédex resource
    private int id;

    // Whether or not this Pokédex originated in the main series of the video games
    private boolean is_main_series;

    // The description of this Pokédex listed in different languages
    private List<Description> descriptions;

    // The name of this Pokédex listed in different languages
    private List<Name> names;

    // A list of Pokémon catalogued in this Pokédex and their indexes
    private List<PokemonEntry> pokemon_entries;

    // The region this Pokédex catalogues Pokémon for
    private Region region;

    // A list of version groups this Pokédex is relevant to
    private List<VersionGroup> version_groups;

    private static Pokedex get(String url) {
        String json = Information.fromInternet(url);
        Pokedex obj = new Gson().fromJson(json, Pokedex.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Pokedex getById(int id) {
        return get("https://pokeapi.co/api/v2/pokedex/" + id);
    }

    public static Pokedex getByName(String name) {
        return get("https://pokeapi.co/api/v2/pokedex/" + name);
    }

    public int getId() {
        return id;
    }

    public Pokedex setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isMainSeries() {
        return is_main_series;
    }

    public Pokedex setIsMainSeries(boolean is_main_series) {
        this.is_main_series = is_main_series;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public Pokedex setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Pokedex setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonEntry> getPokemonEntries() {
        return pokemon_entries;
    }

    public Pokedex setPokemonEntries(List<PokemonEntry> pokemon_entries) {
        this.pokemon_entries = pokemon_entries;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Pokedex setRegion(Region region) {
        this.region = region;
        return this;
    }

    public List<VersionGroup> getVersionGroups() {
        return version_groups;
    }

    public Pokedex setVersionGroups(List<VersionGroup> version_groups) {
        this.version_groups = version_groups;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}