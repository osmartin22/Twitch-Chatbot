package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

// Pursuit is the only move that goes before the target switches out
public class PokeBattle {

    private final PokeField field;
    private Map<Trainer, PokeTrainerSide> trainerSideMap;

    public PokeBattle(@Nonnull Trainer red, @Nonnull Poke redPoke, @Nonnull Trainer blue, @Nonnull Poke bluePoke) {
        this.trainerSideMap = new HashMap<>();
        initialize(red, redPoke);
        initialize(blue, bluePoke);
        this.field = new PokeField();
    }

    private void initialize(@Nonnull Trainer trainer, @Nonnull Poke startingPoke) {
        PokeInBattle pokeInBattle = new PokeInBattle(startingPoke);
        TrainerInBattle trainerInBattle = new TrainerInBattle(trainer, pokeInBattle);
        PokeTrainerSide trainerSide = new PokeTrainerSide(trainerInBattle, new PokeSide());
        trainerSideMap.put(trainer, trainerSide);
    }


    public boolean setMoveToUse(@Nonnull Trainer trainer, int movePosition) {
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainer, movePosition);
        if (isAbleToDoMove) {
            trainerSideMap.get(trainer).setMoveToUse(movePosition);
        }
        return isAbleToDoMove;
    }

    /**
     * Attempts to set a Pokemon to switch in and returns whether the Pokemon can switch out or not
     *
     * @param trainer      Trainer switching their Poke
     * @param pokePosition position of the Poke in the Trainer's Poke list
     * @return boolean
     */
    public boolean setPokeToSwitchIn(@Nonnull Trainer trainer, int pokePosition) {
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainer);
        if (isAbleToSwitch) {
            isAbleToSwitch = trainerSideMap.get(trainer).setPokeToSwitchIn(pokePosition);
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public Poke getCurrPoke(@Nonnull Trainer trainer) {
        PokeTrainerSide side = trainerSideMap.get(trainer);
        return side.getCurrPoke();
    }

    /**
     * Checks if the Poke on the field can do the selected move
     *
     * @param trainer      Trainer choosing the move
     * @param movePosition position of the desired move
     * @return boolean
     */
    private boolean canTrainersPokeUseMove(@Nonnull Trainer trainer, int movePosition) {
        boolean isAbleToDoMove = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = side.isAbleToDoMove(movePosition);
        }
        return isAbleToDoMove;
    }

    /**
     * Checks if the Pokemon on the field is prevented from switching out
     *
     * @param trainer Trainer switching Poke
     * @return boolean
     */
    private boolean canTrainerSwitchPoke(@Nonnull Trainer trainer) {
        boolean isAbleToSwitch = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = side.isAbleToSwitchPoke();
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public TrainerChoice getTrainerStatus(@Nonnull Trainer trainer) {
        PokeTrainerSide side = trainerSideMap.get(trainer);
        return side.getCurrStatus();
    }

    /**
     * Checks if all trainers are ready, i.e. have chosen to switch their Poke or do a move
     *
     * @return boolean
     */
    public boolean trainersReady() {
        boolean trainersReady = true;
        for (PokeTrainerSide side : trainerSideMap.values()) {
            if (side.getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
                trainersReady = false;
                break;
            }
        }

        return trainersReady;
    }

    // TODO: Check if one of the Pokemon will use pursuit before switching out
    //  Pursuit activates if the Poke manually switches out,
    //  or uses U-turn, Volt Switch, or Parting Shot and would attack second
    // NOTE* If both trainers switch out on the same turn, the faster pokemon switches out first
    public void doTrainerChoice() {
        for (PokeTrainerSide side : trainerSideMap.values()) {
            side.doChoice();
        }
        // Execute the trainers choices if trainersReady() == true
    }

    private void pursuitisInvolved() {
/* Idea
2v2 battles is more complex (Even if I write this for 2v2, I would have to change other parts to work with 2v2)
1v1 battles
Check if any of the trainers have selected Pursuit

if(selected pursuit) {
    Check what the other trainer chose
    if(other trainer is switching || uses the switching moves) {
        Do special execution
        Pursuit 2x damage
        Poke switches out
        turn ends

    } else {
        Proceed as normal
    }
} else {
    proceed as normal
}


 */
    }

    private void doNonVolatileStatusDamage() {

    }
}
