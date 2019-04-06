package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleHandler;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokePosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeRules;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
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
    private final PokeRules pokeRules;
    private final PokeBattleHandler pokeBattleHandler;

    private final Map<Long, TrainerInBattle> tbMap;
    private final Map<Long, Set<Integer>> faintedMap;   // Trainer Id -> Set<Position of the pokemon on the field>

    /*
    UserId points to it's TrainerInBattle object

     */

    private BattlePhase phase;

    public PokeBattle(@Nonnull PokeBattleViewInterface view, @Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        this.tbMap = new HashMap<>();
        this.faintedMap = new HashMap<>();

        this.phase = BattlePhase.WAITING;
        this.view = view;
        this.field = new PokeField();
        this.pokeRules = new PokeRules();

        List<PokeTrainerSide> sideList = initialize(listList, pokesInBattle);
        this.pokeBattleHandler = new PokeBattleHandler(field, sideList, new HashSet<>(tbMap.values()));
        startChoiceTimer();
    }

    private List<PokeTrainerSide> initialize(@Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        List<PokeTrainerSide> pokeTrainerSideList = new ArrayList<>(listList.size());
        for (int i = 0; i < listList.size(); i++) {
            PokeSide side = new PokeSide(i);
            List<Trainer> trainerList = listList.get(i);
            int trainerListSize = trainerList.size();
            List<TrainerInBattle> trainerInBattleList = new ArrayList<>(trainerListSize);
            pokeTrainerSideList.add(new PokeTrainerSide(side, trainerInBattleList));

            for (int j = 0; j < trainerListSize; j++) {
                Trainer trainer = trainerList.get(j);
                TrainerInBattle tb = new TrainerInBattle(side, trainer, j, pokesInBattle);
                trainerInBattleList.add(tb);
                faintedMap.put(trainer.getId(), new HashSet<>());
                tbMap.put(trainer.getId(), tb);
            }
        }

        return pokeTrainerSideList;
    }

    public boolean setMoveToUse(long userId, int fieldPosition, int movePosition, @Nonnull PokePosition targetPosition) {
        boolean canSetMove = false;
        if (phase == BattlePhase.WAITING) {
            TrainerInBattle trainerInBattle = tbMap.get(userId);
            canSetMove = pokeRules.setMoveToUse(trainerInBattle, fieldPosition, movePosition, targetPosition);
        }

        return canSetMove;
    }

    public boolean setPokeToSwitchIn(long userId, int fieldPosition, int pokePosition) {
        boolean canSwitch = false;

        TrainerInBattle trainerInBattle = tbMap.get(userId);
        if (phase == BattlePhase.WAITING) {
            canSwitch = pokeRules.setPokeToSwitchIn(trainerInBattle, fieldPosition, pokePosition);

        } else if (phase == BattlePhase.SWITCHING) {
            Set<Integer> positionsSet = faintedMap.get(trainerInBattle.getTrainer().getId());
            if (!positionsSet.isEmpty() && positionsSet.contains(fieldPosition)) {
                canSwitch = pokeRules.setPokeToSwitchIn(trainerInBattle, fieldPosition, pokePosition);
                if (canSwitch) {
                    positionsSet.remove(fieldPosition);
                }

                if (!faintedPokeStillOnField()) {
                    stopSwitchTimer();
                    pokeBattleHandler.test();
                    phase = BattlePhase.WAITING;
                }
            }
        }

        return canSwitch;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(long userId, int fieldPosition) {
        return tbMap.get(userId).getPokeInBattle(fieldPosition);
    }

    @Nonnull
    public String getMoves(long userId, int fieldPosition) {
        return tbMap.get(userId).getPokeInBattle(fieldPosition).getPoke().getMoves();
    }

    public boolean trainersReady() {
        for (TrainerInBattle tb : tbMap.values()) {
            if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
                return false;
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
            if (checkForFaintedPoke()) {
                phase = BattlePhase.SWITCHING;
                startSwitchTimer();
            } else {
                startChoiceTimer();
                phase = BattlePhase.WAITING;
            }

//            // If there are fainted pokemon, start Choice Timer after users switch pokes
//
            // TODO: Send message to each user to make their choice after completing
            //  all choices and fainted pokemon have switched in
        }
    }

    // TODO: If there are fainted Pokemon, start a timer for switching Pokemon
    //  When the timer runs out it should switch in a the first pokemon that is not fainted
    //  If no pokemon are available, the battle is over
    private boolean checkForFaintedPoke() {
        boolean pokemonHasFainted = false;
        for (TrainerInBattle tb : tbMap.values()) {
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

        return pokemonHasFainted;
    }

    private boolean faintedPokeStillOnField() {
        boolean stillOnField = false;
        for (Set<Integer> positions : faintedMap.values()) {
            if (!positions.isEmpty()) {
                stillOnField = true;
            }
        }

        return stillOnField;
    }

    public void postAttackPhase() {

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

    public void startChoiceTimer() {
        for (TrainerInBattle tb : tbMap.values()) {
            for (PokeInBattle pb : tb.getPokeInBattleList()) {
                view.sendUserMessage(tb.getTrainer().getId(), String.format("Choose action for %s", pb.getPoke().getName()));
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
    private ScheduledFuture<?> timerToSwitch() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out For Switching");
            // Timer only runs when a user/users have to switch a pokemon due to it fainting or being forced
            // A pokemon to switch should be set if the timer runs out
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
}
