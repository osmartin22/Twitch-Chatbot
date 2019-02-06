package poke_models.encounters;
/*
{
    "id": 1,
    "name": "swarm",
    "values": [{
        "name": "swarm-yes",
        "url": "http://pokeapi.co/api/v2/encounter-condition-value/1/"
    }, {
        "name": "swarm-no",
        "url": "http://pokeapi.co/api/v2/encounter-condition-value/2/"
    }],
    "names": [{
        "name": "Schwarm",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
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

public class EncounterCondition extends NamedAPIResource {
    // The identifier for this encounter condition resource
    private int id;

    // The name of this encounter method listed in different languages
    private List<Name> names;

    // A list of possible values for this encounter condition
    private List<EncounterConditionValue> values;

    private static EncounterCondition get(String url) {
        String json = Information.fromInternet(url);
        EncounterCondition obj = new Gson().fromJson(json, EncounterCondition.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EncounterCondition getById(int id) {
        return get("https://pokeapi.co/api/v2/encounter-condition/" + id);
    }

    public static EncounterCondition getByName(String name) {
        return get("https://pokeapi.co/api/v2/encounter-condition/" + name);
    }

    public int getId() {
        return id;
    }

    public EncounterCondition setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public EncounterCondition setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<EncounterConditionValue> getValues() {
        return values;
    }

    public EncounterCondition setValues(List<EncounterConditionValue> values) {
        this.values = values;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}