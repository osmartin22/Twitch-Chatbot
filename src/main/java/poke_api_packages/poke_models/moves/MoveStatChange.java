package poke_api_packages.poke_models.moves;

import com.google.gson.Gson;
import poke_api_packages.poke_models.pokemon.Stat;

public class MoveStatChange {
    // The amount of change
    private int change;

    // The stat being affected
    private Stat stat;

    public int getChange() {
        return change;
    }

    public MoveStatChange setChange(int change) {
        this.change = change;
        return this;
    }

    public Stat getStat() {
        return stat;
    }

    public MoveStatChange setStat(Stat stat) {
        this.stat = stat;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}