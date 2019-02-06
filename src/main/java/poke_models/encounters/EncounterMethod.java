package poke_models.encounters;
/*
{
    "id": 1,
    "name": "walk",
    "order": 1,
    "names": [{
        "name": "Walking in tall grass or a cave",
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

public class EncounterMethod extends NamedAPIResource {
    // The identifier for this encounter method resource
    private int id;

    // A good value for sorting
    private int order;

    // The name of this encounter method listed in different languages
    private List<Name> names;

    private static EncounterMethod get(String url) {
        String json = Information.fromInternet(url);
        EncounterMethod obj = new Gson().fromJson(json, EncounterMethod.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EncounterMethod getById(int id) {
        return get("https://pokeapi.co/api/v2/encounter-method/" + id);
    }

    public static EncounterMethod getByName(String name) {
        return get("https://pokeapi.co/api/v2/encounter-method/" + name);
    }

    public int getId() {
        return id;
    }

    public EncounterMethod setId(int id) {
        this.id = id;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public EncounterMethod setOrder(int order) {
        this.order = order;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public EncounterMethod setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}