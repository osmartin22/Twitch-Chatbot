package poke_models.moves;

import com.google.gson.Gson;

import java.util.List;

public class ContestComboDetail {
    // A list of moves to use before this move
    private List<Move> use_before;

    // A list of moves to use after this move
    private List<Move> use_after;

    public List<Move> getUseBefore() {
        return use_before;
    }

    public ContestComboDetail setUseBefore(List<Move> use_before) {
        this.use_before = use_before;
        return this;
    }

    public List<Move> getUseAfter() {
        return use_after;
    }

    public ContestComboDetail setUseAfter(List<Move> use_after) {
        this.use_after = use_after;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}