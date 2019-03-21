package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemon.Poke;
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
    public Poke getCurrPoke() {
        return trainerInBattle.getCurrPoke();
    }

    @Nonnull
    public TrainerChoice getCurrStatus() {
        return trainerInBattle.getCurrStatus();
    }

    public void doChoice() {
        trainerInBattle.doChoice();
    }

    public void setMoveToUse(int movePosition) {
        trainerInBattle.setMoveToUse(movePosition);
    }

    public boolean setPokeToSwitchIn(int pokePosition) {
        return trainerInBattle.setPokeToSwitchIn(pokePosition);
    }

    public boolean isAbleToDoMove(int movePosition) {
        return trainerInBattle.isAbleToDoMove(movePosition);
    }

    public boolean isAbleToSwitchPoke() {
        return trainerInBattle.isAbleToSwitchPoke();
    }
}
