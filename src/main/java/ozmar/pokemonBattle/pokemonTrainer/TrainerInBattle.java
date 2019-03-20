package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.Set;

public class TrainerInBattle {
    private final Trainer trainer;
    private final PokeInBattle pokeInBattle;
    private TrainerChoice trainerChoice;
    private Poke pokeToSwitchIn;
    private boolean hasMegaEvolved; // Only one mega evolution is allowed per trainer in a battle


    public TrainerInBattle(@Nonnull Trainer trainer, @Nonnull PokeInBattle pokeInBattle) {
        this.trainer = trainer;
        this.pokeInBattle = pokeInBattle;
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
    public Poke getCurrPoke() {
        return pokeInBattle.getPoke();
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
        return isAbleToSwitch;
    }

    /**
     * Checks if the Pokemon on the field can do the selected move
     * i.e. Enough PP, or another effect prevents the move from being used
     *
     * @param move move selected
     * @return boolean
     */
    public boolean isAbleToDoMove(@Nonnull PokeMove move) {
        boolean isAbleToDoMove = true;
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // Check for any effects that prevent the move from being used
            pokeInBattle.setMoveToUse(move);
            trainerChoice = TrainerChoice.CHOICE_MOVE;
        }

        // TODO: Check if the Poke can do the selected Move
        //  Trainer status will switch to CHOICE_MOVE if able to do the move
        return isAbleToDoMove;
    }

    public void doChoice() {
        if (trainerChoice == TrainerChoice.CHOICE_SWITCH) {
            switchPoke();
        } else {
            pokeInBattle.doMove();
            // TODO: For moves that act over multiple turns, keep the status as CHOICE_MOVE instead of resetting
            //  i.e. charging/recharging or semi invulnerable moves
        }
        trainerChoice = TrainerChoice.CHOICE_WAITING;
    }

    public void setPokeToSwitchIn(@Nonnull Poke pokeToSwitchIn) {
        this.trainerChoice = TrainerChoice.CHOICE_SWITCH;
        this.pokeToSwitchIn = pokeToSwitchIn;
    }

    public void switchPoke() {
        pokeInBattle.setPoke(pokeToSwitchIn);
        trainerChoice = TrainerChoice.CHOICE_WAITING;
        pokeToSwitchIn = null;
        // TODO: Set the poke on pokeInBattle to the desired Poke
        //  Check if the statuses should be kept (Baton Pass) or reset
    }

    public void setMoveToUse(@Nonnull PokeMove moveToUse) {
        this.pokeInBattle.setMoveToUse(moveToUse);
    }
}
