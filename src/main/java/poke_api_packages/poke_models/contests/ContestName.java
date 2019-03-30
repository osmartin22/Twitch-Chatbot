package poke_api_packages.poke_models.contests;

import com.google.gson.Gson;
import poke_api_packages.poke_models.utility.Language;

public class ContestName {
    // The name for this contest
    private String name;

    // The color associated with this contest's name
    private String color;

    // The language that this name is in
    private Language language;

    public String getName() {
        return name;
    }

    public ContestName setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ContestName setColor(String color) {
        this.color = color;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public ContestName setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}