package poke_api_packages.poke_models.contests;
/*
{
    "id": 1,
    "appeal": 2,
    "flavor_text_entries": [{
        "flavor_text": "Enables the user to perform first in the next turn.",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "moves": [{
        "name": "agility",
        "url": "http://pokeapi.co/api/v2/move/97/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.APIResource;
import poke_api_packages.poke_models.common.FlavorText;
import poke_api_packages.poke_models.moves.Move;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class SuperContestEffect extends APIResource {
    // The identifier for this super contest effect resource
    private int id;

    // The level of appeal this super contest effect has
    private int appeal;

    // The flavor text of this super contest effect listed in different languages
    private List<FlavorText> flavor_text_entries;

    // A list of moves that have the effect when used in super contests
    private List<Move> moves;

    private static SuperContestEffect get(String url) {
        String json = Information.fromInternet(url);
        SuperContestEffect obj = new Gson().fromJson(json, SuperContestEffect.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static SuperContestEffect getById(int id) {
        return get("https://pokeapi.co/api/v2/super-contest-effect/" + id);
    }

    public int getId() {
        return id;
    }

    public SuperContestEffect setId(int id) {
        this.id = id;
        return this;
    }

    public int getAppeal() {
        return appeal;
    }

    public SuperContestEffect setAppeal(int appeal) {
        this.appeal = appeal;
        return this;
    }

    public List<FlavorText> getFlavorTextEntries() {
        return flavor_text_entries;
    }

    public SuperContestEffect setFlavorTextEntries(List<FlavorText> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public SuperContestEffect setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}