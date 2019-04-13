package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleHandler;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokePosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeRules;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerAction;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeTarget;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.*;

public class PokeBattle {

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

    @Nonnull
    public PokeInBattle getPokeInBattle(long userId, int fieldPosition) {
        return tbMap.get(userId).getPokeInBattle(fieldPosition);
    }

    @Nonnull
    public String getMoves(long userId, int fieldPosition) {
        return tbMap.get(userId).getPokeInBattle(fieldPosition).getPoke().getMoves();
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

    public SwitchResult setPokeToSwitchIn(long userId, int fieldPosition, int pokePosition) {
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
                    view.sendMessageForAll(switchPokesByForce());
                    phase = BattlePhase.WAITING;
                }
            }
        }

        return new SwitchResult(canSwitch, phase);
    }

    /**
     * Checks if all the Poke on the field have received orders from their trainer
     *
     * @return boolean
     */
    public boolean trainersReady() {
        for (TrainerInBattle tb : tbMap.values()) {
            if (tb.getPokeInBattle(0).getTrainerAction() == TrainerAction.ACTION_WAITING) {
                return false;
            }
        }

        return true;
    }

    public BattlePhase doBattlePhase() {
        if (trainersReady()) {
            phase = BattlePhase.BATTLING;
            String battleResult = doTrainerActions();
            view.sendMessageForAll(battleResult);

            if (!faintedMap.isEmpty()) {
                sendFaintedMessage();

                boolean trainerLost = checkTrainerHasLost();
                if (trainerLost) {
                    phase = BattlePhase.FINISHED;
                    return BattlePhase.FINISHED;
                }

                phase = BattlePhase.SWITCHING;

            } else {
                phase = BattlePhase.WAITING;
            }
            // TODO: Send message to each user to make their choice after completing
            //  all choices and fainted pokemon have switched in
        }
        return phase;
    }

    /**
     * Does all of the Trainer's actions
     * Switches out Pokes that are set to switch
     * Does the moves that Poke have chosen to do
     * TODO: Currently Pursuit is not taken into effect
     *
     * @return String
     */
    @Nonnull
    private String doTrainerActions() {
        List<PokeInBattle> switching = new ArrayList<>();
        List<PokeInBattle> attacking = new ArrayList<>();
        for (TrainerInBattle tb : tbMap.values()) {
            if (tb.getPokeInBattle(0).getTrainerAction() == TrainerAction.ACTION_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            } else {
                attacking.add(tb.getPokeInBattle(0));
            }
        }

        // TODO: TurnResult should also return the order in which Poke have fainted
        //  Assume  Poke2 low HP and Trainer1 and Trainer2 are on their last Poke
        //  -Poke1 uses Explosion (Assume Poke2 low HP) -> Poke1 faints first and Poke2 faints second
        //  -Both Trainers have now lost all their Poke but Trainer2 wins due to Poke1 fainting first
        TurnResult result = pokeBattleHandler.doActions(switching, attacking);
        faintedMap = result.getFaintedPokemon();
        return result.getResult();
    }

    public void postAttackPhase() {
        StringBuilder sb = new StringBuilder();
        for (TrainerInBattle tb : tbMap.values()) {
            for (PokeInBattle pb : tb.getPokeInBattleList()) {
                // Should afflict status damage
                // Should afflict binding damage
                // Should afflict any other damaging effects (i.e. leech seed)
            }
        }
    }

    /**
     * Send message to trainers whose Poke have fainted
     */
    private void sendFaintedMessage() {
        for (Map.Entry<Long, Set<Integer>> entry : faintedMap.entrySet()) {
            TrainerInBattle tb = tbMap.get(entry.getKey());
            for (int fieldPosition : entry.getValue()) {
                PokeInBattle pb = tb.getPokeInBattle(fieldPosition);
                String trainerName = tb.getTrainer().getTrainerName();
                view.sendUserMessage(tb.getTrainer().getId(),
                        String.format("%s, Your Pokemon %s has fainted", trainerName, pb.getPoke().getName()));
            }
        }
    }

    /**
     * Forces the Poke on the field to do an action (move)
     */
    public void forceActionOnPoke() {
        for (TrainerInBattle tb : tbMap.values()) {
            int moveCount = tb.getPokeInBattle(0).getPoke().getMoveList().size();
            for (int i = 0; i < moveCount; i++) {
                boolean canDoMove = setMoveToUse(tb.getTrainer().getId(), 0, i);
                if (canDoMove) {
                    break;
                }
            }

            // TODO: If no move is selected at this point, need to use Struggle
        }
    }

    /**
     * Tells each trainer they can now choose an action
     */
    public void tellTrainersToDoAction() {
        for (TrainerInBattle tb : tbMap.values()) {
            for (PokeInBattle pb : tb.getPokeInBattleList()) {
                view.sendUserMessage(tb.getTrainer().getId(), String.format("Choose action for %s", pb.getPoke().getName()));
            }
        }
    }

    /**
     * Choose a Poke to switch in for the fainted Poke on the field
     * This bypasses effects that prevent switching out
     */
    public void forceSwitchFaintedPoke() {
        for (long id : faintedMap.keySet()) {
            TrainerInBattle tb = tbMap.get(id);

            boolean canSwitch = false;
            int count = 0;
            for (int position : faintedMap.get(id)) {
                while (!canSwitch && count < 6) {
                    canSwitch = pokeRules.setPokeToSwitchIn(tb, position, count++);
                }

                faintedMap.get(id).remove(position);
                if (faintedMap.get(id).isEmpty()) {
                    faintedMap.remove(id);
                }
            }
        }

        view.sendMessageForAll(switchPokesByForce());
        phase = BattlePhase.WAITING;
    }


    /**
     * Checks if a trainer has lost == All of the trainer's Poke have fainted
     * TODO: Currently this does not consider who fainted first
     * Important in cases where the last Poke of each trainer faints on the same turn, i.e. Explosion
     *
     * @return boolean
     */
    private boolean checkTrainerHasLost() {
        for (long id : faintedMap.keySet()) {
            TrainerInBattle tb = tbMap.get(id);
            int pokeListSize = tb.getTrainer().getPokeList().size();
            int faintedCount = tb.getTrainer().getFaintedPokeCount();
            if (pokeListSize == faintedCount) {
                // TODO: Should also say who the winner is
                view.sendMessageForAll(String.format("%s has lost. ", tb.getTrainer().getTrainerName()));
                return true;
            }
        }

        return false;
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
            if (tb.getPokeInBattle(0).getTrainerAction() == TrainerAction.ACTION_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            }
        }

        return pokeBattleHandler.switchPokeOut(switching);
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
}
