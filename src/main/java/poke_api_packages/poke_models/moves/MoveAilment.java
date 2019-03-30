package poke_api_packages.poke_models.moves;
/*
{
    "id": 1,
    "name": "paralysis",
    "moves": [{
        "name": "thunder-punch",
        "url": "http://pokeapi.co/api/v2/move/9/"
    }],
    "names": [{
        "name": "Paralysis",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class MoveAilment extends NamedAPIResource {
    // The identifier for this move ailment resource
    private int id;

    // A list of moves that cause this ailment
    private List<Move> moves;

    // The name of this move ailment listed in different languages
    private List<Name> names;

    private static MoveAilment get(String url) {
        String json = Information.fromInternet(url);
        MoveAilment obj = new Gson().fromJson(json, MoveAilment.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveAilment getById(int id) {
        return get("https://pokeapi.co/api/v2/move-ailment/" + id);
    }

    public static MoveAilment getByName(String name) {
        return get("https://pokeapi.co/api/v2/move-ailment/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveAilment setId(int id) {
        this.id = id;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public MoveAilment setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public MoveAilment setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}