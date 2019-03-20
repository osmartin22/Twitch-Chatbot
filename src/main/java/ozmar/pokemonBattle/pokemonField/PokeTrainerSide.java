package ozmar.pokemonBattle.pokemonField;

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
    public TrainerInBattle getTrainerInBattle() {
        return trainerInBattle;
    }

    @Nonnull
    public PokeSide getSide() {
        return side;
    }

    @Nonnull
    public Poke getCurrPoke() {
        return trainerInBattle.getCurrPoke();
    }

    public void doChoice() {
        trainerInBattle.doChoice();
    }

    public void setPokeToSwitchIn(@Nonnull Poke pokeToSwitchIn) {
        trainerInBattle.setPokeToSwitchIn(pokeToSwitchIn);
    }
}
