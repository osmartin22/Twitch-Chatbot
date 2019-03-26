package ozmar.pokemonBattle.pokemonBattleHelpers;

public class PokeTargetPosition {

    private final int sidePosition;
    private final int trainerPosition;
    private final int fieldPosition;

    public PokeTargetPosition(int sidePosition, int trainerPosition, int fieldPosition) {
        this.sidePosition = sidePosition;
        this.trainerPosition = trainerPosition;
        this.fieldPosition = fieldPosition;
    }

    public int getSidePosition() {
        return sidePosition;
    }

    public int getTrainerPosition() {
        return trainerPosition;
    }

    public int getFieldPosition() {
        return fieldPosition;
    }
}
