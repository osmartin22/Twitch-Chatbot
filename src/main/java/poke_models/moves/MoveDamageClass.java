package poke_models.moves;
/*
{
    "id": 1,
    "name": "status",
    "descriptions": [{
        "description": "ãƒ€ãƒ¡ãƒ¼ã‚¸ãªã„",
        "language": {
            "name": "ja",
            "url": "http://pokeapi.co/api/v2/language/1/"
        }
    }],
    "moves": [{
        "name": "swords-dance",
        "url": "http://pokeapi.co/api/v2/move/14/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Description;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class MoveDamageClass extends NamedAPIResource {
    // The identifier for this move damage class resource
    private int id;

    // The description of this move damage class listed in different languages
    private List<Description> descriptions;

    // A list of moves that fall into this damage class
    private List<Move> moves;

    // The name of this move damage class listed in different languages
    private List<Name> names;

    private static MoveDamageClass get(String url) {
        String json = Information.fromInternet(url);
        MoveDamageClass obj = new Gson().fromJson(json, MoveDamageClass.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveDamageClass getById(int id) {
        return get("https://pokeapi.co/api/v2/move-damage-class/" + id);
    }

    public static MoveDamageClass getByName(String name) {
        return get("https://pokeapi.co/api/v2/move-damage-class/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveDamageClass setId(int id) {
        this.id = id;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public MoveDamageClass setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public MoveDamageClass setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public MoveDamageClass setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}