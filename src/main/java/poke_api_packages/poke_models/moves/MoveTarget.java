package poke_api_packages.poke_models.moves;
/*
{
    "id": 1,
    "name": "specific-move",
    "descriptions": [{
        "description": "Eine spezifische Fähigkeit. Wie diese Fähigkeit genutzt wird, hängt von den genutzten Fähigkeiten ab.",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "moves": [{
        "name": "counter",
        "url": "http://pokeapi.co/api/v2/move/68/"
    }],
    "names": [{
        "name": "Spezifische Fähigkeit",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Description;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class MoveTarget extends NamedAPIResource {
    // The identifier for this move target resource
    private int id;

    // The description of this move target listed in different languages
    private List<Description> descriptions;

    // A list of moves that that are directed at this target
    private List<Move> moves;

    // The name of this move target listed in different languages
    private List<Name> names;

    private static MoveTarget get(String url) {
        String json = Information.fromInternet(url);
        MoveTarget obj = new Gson().fromJson(json, MoveTarget.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveTarget getById(int id) {
        return get("https://pokeapi.co/api/v2/move-target/" + id);
    }

    public static MoveTarget getByName(String name) {
        return get("https://pokeapi.co/api/v2/move-target/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveTarget setId(int id) {
        this.id = id;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public MoveTarget setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public MoveTarget setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public MoveTarget setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}