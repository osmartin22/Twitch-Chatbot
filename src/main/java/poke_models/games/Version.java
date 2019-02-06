package poke_models.games;
/*
{
    "id": 1,
    "name": "red",
    "names": [{
        "name": "Rot",
        "language": {
            "name": "de",
            "url": "http://pokeapi.co/api/v2/language/6/"
        }
    }],
    "version_group": {
        "name": "red-blue",
        "url": "http://pokeapi.co/api/v2/version-group/1/"
    }
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Version extends NamedAPIResource {
    // The identifier for this version resource
    private int id;

    // The name of this version listed in different languages
    private List<Name> names;

    // The version group this version belongs to
    private VersionGroup version_group;

    private static Version get(String url) {
        String json = Information.fromInternet(url);
        Version obj = new Gson().fromJson(json, Version.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Version getById(int id) {
        return get("https://pokeapi.co/api/v2/version/" + id);
    }

    public static Version getByName(String name) {
        return get("https://pokeapi.co/api/v2/version/" + name);
    }

    public int getId() {
        return id;
    }

    public Version setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Version setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return version_group;
    }

    public Version setVersionGroup(VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}