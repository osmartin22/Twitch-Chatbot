package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.pokemonBattle.TurnResult;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;
import java.util.*;

// TODO: Create a class than can hold information on what happens in the battle
//  i.e. Pokemon was confused, flinched, stats were changed, hp restored/damaged
//  In the games, actions are displayed on screen as they occur
//  But to prevent message spam, actions will be grouped into a message and be sent after all of the actions have finished

// *NOTE This only supports 1 v 1 battles
public class PokeBattleHandler {
    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeBattleCalculator calculator;
    private StringBuilder sb;

    public PokeBattleHandler(@Nonnull PokeField field, @Nonnull List<PokeTrainerSide> sideList) {
        this.field = field;
        this.sideList = sideList;
        this.calculator = new PokeBattleCalculator();
        this.sb = new StringBuilder();
    }

    /**
     * Switches the Poke out contained in the list
     * Should only be used in cases where the only options a trainer has is to switch
     * i.e. their Poke fainted or are forced to switch out do to a move
     *
     * @param switching list of Poke's to switch out
     * @return String containing events that occurred while switching
     */
    public String switchPokeOut(List<PokeInBattle> switching) {
        sb.setLength(0);
        switchPokeIn(switching);
        return sb.toString();
    }

    /**
     * Switches out Poke, and Poke that are using a move use them.
     *
     * @param switching list of Poke that are switching out
     * @param attacking list of Poke that are using a move
     * @return TurnResult. Contains a map of all fainted Poke along with a String containing the events that occurred
     */
    public TurnResult doChoices(List<PokeInBattle> switching, List<PokeInBattle> attacking) {
        sb.setLength(0);
        switchPokeIn(switching);
        Map<Long, Set<Integer>> faintedMap = attackingPokes(attacking);

        return new TurnResult(sb.toString(), faintedMap);
    }

    @Nonnull
    private PokeInBattle getPokeInBattle(@Nonnull PokeInBattle pb) {
        return sideList.get(pb.getSidePosition())
                .getTrainerInBattle(pb.getTrainerPosition())
                .getPokeInBattle(pb.getFieldPosition());
    }

    @Nonnull
    private TrainerInBattle getTrainerInBattle(@Nonnull PokeInBattle pb) {
        return sideList.get(pb.getSidePosition())
                .getTrainerInBattle(pb.getTrainerPosition());
    }

    /**
     * Sorts the list by a Poke's speed
     * The list itself is modified and not copied
     *
     * @param list list to be sorted
     */
    private void sortSwitching(@Nonnull List<PokeInBattle> list) {
        list.sort((o1, o2) -> {
            int speed1 = o1.getPoke().getPokeStat(PokeStat.SPD);
            if (o1.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                speed1 /= 2;
            }
            int speed2 = o2.getPoke().getPokeStat(PokeStat.SPD);
            if (o2.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                speed2 /= 2;
            }
            return speed1 - speed2;
        });
    }

    /**
     * Sorts the list by a a moves Priority first. If moves have the same priority, it is sorted by a Poke's speed
     * The list itself is modified and not copied
     *
     * @param list list to be sorted
     */
    private void sortAttacking(@Nonnull List<PokeInBattle> list) {
        list.sort((o1, o2) -> {
            int result = (o2.getMoveToUse().getPriority()) - o1.getMoveToUse().getPriority();
            if (result == 0) {
                int speed1 = o1.getPoke().getPokeStat(PokeStat.SPD);
                if (o1.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                    speed1 /= 2;
                }
                int speed2 = o2.getPoke().getPokeStat(PokeStat.SPD);
                if (o2.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                    speed2 /= 2;
                }
                result = speed2 - speed1;
            }

            return result;
        });
    }

    /**
     * Switches the Poke in the list out and damages them with Entry Hazards if present
     *
     * @param pbList list of Poke to switch out
     */
    private void switchPokeIn(@Nonnull List<PokeInBattle> pbList) {
        sortSwitching(pbList);
        for (PokeInBattle pb : pbList) {
            TrainerInBattle tb = sideList.get(pb.getSidePosition()).getTrainerInBattle(pb.getTrainerPosition());
            if (tb.getPokeInBattle(pb.getFieldPosition()).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                sb.append(String.format("%s switched in for %s. ", pb.getPokeToSwitchIn().getName(), pb.getPoke().getName()));
                tb.switchPoke(pb.getFieldPosition());

                PokeSide side = tb.getSide();
                if (side.getEntryHazard().isEntryHazardPresent()) {
                    side.getEntryHazard().doEntryHazardEffect(pb);
                }
            }
        }
    }

    /**
     * For every Poke in the list, the Poke attempts to use its move
     *
     * @param attacking list of Poke using a move
     * @return Map. Contains all Poke that have fainted with the trainer's id as the key
     */
    public Map<Long, Set<Integer>> attackingPokes(@Nonnull List<PokeInBattle> attacking) {
        sortAttacking(attacking);
        // Currently ignoring status moves as they have a lot of unique effects to take into account
        Map<Long, Set<Integer>> faintedMap = new HashMap<>();
        for (PokeInBattle pb : attacking) {
            if (pb.getTrainerChoice() == TrainerChoice.CHOICE_MOVE &&
                    pb.getMoveToUse().getDamageClass() != PokeMoveDamageClass.STATUS) {

                int attackAcc = pb.getStatStage(PokeStatStage.ACC_STAGE);
                int targetEva = pb.getStatStage(PokeStatStage.EVA_STAGE);
                if (!calculator.nvStatusPreventsMove(pb)) {
                    if (calculator.willMoveHit(attackAcc, targetEva, pb.getMoveToUse())) {
                        faintedMap = doMove(pb);

                    } else {
                        sb.append(String.format("%s missed. ", pb.getPoke().getName()));
                    }
                } else {
                    sb.append(String.format("%s was prevented by a status effect. ", pb.getPoke().getName()));
                }

                pb.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }

        return faintedMap;
    }

    /**
     * Poke attempts to do the move and damages the target
     * The move will affect statuses that it can affect
     * TODO: Currently status moves are ignored (they have a lot of unique effects)
     * Most move effects are not considered
     * Need to test inflicting statuses and move prevented more
     *
     * @param attacker Poke using a move
     * @return Map. Contains all Poke that have fainted with the trainer's id as the key
     */
    private Map<Long, Set<Integer>> doMove(@Nonnull PokeInBattle attacker) {
        Map<Long, Set<Integer>> faintedMap = new HashMap<>();

        PokeMove move = attacker.getMoveToUse();
        PokePosition targetPosition = attacker.getTargetPosition();
        PokeInBattle target = sideList.get(targetPosition.getSidePosition())
                .getTrainerInBattle(targetPosition.getTrainerPosition())
                .getPokeInBattle(targetPosition.getFieldPosition());

        if (attacker.getMoveToUse().getDamageClass() == PokeMoveDamageClass.STATUS) {
            // TODO: Do status moves later, first finish Physical and Special moves
        } else {

            boolean isMovePrevented = isMovePrevented(attacker);
            if (!isMovePrevented) {
                boolean moveWillHit = willMoveHit(attacker, target, move);
                if (moveWillHit) {
                    attacker.setLastUsedMove(attacker.getMoveToUse());
                    int damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
                    target.getPoke().lowerHp(damageDone);
                    sb.append(String.format("%s attacked %s for %s damage. ", attacker.getPoke().getName(),
                            target.getPoke().getName(), damageDone));

                    if (target.getPoke().isFainted()) {
                        addPokeToFaintedMap(faintedMap, target);
                        target.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
                        sb.append(String.format("%s has fainted. ", target.getPoke().getName()));
                    }

                    inflictStatuses(target, move);
                } else {
                    sb.append(String.format("%s's attack missed", attacker.getPoke().getName()));
                }
            } else {
                sb.append(String.format("%s was prevented from using its move", attacker.getPoke().getName()));
            }
        }

        attacker.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        return faintedMap;
    }

    /**
     * Adds the Poke to the fainted Poke with their key being its trainer's id
     *
     * @param faintedMap Map of all fainted Poke
     * @param pb         Poke to add the the map
     */
    private void addPokeToFaintedMap(@Nonnull Map<Long, Set<Integer>> faintedMap, @Nonnull PokeInBattle pb) {
        int position = pb.getFieldPosition();
        long id = getTrainerInBattle(pb).getTrainer().getId();
        Set<Integer> positions = faintedMap.get(id);

        if (positions != null) {
            positions.add(position);
        } else {
            faintedMap.put(id, new HashSet<>(Collections.singleton(position)));
        }
    }

    /**
     * Cheks if the move will hit the target or miss
     * TODO: Probably write a method for moves that target the user (or bypass this check) i.e. Swords Dance
     *
     * @param attacker Poke using the move
     * @param target The move's target
     * @param move Move to use
     * @return boolean, will the move hit the target or miss
     */
    private boolean willMoveHit(@Nonnull PokeInBattle attacker, @Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        return calculator.willMoveHit(attacker.getStatStage(PokeStatStage.ACC_STAGE),
                target.getStatStage(PokeStatStage.EVA_STAGE), move);
    }

    private boolean isMovePrevented(@Nonnull PokeInBattle attacker) {
        boolean nvStatusPrevented = calculator.nvStatusPreventsMove(attacker);
        boolean confusionPrevented = calculator.confusionActivates(attacker);
        return nvStatusPrevented || confusionPrevented;
    }

    private void inflictStatuses(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        inflictNonVolatile(target, move);
        inflictConfusion(target, move);
        inflictFlinch(target, move);
    }

    private void inflictNonVolatile(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        NonVolatileStatus status = move.getMetaData().getNonVolatileStatus();
        if (status != NonVolatileStatus.NONE) {
            if (status == NonVolatileStatus.MULTIPLE) {
                // Only a handful of moves go here
            } else {
                if (calculator.willEffectHit(move.getMetaData().getNonVolatileChance())) {
                    target.getPoke().updateNonVolatile(status);
                }
            }
        }
    }

    private void inflictFlinch(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        // Flinch only occurs on Pokemon that have not yet attacked
        if (target.getTrainerChoice() == TrainerChoice.CHOICE_MOVE) {
            boolean willFlinch = calculator.willEffectHit(move.getMetaData().getFlinchChance());
            if (willFlinch) {
                // Target flinches and can no longer attack its turn
                sb.append(String.format("%s was flinched. ", target.getPoke().getName()));
                target.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }
    }

    private boolean inflictConfusion(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        boolean isConfused = false;
        boolean willConfuse = RandomHelper.getRandNumInRange(1, 100) <= move.getMetaData().getConfusionChance();
        if (willConfuse) {
            isConfused = target.addVolatileStatus(VolatileStatus.CONFUSION);
        }

        return isConfused;
    }
}

