package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class PokeRules {

    public PokeRules() {

    }

    public boolean setPokeToSwitchIn(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int pokePosition) {
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
            isAbleToSwitch = isAbleToSwitchPoke(trainerInBattle, fieldPosition);
        }

        return isAbleToSwitch;
    }

    private boolean isAbleToSwitchPoke(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition) {
        boolean isAbleToSwitch = true;
        PokeInBattle pokeInBattle = trainerInBattle.getPokeInBattle(fieldPosition);
        Set<VolatileBattleStatus> set = pokeInBattle.getVolatileBattleStatusList();
        if (set.contains(VolatileBattleStatus.CHARGING_TURN) || set.contains(VolatileBattleStatus.SEMI_INVULNERABLE) ||
                set.contains(VolatileBattleStatus.ROOTING) || set.contains(VolatileBattleStatus.RECHARGING) ||
                set.contains(VolatileBattleStatus.WITHDRAWING)) {
            isAbleToSwitch = false;
        }

        if (isAbleToSwitch) {
            Map<VolatileStatus, Integer> statusMap = pokeInBattle.getVolatileStatusMap();
            if (statusMap.containsKey(VolatileStatus.CANT_ESCAPE)) {
                isAbleToSwitch = false;
            } else if (statusMap.containsKey(VolatileStatus.BOUND) && !pokeInBattle.isTypeFound(PokeTypeEnum.GHOST)) {
                isAbleToSwitch = false;
            }
        }

        return isAbleToSwitch;
    }

    public boolean setMoveToUse(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int movePosition,
                                @Nonnull PokePosition targetPosition) {
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainerInBattle, fieldPosition, movePosition, targetPosition);
        if (isAbleToDoMove) {
            trainerInBattle.setMoveToUse(fieldPosition, movePosition, targetPosition);
        }

        return isAbleToDoMove;
    }

    private boolean canTrainersPokeUseMove(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int movePosition,
                                           @Nonnull PokePosition targetPosition) {
        boolean isAbleToDoMove = false;
        PokeInBattle pb = trainerInBattle.getPokeInBattle(fieldPosition);
        if (pb.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = isAbleToDoMove(trainerInBattle, fieldPosition, movePosition, targetPosition);
        }
        return isAbleToDoMove;
    }

    public boolean isAbleToDoMove(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int movePosition,
                                  @Nonnull PokePosition targetPosition) {
        boolean isAbleToDoMove = true;
        PokeInBattle pokeInBattle = trainerInBattle.getPokeInBattle(fieldPosition);
        PokeMove move = pokeInBattle.getPoke().getMoveList().get(movePosition);
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // Check for any effects that prevent the move from being used
            pokeInBattle.setMoveToUse(move, targetPosition);
            trainerInBattle.getPokeInBattle(fieldPosition).setTrainerChoice(TrainerChoice.CHOICE_MOVE);
        }

        return isAbleToDoMove;
    }
}
