package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.pokemonBattle.pokemon.Poke;
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

    /**
     * Sets the desired Poke to switch out with the Poke to switch in
     * Fainted Poke and the same Poke are prevented from switching in
     *
     * @param trainerInBattle trainer in battle trying to switch their Poke
     * @param fieldPosition   position on the field of the desired Poke to be switched out
     * @param pokePosition    position of the Poke on the trainers team to be switched in
     * @return boolean
     */
    public boolean setPokeToSwitchIn(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int pokePosition) {
        boolean canSwitchIn = false;
        PokeInBattle pb = trainerInBattle.getPokeInBattle(fieldPosition);
        if (pb.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
            if (isAbleToSwitchPoke(pb)) {
                Poke currentPoke = pb.getPoke();
                Poke pokeToSwitchIn = trainerInBattle.getTrainer().getPokeList().get(pokePosition);
                if (!pokeToSwitchIn.isFainted() && (currentPoke != pokeToSwitchIn)) {
                    canSwitchIn = true;
                    trainerInBattle.setPokeToSwitchIn(fieldPosition, pokePosition);
                }
            }
        }

        return canSwitchIn;
    }

    /**
     * Checks if the Poke currently on the field can switch out and is not prevented
     * from switching out by an effect on it or the field
     *
     * @param pokeInBattle Poke in battle to be checked
     * @return boolean
     */
    private boolean isAbleToSwitchPoke(@Nonnull PokeInBattle pokeInBattle) {
        boolean isAbleToSwitch = true;
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

    /**
     * Sets the desired move to be used
     * TODO: Check for effects that prevent the move from being used
     *
     * @param trainerInBattle trainer in battle trying to switch their Poke
     * @param fieldPosition position on the field of the desired Poke to be switched out
     * @param movePosition position of the move in the Poke's move set
     * @param targetPosition position of the target
     * @return boolean
     */
    public boolean setMoveToUse(@Nonnull TrainerInBattle trainerInBattle, int fieldPosition, int movePosition,
                                @Nonnull PokePosition targetPosition) {
        boolean isAbleToDoMove = false;
        PokeInBattle pb = trainerInBattle.getPokeInBattle(fieldPosition);
        if (pb.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = isAbleToDoMove(pb, movePosition, targetPosition);
            if (isAbleToDoMove) {
                trainerInBattle.setMoveToUse(fieldPosition, movePosition, targetPosition);
            }
        }

        return isAbleToDoMove;
    }

    /**
     * Checks if the Poke is prevented from using the move by an effect on it or the field
     *
     * @param pokeInBattle   Poke in battle to be checked
     * @param movePosition   position of the move in the Poke's move set
     * @param targetPosition position of the target
     * @return boolean
     */
    private boolean isAbleToDoMove(@Nonnull PokeInBattle pokeInBattle, int movePosition, @Nonnull PokePosition targetPosition) {
        boolean isAbleToDoMove = true;
        PokeMove move = pokeInBattle.getPoke().getMoveList().get(movePosition);
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // TODO: Check for any effects that prevent the move from being used
            pokeInBattle.setMoveToUse(move, targetPosition);
        }

        return isAbleToDoMove;
    }
}
