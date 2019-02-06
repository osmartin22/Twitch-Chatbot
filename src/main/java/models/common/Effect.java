package models.common;

public class Effect {
    // The localized effect text for an API resource in a specific language
    private String effect;

    // The language this effect is in
    private models.utility.Language language;

    public String getEffect() {
        return effect;
    }

    public Effect setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public models.utility.Language getLanguage() {
        return language;
    }

    public Effect setLanguage(models.utility.Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}