package ozmar.PokemonBattle.PokemonTrainer;

import ozmar.PokemonBattle.Poke;
import ozmar.PokemonBattle.PokeInBattle;
import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonStatusConditions.VolatileBattleStatus;
import ozmar.PokemonBattle.PokemonStatusConditions.VolatileStatus;
import ozmar.PokemonBattle.PokemonType.PokeTypeEnum;
import ozmar.PokemonBattle.TrainerChoice;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainerInBattle {
    private final Trainer trainer;
    private PokeInBattle pokeInBattle;
    private TrainerChoice trainerChoice;
    private Poke pokeToSwitchIn;


    public TrainerInBattle(@Nonnull Trainer trainer, @Nonnull PokeInBattle pokeInBattle) {
        this.trainer = trainer;
        this.pokeInBattle = pokeInBattle;
        this.trainerChoice = TrainerChoice.CHOICE_WAITING;
        this.pokeToSwitchIn = null;
    }

    // Should get the pokeList's info, like it's health, nature, stat stages, so the user can see their Pokemon
    public void getCurrentPokeInfo() {
        // TODO:
    }

    public List<PokeMove> getCurrPokeMoves() {
        // TODO:
        return new ArrayList<>();
    }

    // Should get the info of Pokemon not in the battle, along with the moves possibly
    public void getOtherPokeInfo() {
        // TODO:
    }

    // Makes the Pokemon do the selected move (check if the Pokemon knows the move first
    public void doMove() {
        // TODO:
        trainerChoice = TrainerChoice.CHOICE_MOVE;
    }

    public TrainerChoice getCurrStatus() {
        return trainerChoice;
    }


    /**
     * Checks if the Pokemon on the field is able to switch out
     *
     * @return boolean
     */
    public boolean isAbleToSwitchPoke() {
        boolean isAbleToSwitch = true;
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
        return false;
    }

    /**
     * Checks if the Pokemon on the field can do the selected move
     * i.e. Enough PP, or another effect prevents the move from being used
     *
     * @param move move selected
     * @return boolean
     */
    public boolean isAbleToDoMove(PokeMove move) {
        boolean isAbleToDoMove = true;
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {

            // Check for any effects that prevent the move from being used
        }

        // TODO: Check if the Poke can do the selected Move
        //  Trainer status will switch to CHOICE_MOVE if able to do the move
        return isAbleToDoMove;
    }

    public void switchPoke() {
        pokeInBattle = new PokeInBattle(pokeToSwitchIn);
    }
}
