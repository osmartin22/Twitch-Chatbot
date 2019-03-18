package ozmar.PokemonBattle.PokemonField;

import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonTrainer.TrainerInBattle;

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

    public TrainerInBattle getTrainerInBattle() {
        return trainerInBattle;
    }

    public PokeSide getSide() {
        return side;
    }
}
