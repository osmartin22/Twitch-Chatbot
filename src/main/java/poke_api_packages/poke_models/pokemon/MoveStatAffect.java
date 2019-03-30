package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.moves.Move;

public class MoveStatAffect {
    // The maximum amount of change to the referenced stat
    private int change;

    // The move causing the change
    private Move move;

    public int getChange() {
        return change;
    }

    public MoveStatAffect setChange(int change) {
        this.change = change;
        return this;
    }

    public Move getMove() {
        return move;
    }

    public MoveStatAffect setMove(Move move) {
        this.move = move;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}