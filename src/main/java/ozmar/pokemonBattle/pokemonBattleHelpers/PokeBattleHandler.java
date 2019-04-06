package ozmar.pokemonBattle.pokemonBattleHelpers;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// TODO: Create a class than can hold information on what happens in the battle
//  i.e. Pokemon was confused, flinched, stats were changed, hp restored/damaged
//  In the games, actions are displayed on screen as they occur
//  But to prevent message spam, actions will be grouped into a message and be sent after all of the actions have finished

// *NOTE This only supports 1 v 1 battles
public class PokeBattleHandler {
    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeBattleCalculator calculator;
    private final List<PokeInBattle> switching;
    private final List<PokeInBattle> attacking;
    private StringBuilder sb;

    private final Set<TrainerInBattle> tbSet;

    public PokeBattleHandler(@Nonnull PokeField field, @Nonnull List<PokeTrainerSide> sideList,
                             @Nonnull Set<TrainerInBattle> tbSet) {
        this.field = field;
        this.sideList = sideList;
        this.calculator = new PokeBattleCalculator();
        this.switching = new ArrayList<>();
        this.attacking = new ArrayList<>();
        this.sb = new StringBuilder();

        this.tbSet = tbSet;
    }

    // TODO: CREATE A METHOD THAT ONLY SWITCHES POKEMON IN
    public String test() {
        sb.setLength(0);

        for (TrainerInBattle tb : tbSet) {
            if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            }
        }

        switchPokeIn(switching);
        switching.clear();
        return sb.toString();
    }

    @Nonnull
    public String doTrainerChoices() {
        sb.setLength(0);

        for (TrainerInBattle tb : tbSet) {
            if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                switching.add(tb.getPokeInBattle(0));
            } else {
                attacking.add(tb.getPokeInBattle(0));
            }
        }

        switchPokeIn(switching);
        attackingPokes(attacking);

        switching.clear();
        attacking.clear();
        return sb.toString();
    }

    // Sort Pokemon switching by speed
    private void sortSwitching(@Nonnull List<PokeInBattle> switching) {
        switching.sort((o1, o2) -> {
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

    // Sort Pokemon doing a move by move priority first followed by speed if moves have the same priority
    private void sortAttacking(@Nonnull List<PokeInBattle> attacking) {
        attacking.sort((o1, o2) -> {
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

    private void attackingPokes(@Nonnull List<PokeInBattle> attacking) {
        sortAttacking(attacking);
        // Currently ignoring status moves as they have a lot of unique effects to take into account
        for (PokeInBattle pb : attacking) {
            if (pb.getTrainerChoice() == TrainerChoice.CHOICE_MOVE &&
                    pb.getMoveToUse().getDamageClass() != PokeMoveDamageClass.STATUS) {

                int attackAcc = pb.getStatStage(PokeStatStage.ACC_STAGE);
                int targetEva = pb.getStatStage(PokeStatStage.EVA_STAGE);
                if (!calculator.nvStatusPreventsMove(pb)) {
                    if (calculator.willMoveHit(attackAcc, targetEva, pb.getMoveToUse())) {
                        int damageDone = doMove(pb);
//                        PokeInBattle target = getPokeInBattle(pb);
//                        target.getPoke().getPokeStats().updateCurrHp(-damageDone);

                    } else {
                        sb.append(String.format("%s missed. ", pb.getPoke().getName()));
                    }
                } else {
                    sb.append(String.format("%s was prevented by a status effect. ", pb.getPoke().getName()));
                }

                pb.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }
    }

    private int doMove(@Nonnull PokeInBattle attacker) {
        int damageDone = 0;

        PokeMove move = attacker.getMoveToUse();
        PokePosition targetPosition = attacker.getTargetPosition();
        PokeInBattle target = sideList.get(targetPosition.getSidePosition())
                .getTrainerInBattle(targetPosition.getTrainerPosition())
                .getPokeInBattle(targetPosition.getFieldPosition());

        if (attacker.getMoveToUse().getDamageClass() == PokeMoveDamageClass.STATUS) {
            damageDone = -1;
        } else {

            boolean isMovePrevented = isMovePrevented(attacker);
            if (!isMovePrevented) {
                boolean moveWillHit = willMoveHit(attacker, target, move);
                if (moveWillHit) {
                    attacker.setLastUsedMove(attacker.getMoveToUse());
                    damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
                    target.getPoke().lowerHp(damageDone);
                    sb.append(String.format("%s attacked %s for %s damage. ", attacker.getPoke().getName(),
                            target.getPoke().getName(), damageDone));
                    if (target.getPoke().isFainted()) {
                        target.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
                        sb.append(String.format("%s has fainted. ", target.getPoke().getName()));
                    }

                    inflictStatuses(target, move);
                } else {
                    sb.append(String.format("%s's attack missed", attacker.getPoke().getName()));
                    // Move Missed
                }
            } else {
                sb.append(String.format("%s was prevented from using its move", attacker.getPoke().getName()));
                // Pokemon prevented from attacking
            }
        }

        attacker.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        return damageDone;
    }

    private void switchPokeIn(@Nonnull List<PokeInBattle> pbList) {
        sortSwitching(pbList);
        for (PokeInBattle pb : pbList) {
            TrainerInBattle tb = sideList.get(pb.getSidePosition()).getTrainerInBattle(pb.getTrainerPosition());
            if (tb.getPokeInBattle(pb.getFieldPosition()).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                sb.append(String.format("%s switched out for %s. ", pb.getPoke().getName(),
                        pb.getPokeToSwitchIn().getName()));
                tb.switchPoke(pb.getFieldPosition());

                PokeSide side = tb.getSide();
                if (side.getEntryHazard().isEntryHazardPresent()) {
                    side.getEntryHazard().doEntryHazardEffect(pb);
                }
            }
        }
    }

    private PokeInBattle getPokeInBattle(@Nonnull PokeInBattle pb) {
        return sideList.get(pb.getSidePosition())
                .getTrainerInBattle(pb.getTrainerPosition())
                .getPokeInBattle(pb.getFieldPosition());
    }

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

