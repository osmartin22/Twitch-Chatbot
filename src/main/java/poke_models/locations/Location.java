package poke_models.locations;
/*
{
    "id": 1,
    "name": "canalave-city",
    "region": {
        "name": "sinnoh",
        "url": "http://pokeapi.co/api/v2/region/4/"
    },
    "names": [{
        "name": "Canalave City",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "game_indices": [{
        "game_index": 7,
        "generation": {
            "name": "generation-iv",
            "url": "http://pokeapi.co/api/v2/generation/4/"
        }
    }],
    "areas": [{
        "name": "canalave-city-area",
        "url": "http://pokeapi.co/api/v2/location-area/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.GenerationGameIndex;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Location extends NamedAPIResource {
    // The identifier for this location resource
    private int id;

    // The region this location can be found in
    private Region region;

    // The name of this language listed in different languages
    private List<Name> names;

    // A list of game indices relevent to this location by generation
    private List<GenerationGameIndex> game_indices;

    // Areas that can be found within this location
    private List<LocationArea> areas;

    private static Location get(String url) {
        String json = Information.fromInternet(url);
        Location obj = new Gson().fromJson(json, Location.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Location getById(int id) {
        return get("https://pokeapi.co/api/v2/location/" + id);
    }

    public int getId() {
        return id;
    }

    public Location setId(int id) {
        this.id = id;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Location setRegion(Region region) {
        this.region = region;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Location setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<GenerationGameIndex> getGameIndices() {
        return game_indices;
    }

    public Location setGameIndices(List<GenerationGameIndex> game_indices) {
        this.game_indices = game_indices;
        return this;
    }

    public List<LocationArea> getAreas() {
        return areas;
    }

    public Location setAreas(List<LocationArea> areas) {
        this.areas = areas;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}