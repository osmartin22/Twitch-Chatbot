package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.common.Effect;
import poke_api_packages.poke_models.games.VersionGroup;

import java.util.List;

public class AbilityEffectChange {
    // The previous effect of this ability listed in different languages
    private List<Effect> effect_entries;

    // The version group in which the previous effect of this ability originated
    private VersionGroup version_group;

    public List<Effect> getEffectEntries() {
        return effect_entries;
    }

    public AbilityEffectChange setEffectEntries(List<Effect> effect_entries) {
        this.effect_entries = effect_entries;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return version_group;
    }

    public AbilityEffectChange setVersionGroup(VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}