package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;

public class NaturePokeathlonStatAffect {
    // The maximum amount of change to the referenced Pokéathlon stat
    private int max_change;

    // The nature causing the change
    private Nature nature;

    public int getMaxChange() {
        return max_change;
    }

    public NaturePokeathlonStatAffect setMaxChange(int max_change) {
        this.max_change = max_change;
        return this;
    }

    public Nature getNature() {
        return nature;
    }

    public NaturePokeathlonStatAffect setNature(Nature nature) {
        this.nature = nature;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}