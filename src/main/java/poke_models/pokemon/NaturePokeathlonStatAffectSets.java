package poke_models.pokemon;

import com.google.gson.Gson;

import java.util.List;

public class NaturePokeathlonStatAffectSets {
    // A list of natures and how they change the referenced Pokéathlon stat
    private List<NaturePokeathlonStatAffect> increase;

    // A list of natures and how they change the referenced Pokéathlon stat
    private List<NaturePokeathlonStatAffect> decrease;

    public List<NaturePokeathlonStatAffect> getIncrease() {
        return increase;
    }

    public NaturePokeathlonStatAffectSets setIncrease(List<NaturePokeathlonStatAffect> increase) {
        this.increase = increase;
        return this;
    }

    public List<NaturePokeathlonStatAffect> getDecrease() {
        return decrease;
    }

    public NaturePokeathlonStatAffectSets setDecrease(List<NaturePokeathlonStatAffect> decrease) {
        this.decrease = decrease;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}