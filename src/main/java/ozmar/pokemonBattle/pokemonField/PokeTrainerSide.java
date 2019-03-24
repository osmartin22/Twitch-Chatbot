package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.PokeRules;
import ozmar.pokemonBattle.PositionHelper;
import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PokeTrainerSide {
    private final PokeSide side;

    private final List<TrainerInBattle> trainerInBattleList;

    private final PokeRules pokeRules;

    public PokeTrainerSide(@NonNull List<TrainerInBattle> trainerInBattleList, @NonNull PokeSide side) {
        this.side = side;
        this.trainerInBattleList = trainerInBattleList;
        this.pokeRules = new PokeRules();
    }

    public PokeTrainerSide(@Nonnull TrainerInBattle trainerInBattle, @Nonnull PokeSide side) {
        this.trainerInBattleList = new ArrayList<>();
        this.trainerInBattleList.add(trainerInBattle);

        this.side = side;
        this.pokeRules = new PokeRules();
    }

    @Nonnull
    public PokeSide getSide() {
        return side;
    }

    public List<TrainerInBattle> getTrainerInBattleList() {
        return trainerInBattleList;
    }

    public TrainerInBattle getTrainerInBattle(@NonNull PositionHelper positions) {
        return trainerInBattleList.get(positions.getTrainer());
    }

    @Nonnull
    public TrainerChoice getTrainerChoice(@NonNull PositionHelper positions) {
        return trainerInBattleList.get(positions.getTrainer()).getTrainerChoice(positions);
    }

    public void doChoice(@NonNull PositionHelper positions) {
        trainerInBattleList.get(positions.getTrainer()).doTrainerChoice(positions);
    }

    public void setMoveToUse(@NonNull PositionHelper positions) {
        trainerInBattleList.get(positions.getTrainer()).setMoveToUse(positions);
    }

    public boolean setPokeToSwitchIn(@NonNull PositionHelper positions) {
        return trainerInBattleList.get(positions.getTrainer()).setPokeToSwitchIn(positions);
    }

    public boolean isAbleToDoMove(@NonNull PositionHelper positions) {
        return trainerInBattleList.get(positions.getTrainer()).isAbleToDoMove(positions);
    }

    public boolean isAbleToSwitchPoke(@NonNull PositionHelper positions) {
        return trainerInBattleList.get(positions.getTrainer()).isAbleToSwitchPoke(positions);
    }

    public void doNonVolatileStatusEffect() {

    }
}
