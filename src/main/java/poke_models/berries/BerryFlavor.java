package poke_models.berries;
/*
{
    "id": 1,
    "name": "spicy",
    "berries": [{
        "potency": 10,
        "berry": {
            "name": "rowap",
            "url": "http://pokeapi.co/api/v2/berry/64/"
        }
    }],
    "contest_type": {
        "name": "cool",
        "url": "http://pokeapi.co/api/v2/contest-type/1/"
    },
    "names": [{
        "name": "Spicy",
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
import poke_models.contests.ContestType;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class BerryFlavor extends NamedAPIResource {
    // The identifier for this berry flavor resource
    private int id;

    // A list of the berries with this flavor
    private List<FlavorBerryMap> berries;

    // The contest type that correlates with this berry flavor
    private ContestType contest_type;

    // The name of this berry flavor listed in different languages
    private List<Name> names;

    private static BerryFlavor get(String url) {
        String json = Information.fromInternet(url);
        BerryFlavor obj = new Gson().fromJson(json, BerryFlavor.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static BerryFlavor getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/berry-flavor/" + id);
    }

    public static BerryFlavor getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/berry-flavor/" + name);
    }

    public int getId() {
        return id;
    }

    public BerryFlavor setId(int id) {
        this.id = id;
        return this;
    }

    public List<FlavorBerryMap> getBerries() {
        return berries;
    }

    public BerryFlavor setBerries(List<FlavorBerryMap> berries) {
        this.berries = berries;
        return this;
    }

    public ContestType getContestType() {
        return contest_type;
    }

    public BerryFlavor setContestType(ContestType contest_type) {
        this.contest_type = contest_type;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public BerryFlavor setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}