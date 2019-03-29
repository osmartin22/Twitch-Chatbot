package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleHandler;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeRules;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PokeBattle {

    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeRules pokeRules;
    private final PokeBattleHandler pokeBattleHandler;

    public PokeBattle(@Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        this.field = new PokeField();
        this.sideList = new ArrayList<>(listList.size());
        this.pokeRules = new PokeRules();
        this.pokeBattleHandler = new PokeBattleHandler(field, sideList);
        initialize(listList, pokesInBattle);
    }

    private void initialize(@Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        for (int i = 0; i < listList.size(); i++) {
            List<Trainer> trainerList = listList.get(i);
            List<TrainerInBattle> trainerInBattleList = new ArrayList<>(trainerList.size());
            for (int j = 0; j < trainerList.size(); j++) {
                trainerInBattleList.add(new TrainerInBattle(trainerList.get(j), i, j, pokesInBattle));
            }
            sideList.add(new PokeTrainerSide(trainerInBattleList, i));
        }
    }

    public boolean setMoveToUse(int sidePosition, int trainerPosition, int fieldPosition, int movePosition,
                                @Nonnull PokeTargetPosition targetPosition) {
        TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
        return pokeRules.setMoveToUse(trainerInBattle, fieldPosition, movePosition, targetPosition);
    }

    public boolean setPokeToSwitchIn(int sidePosition, int trainerPosition, int fieldPosition, int pokePosition) {
        TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
        return pokeRules.setPokeToSwitchIn(trainerInBattle, fieldPosition, pokePosition);
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int sidePosition, int trainerPosition, int fieldPosition) {
        return sideList.get(sidePosition).getTrainerInBattle(trainerPosition).getPokeInBattle(fieldPosition);
    }

    @Nonnull
    public TrainerChoice getTrainerStatus(int sidePosition, int trainerPosition, int fieldPosition) {
        TrainerInBattle tb = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
        return tb.getPokeInBattle(fieldPosition).getTrainerChoice();
    }

    public boolean trainersReady() {
        for (PokeTrainerSide pSide : sideList) {
            List<TrainerInBattle> tbList = pSide.getTrainerInBattleList();
            for (TrainerInBattle tb : tbList) {
                if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
                    return false;
                }
            }
        }

        return true;
    }

    public void doTrainerChoices() {
        pokeBattleHandler.doTrainerChoices();
    }

    public void postAttackPhase() {

    }
}
