package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.utility.Language;

public class Genus {
    // The localized genus for the referenced Pokémon species
    private String genus;

    // The language this genus is in
    private Language language;

    public String getGenus() {
        return genus;
    }

    public Genus setGenus(String genus) {
        this.genus = genus;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Genus setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}