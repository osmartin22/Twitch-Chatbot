package poke_api_packages.poke_models.games;
/*
{
    "id": 1,
    "name": "generation-i",
    "abilities": [],
    "main_region": {
        "name": "kanto",
        "url": "http://pokeapi.co/api/v2/region/1/"
    },
    "moves": [{
        "name": "pound",
        "url": "http://pokeapi.co/api/v2/move/1/"
    }],
    "names": [{
        "name": "Generation I",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "pokemon_species": [{
        "name": "bulbasaur",
        "url": "http://pokeapi.co/api/v2/pokemon-species/1/"
    }],
    "types": [{
        "name": "normal",
        "url": "http://pokeapi.co/api/v2/type/1/"
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
import poke_api_packages.poke_models.locations.Region;
import poke_api_packages.poke_models.moves.Move;
import poke_api_packages.poke_models.pokemon.Ability;
import poke_api_packages.poke_models.pokemon.PokemonSpecies;
import poke_api_packages.poke_models.pokemon.Type;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Generation extends NamedAPIResource {
    // The identifier for this generation resource
    private int id;

    // A list of abilities that were introduced in this generation
    private List<Ability> abilities;

    // The name of this generation listed in different languages
    private List<Name> names;

    // The main region travelled in this generation
    private Region main_region;

    // A list of moves that were introduced in this generation
    private List<Move> moves;

    // A list of Pokémon species that were introduced in this generation
    private List<PokemonSpecies> pokemon_species;

    // A list of types that were introduced in this generation
    private List<Type> types;

    // A list of version groups that were introduced in this generation
    private List<VersionGroup> version_groups;

    private static Generation get(String url) {
        String json = Information.fromInternet(url);
        Generation obj = new Gson().fromJson(json, Generation.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Generation getById(int id) {
        return get("https://pokeapi.co/api/v2/generation/" + id);
    }

    public static Generation getByName(String name) {
        return get("https://pokeapi.co/api/v2/generation/" + name);
    }

    public int getId() {
        return id;
    }

    public Generation setId(int id) {
        this.id = id;
        return this;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public Generation setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Generation setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public Region getMainRegion() {
        return main_region;
    }

    public Generation setMainRegion(Region main_region) {
        this.main_region = main_region;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Generation setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public Generation setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    public List<Type> getTypes() {
        return types;
    }

    public Generation setTypes(List<Type> types) {
        this.types = types;
        return this;
    }

    public List<VersionGroup> getVersionGroups() {
        return version_groups;
    }

    public Generation setVersionGroups(List<VersionGroup> version_groups) {
        this.version_groups = version_groups;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}