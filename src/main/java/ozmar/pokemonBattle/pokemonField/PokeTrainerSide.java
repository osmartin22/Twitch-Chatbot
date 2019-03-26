package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
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

    public PokeTrainerSide(@Nonnull TrainerInBattle trainerInBattle, int sideId) {
        this.sideId = sideId;
        this.side = new PokeSide(sideId);
        this.trainerInBattleList = new ArrayList<>(Collections.singletonList(trainerInBattle));
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

//    public void doChoice(@int trainerPosition, int fieldPosition) {
//        trainerInBattleList.get(trainerPosition).doTrainerChoice(fieldPosition);
//    }

    public void setMoveToUse(int trainerPosition, int fieldPosition, int movePosition,
                             @Nonnull PokeTargetPosition targetPosition) {
        trainerInBattleList.get(trainerPosition).setMoveToUse(fieldPosition, movePosition, targetPosition);
    }

    public boolean setPokeToSwitchIn(int trainerPosition, int fieldPosition, int pokePosition) {
        return trainerInBattleList.get(trainerPosition).setPokeToSwitchIn(fieldPosition, pokePosition);
    }

    public boolean isAbleToDoMove(int trainerPosition, int fieldPosition, int movePosition,
                                  @Nonnull PokeTargetPosition targetPosition) {
        return trainerInBattleList.get(trainerPosition).isAbleToDoMove(fieldPosition, movePosition, targetPosition);
    }

    public boolean isAbleToSwitchPoke(int trainerPosition, int fieldPosition) {
        return trainerInBattleList.get(trainerPosition).isAbleToSwitchPoke(fieldPosition);
    }
}
