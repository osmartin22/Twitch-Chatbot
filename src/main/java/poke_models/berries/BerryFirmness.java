package poke_models.berries;
/*
{
    "id": 1,
    "name": "very-soft",
    "berries": [{
        "name": "pecha",
        "url": "http://pokeapi.co/api/v2/berry/3/"
    }],
    "names": [{
        "name": "Very Soft",
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

public class BerryFirmness extends NamedAPIResource {
    // The identifier for this berry firmness resource
    private int id;

    // A list of the berries with this firmness
    private List<Berry> berries;

    // The name of this berry firmness listed in different languages
    private List<Name> names;

    private static BerryFirmness get(String url) {
        String json = Information.fromInternet(url);
        BerryFirmness obj = new Gson().fromJson(json, BerryFirmness.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static BerryFirmness getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/berry-firmness/" + id);
    }

    public static BerryFirmness getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/berry-firmness/" + name);
    }

    public int getId() {
        return id;
    }

    public BerryFirmness setId(int id) {
        this.id = id;
        return this;
    }

    public List<Berry> getBerries() {
        return berries;
    }

    public BerryFirmness setBerries(List<Berry> berries) {
        this.berries = berries;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public BerryFirmness setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}