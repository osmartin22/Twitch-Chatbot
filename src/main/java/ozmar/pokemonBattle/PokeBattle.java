package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleCalculator;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PokeBattle {

    private final PokeField field;
    private final PokeBattleCalculator calculator;
    private final List<PokeTrainerSide> sideList;

    public PokeBattle(@Nonnull List<List<Trainer>> listList) {
        this.field = new PokeField();
        this.calculator = new PokeBattleCalculator();
        this.sideList = new ArrayList<>();

        for (int i = 0; i < listList.size(); i++) {
            List<Trainer> trainerList = listList.get(i);
            for (int j = 0; j < trainerList.size(); j++) {
                TrainerInBattle tb = new TrainerInBattle(trainerList.get(j), j);
                this.sideList.add(new PokeTrainerSide(tb, i));
            }
        }
    }

    public boolean setMoveToUse(int sidePosition, int trainerPosition, int fieldPosition, int movePosition,
                                @Nonnull PokeTargetPosition targetPosition) {
        TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainerInBattle, fieldPosition, movePosition, targetPosition);
        if (isAbleToDoMove) {
            trainerInBattle.setMoveToUse(fieldPosition, movePosition, targetPosition);
        }
        return isAbleToDoMove;
    }

    private boolean canTrainersPokeUseMove(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int movePosition,
                                           @Nonnull PokeTargetPosition targetPosition) {
        boolean isAbleToDoMove = false;
        PokeInBattle pb = trainerInBattle.getPokeInBattle(fieldPosition);
        if (pb.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = trainerInBattle.isAbleToDoMove(fieldPosition, movePosition, targetPosition);
        }
        return isAbleToDoMove;
    }

    public boolean setPokeToSwitchIn(int sidePosition, int trainerPosition, int fieldPosition, int pokePosition) {
        TrainerInBattle trainerInBattle = sideList.get(sidePosition).getTrainerInBattle(trainerPosition);
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainerInBattle, fieldPosition);
        if (isAbleToSwitch) {
            isAbleToSwitch = trainerInBattle.setPokeToSwitchIn(fieldPosition, pokePosition);
        }

        return isAbleToSwitch;
    }

    private boolean canTrainerSwitchPoke(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition) {
        boolean isAbleToSwitch = false;
        PokeInBattle pb = trainerInBattle.getPokeInBattle(fieldPosition);
        if (pb.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = trainerInBattle.isAbleToSwitchPoke(fieldPosition);
        }

        return isAbleToSwitch;
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

    // This method only works for 1v1 battles and is currently only a basic version of actual Pokemon moves
    // This method DOES NOT take into account Pursuit attacking before a Pokemon switches
    // This method DOES NOT afflict statuses
    public void doTrainerChoice() {

        List<PokeInBattle> pbList = new ArrayList<>();
        for (PokeTrainerSide pSide : sideList) {
            List<TrainerInBattle> tbList = sideList.get(pSide.getSideId()).getTrainerInBattleList();
            for (TrainerInBattle tb : tbList) {
                pbList.add(tb.getPokeInBattle(0));
            }
        }

        pbList.sort(Comparator.comparingInt((PokeInBattle p)
                -> p.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD)).reversed());

        for (PokeInBattle pb : pbList) {
            if (pb.getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                pb.switchPoke();
                pb.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }

        for (PokeInBattle pb : pbList) {
            if (pb.getTrainerChoice() == TrainerChoice.CHOICE_MOVE &&
                    pb.getMoveToUse().getDamageClass() != PokeMoveDamageClass.STATUS) {
                System.out.println(pb.getMoveToUse().getName());
                System.out.println(pb.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD));
                int damageDone = doMove(pb);
                System.out.println(damageDone);
                pb.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
            }
        }
    }


    private int doMove(@Nonnull PokeInBattle attacker) {
        int damageDone = 0;

        PokeTargetPosition targetPosition = attacker.getTargetPosition();
        PokeInBattle target = sideList.get(targetPosition.getSidePosition())
                .getTrainerInBattle(targetPosition.getTrainerPosition())
                .getPokeInBattle(targetPosition.getFieldPosition());

        if (attacker.getMoveToUse().getDamageClass() == PokeMoveDamageClass.STATUS) {
            damageDone = -1;
        } else {
            boolean willMoveHit = calculator.willMoveHit(attacker.getPokeStages().getStateStage(PokeStatStage.ACC_STAGE),
                    target.getPokeStages().getStateStage(PokeStatStage.EVA_STAGE), attacker.getMoveToUse());

            if (willMoveHit) {
                damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
            }
        }

        attacker.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        return damageDone;
    }
}
