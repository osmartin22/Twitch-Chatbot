package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.utility.Language;

public class AwesomeName {
    // The localized "scientific" name for an API resource in a specific language
    private String awesome_name;

    // The language this "scientific" name is in
    private Language language;

    public String getAwesomeName() {
        return awesome_name;
    }

    public AwesomeName setAwesomeName(String awesome_name) {
        this.awesome_name = awesome_name;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public AwesomeName setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}