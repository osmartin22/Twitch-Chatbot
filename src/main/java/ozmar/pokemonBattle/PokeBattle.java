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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PokeBattle {

    private ScheduledFuture<?> choiceTimer;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final PokeBattleViewInterface view;
    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeRules pokeRules;
    private final PokeBattleHandler pokeBattleHandler;

    public PokeBattle(@Nonnull PokeBattleViewInterface view, @Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        this.view = view;
        this.field = new PokeField();
        this.sideList = new ArrayList<>(listList.size());
        this.pokeRules = new PokeRules();
        this.pokeBattleHandler = new PokeBattleHandler(field, sideList);
        initialize(listList, pokesInBattle);
        startChoiceTimer();
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
        if (trainersReady()) {
            stopChoiceTimer();
            String battleResult = pokeBattleHandler.doTrainerChoices();
            view.sendMessageForAll(battleResult);
            checkForFaintedPokemon();

            // TODO: Send message to each user to make their choice after completing all choices
            startChoiceTimer();
        } else {
            System.out.println("TRAINERS ARE NOT READY YET");
        }
    }

    // TODO: If there are fainted Pokemon, start a timer for switching Pokemon
    //  When the timer runs out it should switch in a the first pokemon that is not fainted
    //  If no pokemon are available, the battle is over
    private void checkForFaintedPokemon() {
        for (PokeTrainerSide side : sideList) {
            for (TrainerInBattle tb : side.getTrainerInBattleList()) {
                for (PokeInBattle pb : tb.getPokeInBattleList()) {
                    if (pb.getPoke().isFainted()) {
                        String trainerName = tb.getTrainer().getTrainerName();
                        view.sendUserMessage(tb.getTrainer().getId(),
                                String.format("%s, Your Pokemon %s has fainted", trainerName, pb.getPoke().getName()));
                    }
                }
            }
        }
    }

    public void postAttackPhase() {

    }

    public long getTrainerId(int sidePosition, int TrainerPosition) {
        return sideList.get(0).getTrainerInBattle(0).getTrainer().getId();
    }

    public void startChoiceTimer() {
        for (PokeTrainerSide side : sideList) {
            for (TrainerInBattle tb : side.getTrainerInBattleList()) {
                for (PokeInBattle pb : tb.getPokeInBattleList()) {
                    view.sendUserMessage(tb.getTrainer().getId(), String.format("Choose action for %s", pb.getPoke().getName()));
                }
            }
        }

        System.out.println("Started Choice Timer");
        this.choiceTimer = timerToDoChoice();
    }

    public void stopChoiceTimer() {
        System.out.println("Stopping Choice Timer");
        this.choiceTimer.cancel(false);
    }

    @Nonnull
    private ScheduledFuture<?> timerToDoChoice() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out");
            // Timer only runs when a user has not done a choice yet (move or switch out)
            // A move should be chosen for them to do here
            doTrainerChoices();
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
    }
}
