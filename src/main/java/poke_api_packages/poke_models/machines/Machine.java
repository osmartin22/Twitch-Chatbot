package poke_api_packages.poke_models.machines;
/*
{
    "id": 1,
    "item": {
        "name": "tm01",
        "url": "http://localhost:8000/api/v2/item/305/"
    },
    "move": {
        "name": "mega-punch",
        "url": "http://localhost:8000/api/v2/move/5/"
    },
    "version_group": {
        "name": "red-blue",
        "url": "http://localhost:8000/api/v2/version/1/"
    }
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.APIResource;
import poke_api_packages.poke_models.games.VersionGroup;
import poke_api_packages.poke_models.items.Item;
import poke_api_packages.poke_models.moves.Move;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

public class Machine extends APIResource {
    // The identifier for this machine resource
    private int id;

    // The TM or HM item that corresponds to this machine
    private Item item;

    // The move that is taught by this machine
    private Move move;

    // The version group that this machine applies to
    private VersionGroup version_group;

    private static Machine get(String url) {
        String json = Information.fromInternet(url);
        Machine obj = new Gson().fromJson(json, Machine.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Machine getById(int id) {
        return get("https://pokeapi.co/api/v2/machine/" + id);
    }

    public int getId() {
        return id;
    }

    public Machine setId(int id) {
        this.id = id;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Machine setItem(Item item) {
        this.item = item;
        return this;
    }

    public Move getMove() {
        return move;
    }

    public Machine setMove(Move move) {
        this.move = move;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return version_group;
    }

    public Machine setVersionGroup(VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}