package poke_models.common;

import com.google.gson.Gson;
import poke_models.utility.Language;

public class FlavorText {
    // The localized flavor text for an API resource in a specific language
    private String flavor_text;

    // The language this name is in
    private Language language;

    public String getFlavorText() {
        return flavor_text;
    }

    public FlavorText setFlavorText(String flavor_text) {
        this.flavor_text = flavor_text;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public FlavorText setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}