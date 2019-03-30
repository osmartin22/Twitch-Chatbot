package poke_api_packages.poke_models.moves;
/*
{
    "id": 1,
    "name": "level-up",
    "names": [{
        "name": "Level up",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "descriptions": [{
        "description": "Wird gelernt, wenn ein Pokémon ein bestimmtes Level erreicht.",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "version_groups": [{
        "name": "red-blue",
        "url": "http://pokeapi.co/api/v2/version-group/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Description;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.games.VersionGroup;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class MoveLearnMethod extends NamedAPIResource {
    // The identifier for this move learn method resource
    private int id;

    // The description of this move learn method listed in different languages
    private List<Description> descriptions;

    // The name of this move learn method listed in different languages
    private List<Name> names;

    // A list of version groups where moves can be learned through this method
    private List<VersionGroup> version_groups;

    private static MoveLearnMethod get(String url) {
        String json = Information.fromInternet(url);
        MoveLearnMethod obj = new Gson().fromJson(json, MoveLearnMethod.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static MoveLearnMethod getById(int id) {
        return get("https://pokeapi.co/api/v2/move-learn-method/" + id);
    }

    public static MoveLearnMethod getByName(String name) {
        return get("https://pokeapi.co/api/v2/move-learn-method/" + name);
    }

    public int getId() {
        return id;
    }

    public MoveLearnMethod setId(int id) {
        this.id = id;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public MoveLearnMethod setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public MoveLearnMethod setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<VersionGroup> getVersionGroups() {
        return version_groups;
    }

    public MoveLearnMethod setVersionGroups(List<VersionGroup> version_groups) {
        this.version_groups = version_groups;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}