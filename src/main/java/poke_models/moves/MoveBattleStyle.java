package poke_models.moves;
/*
{
    "id": 1,
    "name": "attack",
    "names": [{
        "name": "Attack",
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

public class MoveBattleStyle extends NamedAPIResource {
    // The identifier for this move battle style resource
    private int id;

    // The name of this move battle style listed in different languages
    private List<Name> names;

    private static MoveBattleStyle get(String url) {
        String json = Information.fromInternet(url);
        MoveBattleStyle obj = new Gson().fromJson(json, MoveBattleStyle.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveBattleStyle getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/move-battle-style/" + id);
    }

    public static MoveBattleStyle getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/move-battle-style/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveBattleStyle setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public MoveBattleStyle setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}