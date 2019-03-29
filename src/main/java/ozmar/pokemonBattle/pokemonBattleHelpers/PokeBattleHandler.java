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
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PokeBattleHandler {
    private final PokeField field;
    private final List<PokeTrainerSide> sideList;
    private final PokeBattleCalculator calculator;

    public PokeBattleHandler(@Nonnull PokeField field, @Nonnull List<PokeTrainerSide> sideList) {
        this.field = field;
        this.sideList = sideList;
        this.calculator = new PokeBattleCalculator();
    }

    public void doTrainerChoices() {
        List<PokeInBattle> switching = new ArrayList<>();
        List<PokeInBattle> attacking = new ArrayList<>();
        for (PokeTrainerSide pSide : sideList) {
            List<TrainerInBattle> tbList = sideList.get(pSide.getSideId()).getTrainerInBattleList();
            for (TrainerInBattle tb : tbList) {
                if (tb.getPokeInBattle(0).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                    switching.add(tb.getPokeInBattle(0));
                } else {
                    attacking.add(tb.getPokeInBattle(0));
                }
            }
        }

        switchPokes(switching);
        attackingPokes(attacking);
    }

    // Sort Pokemon switching by speed and then switch them out
    private void switchPokes(@Nonnull List<PokeInBattle> switching) {
        switching.sort((o1, o2) -> {
            int speed1 = o1.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD);
            if (o1.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                speed1 /= 2;
            }
            int speed2 = o2.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD);
            if (o2.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                speed2 /= 2;
            }
            return speed1 - speed2;
        });
        switchPokeIn(switching);
    }

    // Sort Pokemon doing a move by move priority first followed by speed if moves have the same priority
    private void attackingPokes(@Nonnull List<PokeInBattle> attacking) {
        attacking.sort((o1, o2) -> {
            int result = (o2.getMoveToUse().getPriority()) - o1.getMoveToUse().getPriority();
            if (result == 0) {
                int speed1 = o1.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD);
                if (o1.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                    speed1 /= 2;
                }
                int speed2 = o2.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD);
                if (o2.getPoke().getNonVolatile() == NonVolatileStatus.PARALYSIS) {
                    speed2 /= 2;
                }
                result = speed2 - speed1;
            }

            return result;
        });

        // Currently ignoring status moves as they have a lot of unique effects to take into account
        for (PokeInBattle pb : attacking) {
            if (pb.getTrainerChoice() == TrainerChoice.CHOICE_MOVE &&
                    pb.getMoveToUse().getDamageClass() != PokeMoveDamageClass.STATUS) {

                int attackAcc = pb.getPokeStages().getStateStage(PokeStatStage.ACC_STAGE);
                int targetEva = pb.getPokeStages().getStateStage(PokeStatStage.EVA_STAGE);
                if (!calculator.statusPreventsMove(pb)) {
                    if (calculator.willMoveHit(attackAcc, targetEva, pb.getMoveToUse())) {
                        int damageDone = doMove(pb);
                        System.out.println(pb.getPoke().getName() + " Damage Done: " + damageDone);
                        PokeInBattle target = getPokeInBattle(pb);
                        target.getPoke().getPokeStats().updateCurrHp(-damageDone);

                    } else {
                        System.out.println("Move missed");
                    }
                } else {
                    System.out.println("Status prevented move");
                }

                pb.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }
    }

    private int doMove(@Nonnull PokeInBattle attacker) {
        System.out.println("Attacker is : " + attacker.getPoke().getName());
        int damageDone = 0;

        PokeMove move = attacker.getMoveToUse();
        PokeTargetPosition targetPosition = attacker.getTargetPosition();
        PokeInBattle target = sideList.get(targetPosition.getSidePosition())
                .getTrainerInBattle(targetPosition.getTrainerPosition())
                .getPokeInBattle(targetPosition.getFieldPosition());

        if (attacker.getMoveToUse().getDamageClass() == PokeMoveDamageClass.STATUS) {
            damageDone = -1;
        } else {
            boolean willMoveHit = calculator.willMoveHit(attacker.getPokeStages().getStateStage(PokeStatStage.ACC_STAGE),
                    target.getPokeStages().getStateStage(PokeStatStage.EVA_STAGE), move);

            if (willMoveHit) {
                attacker.setLastUsedMove(attacker.getMoveToUse());
                damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
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

                boolean willFlinch = calculator.willEffectHit(move.getMetaData().getFlinchChance());
                if (willFlinch) {
                    // Target flinches and can no longer attack its turn
                    System.out.println(target.getPoke().getName() + " was flinched");
                    target.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
                }
            }
        }

        attacker.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        return damageDone;
    }

    private void switchPokeIn(@Nonnull List<PokeInBattle> pbList) {
        for (PokeInBattle pb : pbList) {
            TrainerInBattle tb = sideList.get(pb.getSidePosition()).getTrainerInBattle(pb.getTrainerPosition());
            if (tb.getPokeInBattle(pb.getFieldPosition()).getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                tb.switchPoke(pb.getFieldPosition());
                PokeSide side = sideList.get(pb.getSidePosition()).getSide();
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
}

