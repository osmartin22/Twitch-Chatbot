package poke_models.contests;
/*
{
    "id": 1,
    "name": "cool",
    "berry_flavor": {
        "name": "spicy",
        "url": "http://pokeapi.co/api/v2/berry-flavor/1/"
    },
    "names": [{
        "name": "Cool",
        "color": "Red",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.berries.BerryFlavor;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class ContestType extends NamedAPIResource {
    // The identifier for this contest type resource
    private int id;

    // The berry flavor that correlates with this contest type
    private BerryFlavor berry_flavor;

    // The name of this contest type listed in different languages
    private List<ContestName> names;

    private static ContestType get(String url) {
        String json = Information.fromInternet(url);
        ContestType obj = new Gson().fromJson(json, ContestType.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static ContestType getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/contest-type/" + id);
    }

    public static ContestType getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/contest-type/" + name);
    }

    public int getId() {
        return id;
    }

    public ContestType setId(int id) {
        this.id = id;
        return this;
    }

    public BerryFlavor getBerryFlavor() {
        return berry_flavor;
    }

    public ContestType setBerryFlavor(BerryFlavor berry_flavor) {
        this.berry_flavor = berry_flavor;
        return this;
    }

    public List<ContestName> getNames() {
        return names;
    }

    public ContestType setNames(List<ContestName> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}