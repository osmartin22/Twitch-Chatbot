package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;

import java.util.List;

public class MoveStatAffectSets {
    // A list of moves and how they change the referenced stat
    private List<MoveStatAffect> increase;

    // A list of moves and how they change the referenced stat
    private List<MoveStatAffect> decrease;

    public List<MoveStatAffect> getIncrease() {
        return increase;
    }

    public MoveStatAffectSets setIncrease(List<MoveStatAffect> increase) {
        this.increase = increase;
        return this;
    }

    public List<MoveStatAffect> getDecrease() {
        return decrease;
    }

    public MoveStatAffectSets setDecrease(List<MoveStatAffect> decrease) {
        this.decrease = decrease;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}