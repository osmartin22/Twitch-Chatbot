package poke_api_packages.poke_models.common;

import com.google.gson.Gson;
import poke_api_packages.poke_models.games.VersionGroup;
import poke_api_packages.poke_models.utility.Language;

public class VersionGroupFlavorText {
    // The localized name for an API resource in a specific language
    private String text;

    // The language this name is in
    private Language language;

    // The version group which uses this flavor text
    private VersionGroup version_group;

    public String getText() {
        return text;
    }

    public VersionGroupFlavorText setText(String text) {
        this.text = text;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public VersionGroupFlavorText setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return version_group;
    }

    public VersionGroupFlavorText setVersionGroup(VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}