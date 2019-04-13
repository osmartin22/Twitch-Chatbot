package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.pokemonBattle.TurnResult;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonField.PokemonBinding.PokeBinding;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;
import java.util.*;

// TODO: Create a class than can hold information on what happens in the battle
//  i.e. Pokemon was confused, flinched, stats were changed, hp restored/damaged
//  In the games, actions are displayed on screen as they occur
//  But to prevent message spam, actions will be grouped into a message and be sent after all of the actions have finished

// TODO: Doing a move should create an object with all of the information the move did
//  i.e. it missed/did X amount of damage, inflicted a status effect, critical
//  This object should then be taken and turned into a string to be displayed to users
//  Currently move effects are appended to a string builder as they occur
//  Moves that can hit multiple Poke should be handled differently

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
    public TurnResult doActions(List<PokeInBattle> switching, List<PokeInBattle> attacking) {
        sb.setLength(0);
        switchPokeIn(switching);
        Map<Long, Set<Integer>> faintedMap = attackingPokes(attacking);

        return new TurnResult(sb.toString(), faintedMap);
    }

    @Nonnull
    public PokeInBattle getPokeFromPosition(@Nonnull PokePosition pokePosition) {
        return sideList.get(pokePosition.getSidePosition())
                .getTrainerInBattle(pokePosition.getTrainerPosition())
                .getPokeInBattle(pokePosition.getFieldPosition());
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
            if (tb.getPokeInBattle(pb.getFieldPosition()).getTrainerAction() == TrainerAction.ACTION_SWITCH) {
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
    // TODO: This method should do more checks on the types of moves, since some should be handled differently
    //  i.e. semi-invulnerable/charging moves are only prevented by status effects on the turn first used
    //  Accuracy and damage are done on the next turn
    //  NOTE: Some moves target the user but damage an opponent (Thrash)
    public Map<Long, Set<Integer>> attackingPokes(@Nonnull List<PokeInBattle> attacking) {
        sortAttacking(attacking);
        Map<Long, Set<Integer>> faintedMap = new HashMap<>();

        for (PokeInBattle attacker : attacking) {
            if (attacker.getTrainerAction() == TrainerAction.ACTION_MOVE) {
                PokeMoveDamageClass damageClass = attacker.getMoveToUse().getDamageClass();
                if (damageClass == PokeMoveDamageClass.STATUS) {
                    // Status moves
                } else {
                    if (!willStatusPreventMove(attacker)) {
                        // TODO: Not sure when a move uses a PP point for a move, set here for now
                        //  I know that even if a move misses, PP is used
                        attacker.getMoveToUse().decrementPp();
                        if (accuracyCheck(attacker)) {
                            PokeMove move = attacker.getMoveToUse();
                            PokeInBattle target = getPokeFromPosition(attacker.getTargetPosition());
                            attacker.setLastUsedMove(move);
                            int damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
                            target.getPoke().lowerHp(damageDone);

                            sb.append(String.format("%s hit %s with %s for %s HP -> %s HP. ", attacker.getPoke().getName(),
                                    target.getPoke().getName(), move.getName(), damageDone, target.getPoke().getCurrHp()));

                            if (target.getPoke().isFainted()) {
                                addPokeToFaintedMap(faintedMap, target);
                                target.setTrainerAction(TrainerAction.ACTION_WAITING);
                                sb.append(String.format("%s has fainted. ", target.getPoke().getName()));
                            }

                            inflictStatuses(target, move);

                        } else {
                            sb.append(String.format("%s's attack missed. ", attacker.getPoke().getName()));
                            // Poke turn now over (Need to handle recoil damage on miss)
                        }
                    } else {
                        sb.append(String.format("%s was prevented from using its move. ", attacker.getPoke().getName()));
                    }
                }

                attacker.setTrainerAction(TrainerAction.ACTION_WAITING);
            }
        }

        return faintedMap;
    }

    /**
     * Adds the Poke to the fainted Poke with their key being its trainer's id
     * Also increments a trainers fainted Poke count
     *
     * @param faintedMap Map of all fainted Poke
     * @param pb         Poke to add the the map
     */
    private void addPokeToFaintedMap(@Nonnull Map<Long, Set<Integer>> faintedMap, @Nonnull PokeInBattle pb) {
        int position = pb.getFieldPosition();
        TrainerInBattle tb = getTrainerInBattle(pb);
        long id = tb.getTrainer().getId();
        tb.getTrainer().incrementFaintedPoke();
        Set<Integer> positions = faintedMap.get(id);

        if (positions != null) {
            positions.add(position);
        } else {
            faintedMap.put(id, new HashSet<>(Collections.singleton(position)));
        }
    }

    public boolean accuracyCheck(@Nonnull PokeInBattle attacker) {
        boolean moveWillHit;

        if (attacker.getPokePosition().equals(attacker.getTargetPosition())) {
            moveWillHit = true;
        } else {
            PokeMove move = attacker.getMoveToUse();
            PokeInBattle target = getPokeFromPosition(attacker.getTargetPosition());
            int attackerAcc = attacker.getStatStage(PokeStatStage.ACC_STAGE);
            int targetEva = target.getStatStage(PokeStatStage.EVA_STAGE);
            moveWillHit = calculator.willMoveHit(attackerAcc, targetEva, move);
        }

        return moveWillHit;
    }

    public boolean willStatusPreventMove(@Nonnull PokeInBattle attacker) {
        boolean nvStatusPrevented = calculator.nvStatusPreventsMove(attacker);
        boolean confusionPrevented = calculator.confusionActivates(attacker);
        if (confusionPrevented) {
            // TODO: Inflict Poke with a 40 power typeless physical attack
        }

        return nvStatusPrevented || confusionPrevented;
    }

    private void inflictStatuses(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        inflictNonVolatile(target, move);
        inflictConfusion(target, move);
        inflictFlinch(target, move);
    }

    // TODO: Some poison moves can turn Poison to Badly Poisoned, currently no taken into account
    private void inflictNonVolatile(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        NonVolatileStatus status = move.getMetaData().getNonVolatileStatus();
        if (status != NonVolatileStatus.NONE) {
            if (status == NonVolatileStatus.MULTIPLE) {
                // Only a handful of moves go here
                // Need to be handled separately
            } else {
                NonVolatileStatus nvStatus = target.getPoke().getNonVolatile();
                if (nvStatus == NonVolatileStatus.NONE && calculator.willEffectHit(move.getMetaData().getNonVolatileChance())) {
                    switch (status) {
                        case BURN:
                            if (!target.isTypeFound(PokeTypeEnum.FIRE)) {
                                target.getPoke().updateNonVolatile(status);
                                sb.append(String.format("%s was burnt.", target.getPoke().getName()));
                            }
                            break;
                        case FREEZE:
                            if (!target.isTypeFound(PokeTypeEnum.ICE)) {
                                target.getPoke().updateNonVolatile(status);
                                sb.append(String.format("%s was frozen.", target.getPoke().getName()));
                            }
                            break;
                        case PARALYSIS:
                            if (!target.isTypeFound(PokeTypeEnum.ELECTRIC)) {
                                target.getPoke().updateNonVolatile(status);
                                sb.append(String.format("%s was paralyzed.", target.getPoke().getName()));
                            }
                            break;
                        case POISON:
                            if (!target.isTypeFound(PokeTypeEnum.POISON) && !target.isTypeFound(PokeTypeEnum.STEEL)) {
                                target.getPoke().updateNonVolatile(status);
                                sb.append(String.format("%s was poisoned.", target.getPoke().getName()));
                            }
                            break;
                        case BADLY_POISONED:
                            if (!target.isTypeFound(PokeTypeEnum.POISON) && !target.isTypeFound(PokeTypeEnum.STEEL)) {
                                target.getPoke().updateNonVolatile(status);
                                sb.append(String.format("%s was badly poisoned.", target.getPoke().getName()));
                            }
                            break;
                        case SLEEP:
                            target.getPoke().updateNonVolatile(status);
                            sb.append(String.format("%s fell asleep.", target.getPoke().getName()));
                            break;
                        default:
                    }
                }
            }
        }
    }

    private void inflictFlinch(@Nonnull PokeInBattle target, @Nonnull PokeMove move) {
        // Flinch only occurs on Pokemon that have not yet attacked
        if (target.getTrainerAction() == TrainerAction.ACTION_MOVE) {
            boolean willFlinch = calculator.willEffectHit(move.getMetaData().getFlinchChance());
            if (willFlinch) {
                // Target flinches and can no longer attack its turn
                sb.append(String.format("%s was flinched. ", target.getPoke().getName()));
                target.setTrainerAction(TrainerAction.ACTION_WAITING);
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

    public void doBindingDamage(@Nonnull PokeInBattle pokeInBattle) {
        PokeBinding binding = pokeInBattle.getBinding();
        binding.doBindingDamage(pokeInBattle.getPoke());
    }

    /*
    Can Possibly create a Class that hold the information of a move used
    i.e. damaged Poke, stat stage changes, statuses inflicted
    then convert this into a string to output and append it to the string builder

    if(statusPrevented) {
        Status Prevented Move, return
    } else {
        if(willMiss) {
            Poke missed/Target evaded, return
        } else {
                doMove();
            damageTarget();
            if(moveHeals) {
                restoreHp();
            }

            afflictStatusEffects();
        }
    }
     */
}

