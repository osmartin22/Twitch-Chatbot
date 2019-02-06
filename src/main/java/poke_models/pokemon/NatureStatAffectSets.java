package poke_models.pokemon;

import com.google.gson.Gson;

import java.util.List;

public class NatureStatAffectSets {
    // A list of natures and how they change the referenced stat
    private List<Nature> increase;

    // A list of nature sand how they change the referenced stat
    private List<Nature> decrease;

    public List<Nature> getIncrease() {
        return increase;
    }

    public NatureStatAffectSets setIncrease(List<Nature> increase) {
        this.increase = increase;
        return this;
    }

    public List<Nature> getDecrease() {
        return decrease;
    }

    public NatureStatAffectSets setDecrease(List<Nature> decrease) {
        this.decrease = decrease;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}