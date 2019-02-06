package models.pokemon;

public class MoveStatAffect {
    // The maximum amount of change to the referenced stat
    private int change;

    // The move causing the change
    private models.moves.Move move;

    public int getChange() {
        return change;
    }

    public MoveStatAffect setChange(int change) {
        this.change = change;
        return this;
    }

    public models.moves.Move getMove() {
        return move;
    }

    public MoveStatAffect setMove(models.moves.Move move) {
        this.move = move;
        return this;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}