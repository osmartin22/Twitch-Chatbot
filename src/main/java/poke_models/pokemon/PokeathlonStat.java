package poke_models.pokemon;
/*
{
    "id": 1,
    "name": "speed",
    "affecting_natures": {
        "increase": [{
            "max_change": 2,
            "nature": {
                "name": "timid",
                "url": "http://pokeapi.co/api/v2/nature/5/"
            }
        }],
        "decrease": [{
            "max_change": -1,
            "nature": {
                "name": "hardy",
                "url": "http://pokeapi.co/api/v2/nature/1/"
            }
        }]
    },
    "names": [{
        "name": "Speed",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
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

public class PokeathlonStat extends NamedAPIResource {
    // The identifier for this Pokéathlon stat resource
    private int id;

    // The name of this Pokéathlon stat listed in different languages
    private List<Name> names;

    // A detail of natures which affect this Pokéathlon stat positively or negatively
    private NaturePokeathlonStatAffectSets affecting_natures;

    private static PokeathlonStat get(String url) {
        String json = Information.fromInternet(url);
        PokeathlonStat obj = new Gson().fromJson(json, PokeathlonStat.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static PokeathlonStat getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/pokeathlon-stat/" + id);
    }

    public static PokeathlonStat getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/pokeathlon-stat/" + name);
    }

    public int getId() {
        return id;
    }

    public PokeathlonStat setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public PokeathlonStat setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public NaturePokeathlonStatAffectSets getAffectingNatures() {
        return affecting_natures;
    }

    public PokeathlonStat setAffectingNatures(NaturePokeathlonStatAffectSets affecting_natures) {
        this.affecting_natures = affecting_natures;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}