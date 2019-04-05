package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleHandler;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokePosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeRules;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PokeBattle {

    private ScheduledFuture<?> switchTimer;
    private ScheduledFuture<?> choiceTimer;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final PokeBattleViewInterface view;
    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeRules pokeRules;
    private final PokeBattleHandler pokeBattleHandler;

    private final Map<Long, Set<Integer>> faintedMap;   // Trainer Id -> Set<Position of the pokemon on the field>

    private BattlePhase phase;

    public PokeBattle(@Nonnull PokeBattleViewInterface view, @Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        this.phase = BattlePhase.WAITING;
        this.view = view;
        this.field = new PokeField();
        this.sideList = new ArrayList<>(listList.size());
        this.pokeRules = new PokeRules();
        this.pokeBattleHandler = new PokeBattleHandler(field, sideList);
        this.faintedMap = new HashMap<>();
        initialize(listList, pokesInBattle);
        startChoiceTimer();
    }

    private void initialize(@Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        for (int i = 0; i < listList.size(); i++) {
            List<Trainer> trainerList = listList.get(i);
            int trainerListSize = trainerList.size();
            List<TrainerInBattle> trainerInBattleList = new ArrayList<>(trainerListSize);
            sideList.add(new PokeTrainerSide(trainerInBattleList, i));

            for (int j = 0; j < trainerListSize; j++) {
                Trainer trainer = trainerList.get(j);
                trainerInBattleList.add(new TrainerInBattle(trainer, i, j, pokesInBattle));
                faintedMap.put(trainer.getId(), new HashSet<>());
            }
        }
    }

    public boolean setMoveToUse(int sidePosition, int trainerPosition, int fieldPosition, int movePosition,
                                @Nonnull PokePosition targetPosition) {
        boolean canSetMove = false;
        if (phase == BattlePhase.WAITING) {
            TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
            canSetMove = pokeRules.setMoveToUse(trainerInBattle, fieldPosition, movePosition, targetPosition);
        } else {
            // Only for testing currently
            System.out.println("Currently in battle phase. Setting a move not allowed");
        }

        return canSetMove;
    }

    public boolean setPokeToSwitchIn(int sidePosition, int trainerPosition, int fieldPosition, int pokePosition) {
        boolean canSwitch = false;
        if (phase == BattlePhase.WAITING || phase == BattlePhase.SWITCHING) {
            TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
            canSwitch = pokeRules.setPokeToSwitchIn(trainerInBattle, fieldPosition, pokePosition);
        } else {
            // Only for testing currently
            System.out.println("Currently in battle phase. Switching out is not available");
        }

        return canSwitch;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int sidePosition, int trainerPosition, int fieldPosition) {
        return sideList.get(sidePosition).getTrainerInBattle(trainerPosition).getPokeInBattle(fieldPosition);
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
            phase = BattlePhase.BATTLING;
            stopChoiceTimer();
            String battleResult = pokeBattleHandler.doTrainerChoices();
            view.sendMessageForAll(battleResult);
            if (checkForFaintedPokemon()) {
                startSwitchTimer();
            }

            // If there are fainted pokemon, start Choice Timer after users switch pokes

            // TODO: Send message to each user to make their choice after completing all choices
            startChoiceTimer();
            phase = BattlePhase.WAITING;
        } else {
            System.out.println("TRAINERS ARE NOT READY YET");
        }
    }

    // TODO: If there are fainted Pokemon, start a timer for switching Pokemon
    //  When the timer runs out it should switch in a the first pokemon that is not fainted
    //  If no pokemon are available, the battle is over
    private boolean checkForFaintedPokemon() {
        boolean pokemonHasFainted = false;
        for (PokeTrainerSide side : sideList) {
            for (TrainerInBattle tb : side.getTrainerInBattleList()) {
                for (PokeInBattle pb : tb.getPokeInBattleList()) {
                    if (pb.getPoke().isFainted()) {
                        pokemonHasFainted = true;
                        faintedMap.get(tb.getTrainer().getId()).add(pb.getFieldPosition());

                        String trainerName = tb.getTrainer().getTrainerName();
                        view.sendUserMessage(tb.getTrainer().getId(),
                                String.format("%s, Your Pokemon %s has fainted", trainerName, pb.getPoke().getName()));
                    }
                }
            }
        }

        return pokemonHasFainted;
    }

    public void postAttackPhase() {

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
            System.out.println("Time Ran Out For Choosing A Choice");
            // Timer only runs when a user has not done a choice yet (move or switch out)
            // A move should be chosen for them to do here
            doTrainerChoices();
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
    }

    public void startSwitchTimer() {
        System.out.println("Started Switch Timer");
        switchTimer = timerToSwitch();
    }

    public void stopSwitchTimer() {
        System.out.println("Stopped Switch Timer");
        switchTimer.cancel(false);
    }

    @Nonnull
    private ScheduledFuture<?> timerToSwitch() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out For Switching");
            // Timer only runs when a user/users have to switch a pokemon due to it fainting or being forced
            // A pokemon to switch should be set if the timer runs out
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
    }

    public BattlePhase getPhase() {
        return phase;
    }
}
