package poke_models.games;
/*
{
    "id": 1,
    "name": "red-blue",
    "order": 1,
    "generation": {
        "name": "generation-i",
        "url": "http://pokeapi.co/api/v2/generation/1/"
    },
    "move_learn_methods": [{
        "name": "level-up",
        "url": "http://pokeapi.co/api/v2/move-learn-method/1/"
    }],
    "pokedexes": [{
        "name": "kanto",
        "url": "http://pokeapi.co/api/v2/pokedex/2/"
    }],
    "regions": [{
        "name": "kanto",
        "url": "http://pokeapi.co/api/v2/region/1/"
    }],
    "versions": [{
        "name": "red",
        "url": "http://pokeapi.co/api/v2/version/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.NamedAPIResource;
import poke_models.locations.Region;
import poke_models.moves.MoveLearnMethod;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class VersionGroup extends NamedAPIResource {
    // The identifier for this version group resource
    private int id;

    // Order for sorting. Almost by date of release, except similar versions are grouped together.
    private int order;

    // The generation this version was introduced in
    private Generation generation;

    // A list of methods in which Pokémon can learn moves in this version group
    private List<MoveLearnMethod> move_learn_methods;

    // A list of Pokédexes introduces in this version group
    private List<Pokedex> pokedexes;

    // A list of regions that can be visited in this version group
    private List<Region> regions;

    // The versions this version group owns
    private List<Version> versions;

    private static VersionGroup get(String url) {
        String json = Information.fromInternet(url);
        VersionGroup obj = new Gson().fromJson(json, VersionGroup.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static VersionGroup getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/version-group/" + id);
    }

    public static VersionGroup getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/version-group/" + name);
    }

    public int getId() {
        return id;
    }

    public VersionGroup setId(int id) {
        this.id = id;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public VersionGroup setOrder(int order) {
        this.order = order;
        return this;
    }

    public Generation getGeneration() {
        return generation;
    }

    public VersionGroup setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    public List<MoveLearnMethod> getMoveLearnMethods() {
        return move_learn_methods;
    }

    public VersionGroup setMoveLearnMethods(List<MoveLearnMethod> move_learn_methods) {
        this.move_learn_methods = move_learn_methods;
        return this;
    }

    public List<Pokedex> getPokedexes() {
        return pokedexes;
    }

    public VersionGroup setPokedexes(List<Pokedex> pokedexes) {
        this.pokedexes = pokedexes;
        return this;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public VersionGroup setRegions(List<Region> regions) {
        this.regions = regions;
        return this;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public VersionGroup setVersions(List<Version> versions) {
        this.versions = versions;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}