package poke_api_packages.poke_models.utility;
/*
{
    "id": 1,
    "name": "ja",
    "official": true,
    "iso639": "ja",
    "iso3166": "jp",
    "names": [{
        "name": "Japanese",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
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

public class Language extends NamedAPIResource {
    // The identifier for this language resource
    private int id;

    // Whether or not the games are published in this language
    private boolean official;

    // The two-letter code of the country where this language is spoken. Note that it is not unique.
    private String iso639;

    // The two-letter code of the language. Note that it is not unique.
    private String iso3166;

    // The name of this language listed in different languages
    private List<Name> names;

    private static Language get(String url) {
        String json = Information.fromInternet(url);
        Language obj = new Gson().fromJson(json, Language.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Language getById(int id) {
        return get("https://pokeapi.co/api/v2/language/" + id);
    }

    public static Language getByName(String name) {
        return get("https://pokeapi.co/api/v2/language/" + name);
    }

    public int getId() {
        return id;
    }

    public Language setId(int id) {
        this.id = id;
        return this;
    }

    public boolean official() {
        return official;
    }

    public Language setOfficial(boolean official) {
        this.official = official;
        return this;
    }

    public String getIso639() {
        return iso639;
    }

    public Language setIso639(String iso639) {
        this.iso639 = iso639;
        return this;
    }

    public String getIso3166() {
        return iso3166;
    }

    public Language setIso3166(String iso3166) {
        this.iso3166 = iso3166;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Language setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}