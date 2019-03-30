package poke_api_packages.poke_models.moves;
/*
{
    "id": 1,
    "name": "ailment",
    "descriptions": [{
        "description": "No damage; inflicts status ailment",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "moves": [{
        "name": "sing",
        "url": "http://pokeapi.co/api/v2/move/47/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Description;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class MoveCategory {
    // The identifier for this move category resource
    private int id;

    // The name for this move category resource
    private String name;

    // A list of moves that fall into this category
    private List<Move> moves;

    // The description of this move ailment listed in different languages
    private List<Description> descriptions;

    private static MoveCategory get(String url) {
        String json = Information.fromInternet(url);
        MoveCategory obj = new Gson().fromJson(json, MoveCategory.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveCategory getById(int id) {
        return get("https://pokeapi.co/api/v2/move-category/" + id);
    }

    public static MoveCategory getByName(String name) {
        return get("https://pokeapi.co/api/v2/move-category/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveCategory setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MoveCategory setName(String name) {
        this.name = name;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public MoveCategory setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public MoveCategory setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}