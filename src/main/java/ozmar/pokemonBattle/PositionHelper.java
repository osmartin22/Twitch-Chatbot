package ozmar.pokemonBattle;

public class PositionHelper {
    private int trainer;
    private int field;
    private int poke;
    private int move;

    public PositionHelper() {
        this.trainer = 0;
        this.field = 0;
        this.poke = 0;
        this.move = 0;
    }

    public PositionHelper(int trainer, int field, int poke, int move) {
        this.trainer = trainer;
        this.field = field;
        this.poke = poke;
        this.move = move;
    }

    public int getTrainer() {
        return trainer;
    }

    public void setTrainer(int trainer) {
        this.trainer = trainer;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getPoke() {
        return poke;
    }

    public void setPoke(int poke) {
        this.poke = poke;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
