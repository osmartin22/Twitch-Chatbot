package poke_api_packages.poke_models.common;

import com.google.gson.Gson;
import poke_api_packages.poke_models.utility.Language;

public class Effect {
    // The localized effect text for an API resource in a specific language
    private String effect;

    // The language this effect is in
    private Language language;

    public String getEffect() {
        return effect;
    }

    public Effect setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Effect setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}