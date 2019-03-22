package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainerInBattle {
    private final Trainer trainer;
    private final List<PokeInBattle> pokeInBattleList;
    private TrainerChoice trainerChoice;
    private Poke pokeToSwitchIn;
    private boolean hasMegaEvolved; // Only one mega evolution is allowed per trainer in a battle


    public TrainerInBattle(@Nonnull Trainer trainer, @Nonnull PokeInBattle pokeInBattle) {
        this.trainer = trainer;
        this.pokeInBattleList = new ArrayList<>(1);
        this.pokeInBattleList.add(pokeInBattle);
        this.trainerChoice = TrainerChoice.CHOICE_WAITING;
        this.pokeToSwitchIn = null;
        this.hasMegaEvolved = false;
    }


    public boolean hasMegaEvolved() {
        return hasMegaEvolved;
    }


    @Nonnull
    public TrainerChoice getCurrStatus() {
        return trainerChoice;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int fieldPosition) {
        return pokeInBattleList.get(fieldPosition);
    }

    /**
     * Checks if the Pokemon on the field is able to switch out
     *
     * @param fieldPosition position on the field the pokemon is in
     * @return boolean
     */
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
            } else if (statusSet.contains(VolatileStatus.BOUND) && !pokeInBattle.getPoke().getType().isTypeFound(PokeTypeEnum.GHOST)) {
                isAbleToSwitch = false;
            }
        }

        trainerChoice = TrainerChoice.CHOICE_SWITCH;
        return isAbleToSwitch;
    }

    /**
     * Checks if the Pokemon on the field can do the selected move
     * i.e. Enough PP, or another effect prevents the move from being used
     *
     * @param movePosition position of the selected move
     * @param fieldPosition position on the field the pokemon is in
     * @return boolean
     */
    public boolean isAbleToDoMove(int movePosition, int fieldPosition) {
        boolean isAbleToDoMove = true;
        PokeMove move = pokeInBattleList.get(fieldPosition).getPoke().getMoveList().get(movePosition);
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // Check for any effects that prevent the move from being used
            pokeInBattleList.get(fieldPosition).setMoveToUse(move);
            trainerChoice = TrainerChoice.CHOICE_MOVE;
        }

        // TODO: Check if the Poke can do the selected Move
        //  Trainer status will switch to CHOICE_MOVE if able to do the move
        return isAbleToDoMove;
    }

    public void doChoice(int fieldPosition) {
        if (trainerChoice == TrainerChoice.CHOICE_SWITCH) {
            switchPoke(fieldPosition);
        } else {
            pokeInBattleList.get(fieldPosition).doMove();
            // TODO: For moves that act over multiple turns, keep the status as CHOICE_MOVE instead of resetting
            //  i.e. charging/recharging or semi invulnerable moves
        }
        trainerChoice = TrainerChoice.CHOICE_WAITING;
    }

    public boolean setPokeToSwitchIn(int pokePosition) {
        boolean canSwitchIn = false;
        Poke pokeToSwitchIn = trainer.getPokeList().get(pokePosition);
        if (!pokeToSwitchIn.isFainted()) {
            this.trainerChoice = TrainerChoice.CHOICE_SWITCH;
            this.pokeToSwitchIn = pokeToSwitchIn;
            canSwitchIn = true;
        }

        return canSwitchIn;
    }

    public void switchPoke(int fieldPosition) {
        pokeInBattleList.get(fieldPosition).setPoke(pokeToSwitchIn);
        trainerChoice = TrainerChoice.CHOICE_WAITING;
        pokeToSwitchIn = null;
        // TODO: Set the poke on pokeInBattle to the desired Poke
        //  Check if the statuses should be kept (Baton Pass) or reset
    }

    public void setMoveToUse(int movePosition, int fieldPosition) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        pokeInBattle.setMoveToUse(pokeInBattle.getPoke().getMoveList().get(movePosition));
        this.trainerChoice = TrainerChoice.CHOICE_MOVE;
    }

    public void doNonVolatileStatusEffect() {
        for (PokeInBattle poke : pokeInBattleList) {
            // For each Pokemon in the battle, do their status effect
        }
    }
}
