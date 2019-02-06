package models.pokemon;

public class AbilityEffectChange {
    // The previous effect of this ability listed in different languages
    private java.util.ArrayList<models.common.Effect> effect_entries;

    // The version group in which the previous effect of this ability originated
    private models.games.VersionGroup version_group;

    public java.util.ArrayList<models.common.Effect> getEffectEntries() {
        return effect_entries;
    }

    public AbilityEffectChange setEffectEntries(java.util.ArrayList<models.common.Effect> effect_entries) {
        this.effect_entries = effect_entries;
        return this;
    }

    public models.games.VersionGroup getVersionGroup() {
        return version_group;
    }

    public AbilityEffectChange setVersionGroup(models.games.VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}