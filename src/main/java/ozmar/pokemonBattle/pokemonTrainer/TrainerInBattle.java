package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainerInBattle {
    private final int position;
    private final Trainer trainer;
    private final List<PokeInBattle> pokeInBattleList;
    private boolean hasMegaEvolved; // Only one mega evolution is allowed per trainer in a battle


    public TrainerInBattle(@Nonnull Trainer trainer, int position) {
        this.position = position;
        this.trainer = trainer;
        this.hasMegaEvolved = false;

        this.pokeInBattleList = new ArrayList<>(1); // Currently only 1v1 battles
        this.pokeInBattleList.add(new PokeInBattle(trainer.getPokeList().get(0), position));
    }


    public boolean hasMegaEvolved() {
        return hasMegaEvolved;
    }

    public List<PokeInBattle> getPokeInBattleList() {
        return pokeInBattleList;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int fieldPosition) {
        return pokeInBattleList.get(fieldPosition);
    }

    public boolean isAbleToSwitchPoke(int fieldPosition) {
        boolean isAbleToSwitch = true;
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        Set<VolatileBattleStatus> set = pokeInBattle.getVolatileBattleStatusList();
        if (set.contains(VolatileBattleStatus.CHARGING_TURN) || set.contains(VolatileBattleStatus.SEMI_INVULNERABLE) ||
                set.contains(VolatileBattleStatus.ROOTING) || set.contains(VolatileBattleStatus.RECHARGING) ||
                set.contains(VolatileBattleStatus.WITHDRAWING)) {
            isAbleToSwitch = false;
        }

        if (isAbleToSwitch) {
            Set<VolatileStatus> statusSet = pokeInBattle.getVolatileList();
            if (statusSet.contains(VolatileStatus.CANT_ESCAPE)) {
                isAbleToSwitch = false;
            } else if (statusSet.contains(VolatileStatus.BOUND) && !pokeInBattle.isTypeFound(PokeTypeEnum.GHOST)) {
                isAbleToSwitch = false;
            }
        }

        pokeInBattleList.get(fieldPosition).setTrainerChoice(TrainerChoice.CHOICE_SWITCH);
        return isAbleToSwitch;
    }

    public boolean isAbleToDoMove(int fieldPosition, int movePosition, @Nonnull PokeTargetPosition targetPosition) {
        boolean isAbleToDoMove = true;
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        PokeMove move = pokeInBattle.getPoke().getMoveList().get(movePosition);
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // Check for any effects that prevent the move from being used
            pokeInBattle.setMoveToUse(move, targetPosition);
            pokeInBattleList.get(fieldPosition).setTrainerChoice(TrainerChoice.CHOICE_MOVE);
        }

        // TODO: Check if the Poke can do the selected Move
        //  Trainer status will switch to CHOICE_MOVE if able to do the move
        return isAbleToDoMove;
    }


//    public void doTrainerChoice(@Nonnull PositionHelper positions) {
//
//    }

    public boolean setPokeToSwitchIn(int fieldPosition, int pokePosition) {
        boolean canSwitchIn = false;
        Poke pokeToSwitchIn = trainer.getPokeList().get(pokePosition);
        if (!pokeToSwitchIn.isFainted()) {
            PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
            pokeInBattle.setPokeToSwitchIn(pokeToSwitchIn);
            pokeInBattle.setTrainerChoice(TrainerChoice.CHOICE_SWITCH);
            canSwitchIn = true;
        }

        return canSwitchIn;
    }

    public void switchPoke(int fieldPosition) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        pokeInBattle.switchPoke();
        pokeInBattle.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
    }

    public void setMoveToUse(int fieldPosition, int movePosition, @Nonnull PokeTargetPosition targetPosition) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        pokeInBattle.setMoveToUse(pokeInBattle.getPoke().getMoveList().get(movePosition), targetPosition);
    }
}
