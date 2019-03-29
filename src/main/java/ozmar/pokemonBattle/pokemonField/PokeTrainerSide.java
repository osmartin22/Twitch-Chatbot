package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.List;

public class PokeTrainerSide {
    private final int sideId;
    private final PokeSide side;
    private final List<TrainerInBattle> trainerInBattleList;

    public PokeTrainerSide(@Nonnull List<TrainerInBattle> trainerInBattleList, int sideId) {
        this.sideId = sideId;
        this.side = new PokeSide(sideId);
        this.trainerInBattleList = trainerInBattleList;
    }

    public int getSideId() {
        return sideId;
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
