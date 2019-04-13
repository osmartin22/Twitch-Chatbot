package ozmar.pokemonBattle.pokemonBattleHelpers;

import java.util.Objects;

public class PokePosition {

    private final int sidePosition;
    private final int trainerPosition;
    private final int fieldPosition;

    public PokePosition(int sidePosition, int trainerPosition, int fieldPosition) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PokePosition)) {
            return false;
        }

        PokePosition pokePosition = (PokePosition) obj;

        return sidePosition == pokePosition.sidePosition &&
                trainerPosition == pokePosition.trainerPosition &&
                fieldPosition == pokePosition.fieldPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sidePosition, trainerPosition, fieldPosition);
    }
}
