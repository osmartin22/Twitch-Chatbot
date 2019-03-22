package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;

public class PokeTrainerSide {
    private final PokeSide side;
    private final TrainerInBattle trainerInBattle;

    private PokeMove lastUsedMove;

    public PokeTrainerSide(@Nonnull TrainerInBattle trainerInBattle, @Nonnull PokeSide side) {
        this.trainerInBattle = trainerInBattle;
        this.side = side;
        this.lastUsedMove = null;
    }

    @Nonnull
    public PokeSide getSide() {
        return side;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int fieldPosition) {
        return trainerInBattle.getPokeInBattle(fieldPosition);
    }

    @Nonnull
    public TrainerChoice getCurrStatus() {
        return trainerInBattle.getCurrStatus();
    }

    public void doChoice(int fieldPosition) {
        trainerInBattle.doChoice(fieldPosition);
    }

    public void setMoveToUse(int movePosition, int fieldPosition) {
        trainerInBattle.setMoveToUse(movePosition, fieldPosition);
    }

    public boolean setPokeToSwitchIn(int pokePosition) {
        return trainerInBattle.setPokeToSwitchIn(pokePosition);
    }

    public boolean isAbleToDoMove(int movePosition, int fieldPosition) {
        return trainerInBattle.isAbleToDoMove(movePosition, fieldPosition);
    }

    public boolean isAbleToSwitchPoke(int fieldPosition) {
        return trainerInBattle.isAbleToSwitchPoke(fieldPosition);
    }

    public void doNonVolatileStatusEffect() {

    }
}
