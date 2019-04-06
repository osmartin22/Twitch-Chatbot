package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.List;

public class PokeTrainerSide {
    private final PokeSide side;
    private final List<TrainerInBattle> trainerInBattleList;

    public PokeTrainerSide(@Nonnull PokeSide side, @Nonnull List<TrainerInBattle> trainerInBattleList) {
        this.side = side;
        this.trainerInBattleList = trainerInBattleList;
    }

    @Nonnull
    public PokeSide getSide() {
        return side;
    }

    public List<TrainerInBattle> getTrainerInBattleList() {
        return trainerInBattleList;
    }

    public TrainerInBattle getTrainerInBattle(int trainerPosition) {
        return trainerInBattleList.get(trainerPosition);
    }
}
