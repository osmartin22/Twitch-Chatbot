package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.PositionHelper;
import ozmar.pokemonBattle.TrainerChoice;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainerInBattle {
    private final Trainer trainer;
    private final List<PokeInBattle> pokeInBattleList;
    private boolean hasMegaEvolved; // Only one mega evolution is allowed per trainer in a battle


    public TrainerInBattle(@Nonnull Trainer trainer, @Nonnull PokeInBattle pokeInBattle) {
        this.trainer = trainer;
        this.hasMegaEvolved = false;

        this.pokeInBattleList = new ArrayList<>(1); // Currently only 1v1 battles
        this.pokeInBattleList.add(pokeInBattle);
    }


    public boolean hasMegaEvolved() {
        return hasMegaEvolved;
    }

    public List<PokeInBattle> getPokeInBattleList() {
        return pokeInBattleList;
    }

    @Nonnull
    public TrainerChoice getTrainerChoice(@NonNull PositionHelper positions) {
        return pokeInBattleList.get(positions.getField()).getTrainerChoice();
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(@NonNull PositionHelper positions) {
        return pokeInBattleList.get(positions.getField());
    }

    /**
     * Checks if the Pokemon on the field is able to switch out
     *
     * @param positions position on the field the pokemon is in
     * @return boolean
     */
    public boolean isAbleToSwitchPoke(@NonNull PositionHelper positions) {
        boolean isAbleToSwitch = true;
        PokeInBattle pokeInBattle = pokeInBattleList.get(positions.getField());
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

        pokeInBattleList.get(positions.getField()).setTrainerChoice(TrainerChoice.CHOICE_SWITCH);
        return isAbleToSwitch;
    }

    /**
     * Checks if the Pokemon on the field can do the selected move
     * i.e. Enough PP, or another effect prevents the move from being used
     *
     * @param positions position of the field and move to get the Poke from
     * @return boolean
     */
    public boolean isAbleToDoMove(@NonNull PositionHelper positions) {
        boolean isAbleToDoMove = true;
        PokeInBattle pokeInBattle = pokeInBattleList.get(positions.getField());
        PokeMove move = pokeInBattle.getPoke().getMoveList().get(positions.getMove());
        if (move.getCurrPp() == 0) {
            isAbleToDoMove = false;
        } else {
            // Check for any effects that prevent the move from being used
            pokeInBattle.setMoveToUse(move);
            pokeInBattleList.get(positions.getField()).setTrainerChoice(TrainerChoice.CHOICE_MOVE);
        }

        // TODO: Check if the Poke can do the selected Move
        //  Trainer status will switch to CHOICE_MOVE if able to do the move
        return isAbleToDoMove;
    }


    public void doTrainerChoice(@NonNull PositionHelper positions) {

    }

    public boolean setPokeToSwitchIn(@NonNull PositionHelper positions) {
        boolean canSwitchIn = false;
        Poke pokeToSwitchIn = trainer.getPokeList().get(positions.getPoke());
        if (!pokeToSwitchIn.isFainted()) {
            PokeInBattle pokeInBattle = pokeInBattleList.get(positions.getField());
            pokeInBattle.setPoke(pokeToSwitchIn);
            pokeInBattle.setTrainerChoice(TrainerChoice.CHOICE_SWITCH);
            canSwitchIn = true;
        }

        return canSwitchIn;
    }

    public void switchPoke(@NonNull PositionHelper positions) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(positions.getField());
        pokeInBattle.switchPoke();
        pokeInBattle.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
    }

    public void setMoveToUse(@NonNull PositionHelper positions) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(positions.getField());
        pokeInBattle.setMoveToUse(pokeInBattle.getPoke().getMoveList().get(positions.getMove()));
    }
}
