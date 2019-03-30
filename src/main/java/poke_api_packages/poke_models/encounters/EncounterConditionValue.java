package poke_api_packages.poke_models.encounters;
/*
{
    "id": 1,
    "name": "swarm-yes",
    "condition": {
        "name": "swarm",
        "url": "http://pokeapi.co/api/v2/encounter-condition/1/"
    },
    "names": [{
        "name": "WÃ¤hrend eines Schwarms",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class EncounterConditionValue extends NamedAPIResource {
    // The identifier for this encounter condition value resource
    private int id;

    // The condition this encounter condition value pertains to
    private List<EncounterCondition> condition;

    // The name of this encounter condition value listed in different languages
    private List<Name> names;

    private static EncounterConditionValue get(String url) {
        String json = Information.fromInternet(url);
        EncounterConditionValue obj = new Gson().fromJson(json, EncounterConditionValue.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EncounterConditionValue getById(int id) {
        return get("https://pokeapi.co/api/v2/encounter-condition-value/" + id);
    }

    public static EncounterConditionValue getByName(String name) {
        return get("https://pokeapi.co/api/v2/encounter-condition-value/" + name);
    }

    public int getId() {
        return id;
    }

    public EncounterConditionValue setId(int id) {
        this.id = id;
        return this;
    }

    public List<EncounterCondition> getCondition() {
        return condition;
    }

    public EncounterConditionValue setCondition(List<EncounterCondition> condition) {
        this.condition = condition;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public EncounterConditionValue setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}