package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleHandler;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokePosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeRules;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeTarget;
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
    private Map<Long, Set<Integer>> faintedMap;   // Trainer Id -> Set<Position of the pokemon on the field>

    private BattlePhase phase;

    public PokeBattle(@Nonnull PokeBattleViewInterface view, @Nonnull List<List<Trainer>> listList, int pokesInBattle) {
        this.tbMap = new HashMap<>();

        this.view = view;
        this.field = new PokeField();
        this.pokeRules = new PokeRules();

        List<PokeTrainerSide> sideList = initialize(listList, pokesInBattle);
        this.pokeBattleHandler = new PokeBattleHandler(field, sideList);
        this.phase = BattlePhase.WAITING;
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
                tbMap.put(trainer.getId(), tb);
            }
        }

        return pokeTrainerSideList;
    }

    /**
     * Switches all Poke that are set to Switch and bypasses checks that prevent switching
     * String returned contains the Poke that switched out and in
     *
     * @return String
     */
    @Nonnull
    private String switchPokesByForce() {
        List<PokeInBattle> switching = new ArrayList<>();
        for (TrainerInBattle tb : tbMap.values()) {
            if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            }
        }

        return pokeBattleHandler.switchPokeOut(switching);
    }

    /**
     * Switches out Pokes that are set to switch
     * Does the moves that Poke have chosen to do
     * TODO: Currently Pursuit is not taken into effect
     *
     * @return String
     */
    @Nonnull
    private String doAllChoices() {
        List<PokeInBattle> switching = new ArrayList<>();
        List<PokeInBattle> attacking = new ArrayList<>();
        for (TrainerInBattle tb : tbMap.values()) {
            if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            } else {
                attacking.add(tb.getPokeInBattle(0));
            }
        }

        TurnResult result = pokeBattleHandler.doChoices(switching, attacking);
        faintedMap = result.getFaintedPokemon();
        return result.getResult();
    }

    /**
     * Gets the position that the move will target
     * This ONLY works for 1v1 battles
     *
     * @param pb           Poke using the move
     * @param movePosition position of the move in the Poke's move set
     * @return PokePosition
     */
    @Nonnull
    private PokePosition getMoveTarget(@Nonnull PokeInBattle pb, int movePosition) {
        PokeMove move = pb.getPoke().getMoveList().get(movePosition);
        PokeTarget target = move.getMoveTarget();
        PokePosition pokePosition;
        if (target == PokeTarget.USER || target == PokeTarget.USER_AND_ALLIES || target == PokeTarget.USER_OR_ANY_ADJ_ALLY) {
            pokePosition = new PokePosition(pb.getSidePosition(), pb.getTrainerPosition(), pb.getFieldPosition());
        } else {
            int fieldPosition = (pb.getSidePosition() == 0) ? 1 : 0;
            pokePosition = new PokePosition(fieldPosition, 0, 0);
        }

        return pokePosition;
    }

    /**
     * Attempts to set the desired move
     *
     * @param userId        id of the trainer
     * @param fieldPosition position on the field the Poke is in
     * @param movePosition  position of the move in the Poke's move set
     * @return boolean
     */
    public boolean setMoveToUse(long userId, int fieldPosition, int movePosition) {
        boolean canSetMove = false;
        if (phase == BattlePhase.WAITING) {
            TrainerInBattle tb = tbMap.get(userId);
            PokeInBattle pb = tb.getPokeInBattle(fieldPosition);
            PokePosition targetPosition = getMoveTarget(pb, movePosition);
            canSetMove = pokeRules.setMoveToUse(tb, fieldPosition, movePosition, targetPosition);
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
            if (positionsSet != null && positionsSet.contains(fieldPosition)) {
                canSwitch = pokeRules.setPokeToSwitchIn(trainerInBattle, fieldPosition, pokePosition);
                if (canSwitch) {
                    positionsSet.remove(fieldPosition);
                    if (positionsSet.isEmpty()) {
                        faintedMap.remove(userId);
                    }
                }

                if (faintedMap.isEmpty()) {
                    stopSwitchTimer();
                    view.sendMessageForAll(switchPokesByForce());
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
            stopChoiceTimer();
            phase = BattlePhase.BATTLING;
            String battleResult = doAllChoices();
            view.sendMessageForAll(battleResult);
            if (checkForFaintedPoke()) {

                // Can maybe check here to see if all Poke are fainted

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
//                    faintedMap.get(tb.getTrainer().getId()).add(pb.getFieldPosition());

                    String trainerName = tb.getTrainer().getTrainerName();
                    view.sendUserMessage(tb.getTrainer().getId(),
                            String.format("%s, Your Pokemon %s has fainted", trainerName, pb.getPoke().getName()));
                }
            }
        }

        return pokemonHasFainted;
    }

    public void postAttackPhase() {

    }

    @Nonnull
    private ScheduledFuture<?> timerToDoChoice() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out For Choosing A Choice");
            for (TrainerInBattle tb : tbMap.values()) {
//                tb.getPokeInBattle(0).setMoveToUse(0, );
            }
            // Timer only runs when a user has not done a choice yet (move or switch out)
            // A move should be chosen for them to do here
//            doTrainerChoices();
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

    // TODO: When switching a Poke out after fainting. The only time a another Poke cannot switch out is due
    //  to it being fainted or the same Poke already on the field.
    //  Should remove reset statuses when a Poke faints so that switching will always pass
    //  or create a method that bypasses switch checks (This method might be useful for moves that can force switch a Poke)

    /**
     * When the Switch Timer runs out, any Poke that are still fainted are forced to switch out
     *
     * @return ScheduledFuture
     */
    @Nonnull
    private ScheduledFuture<?> timerToSwitch() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out For Switching");
            // Timer only runs when a user/users have to switch a pokemon due to it fainting or being forced
            // A pokemon to switch should be set if the timer runs out
            for (long id : faintedMap.keySet()) {
                TrainerInBattle tb = tbMap.get(id);

                boolean canSwitch = false;
                int count = 0;
                for (int position : faintedMap.get(id)) {
                    while (!canSwitch && count < 6) {
                        canSwitch = pokeRules.setPokeToSwitchIn(tb, position, count++);
                    }

                    faintedMap.get(id).remove(position);
                    if (!canSwitch) {
                        // TODO: Change were this event is first discovered
                        System.out.println("TRAINER HAS LOST");
                    }
                }
            }

            view.sendMessageForAll(switchPokesByForce());
            phase = BattlePhase.WAITING;
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
    }

    /**
     * Start the timer used for switching only Poke
     */
    public void startSwitchTimer() {
        System.out.println("Started Switch Timer");
        switchTimer = timerToSwitch();
    }

    /**
     * Stop the timer used for switching only Poke
     */
    public void stopSwitchTimer() {
        System.out.println("Stopped Switch Timer");
        switchTimer.cancel(false);
    }
}
