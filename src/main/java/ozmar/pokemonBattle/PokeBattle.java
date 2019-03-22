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
    private final PokeBattleCalculator calculator;
    // Maybe a last used move and last pokemon that attacked for the entire battle

    public PokeBattle(@Nonnull Trainer red, @Nonnull Poke redPoke, @Nonnull Trainer blue, @Nonnull Poke bluePoke) {
        this.trainerSideMap = new HashMap<>();
        initialize(red, redPoke);
        initialize(blue, bluePoke);
        this.field = new PokeField();
        this.calculator = new PokeBattleCalculator();
    }

    private void initialize(@Nonnull Trainer trainer, @Nonnull Poke startingPoke) {
        PokeInBattle pokeInBattle = new PokeInBattle(startingPoke);
        TrainerInBattle trainerInBattle = new TrainerInBattle(trainer, pokeInBattle);
        PokeTrainerSide trainerSide = new PokeTrainerSide(trainerInBattle, new PokeSide());
        trainerSideMap.put(trainer, trainerSide);
    }


    /**
     * Attempts to set a Pokemon's move
     * Failing to set a move returns false
     *
     * @param trainer       Trainer choosing the move
     * @param movePosition  Position of the move in the Pokemon's move list
     * @param fieldPosition Position in the field the Pokemon is in
     * @return boolean
     */
    public boolean setMoveToUse(@Nonnull Trainer trainer, int movePosition, int fieldPosition) {
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainer, movePosition, fieldPosition);
        if (isAbleToDoMove) {
            trainerSideMap.get(trainer).setMoveToUse(movePosition, fieldPosition);
        }
        return isAbleToDoMove;
    }

    /**
     * Attempts to set a Pokemon to switch in
     * Failing to be able to switch returns false
     *
     * @param trainer       Trainer switching their Poke
     * @param pokePosition  Position of the Poke in the Trainer's Poke list
     * @param fieldPosition Position on the field the Poke is in
     * @return boolean
     */
    public boolean setPokeToSwitchIn(@Nonnull Trainer trainer, int pokePosition, int fieldPosition) {
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainer, fieldPosition);
        if (isAbleToSwitch) {
            isAbleToSwitch = trainerSideMap.get(trainer).setPokeToSwitchIn(pokePosition);
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(@Nonnull Trainer trainer, int fieldPosition) {
        PokeTrainerSide side = trainerSideMap.get(trainer);
        return side.getPokeInBattle(fieldPosition);
    }

    /**
     * Checks if the Poke on the field can do the selected move
     *
     * @param trainer       Trainer choosing the move
     * @param movePosition  Position of the desired move
     * @param fieldPosition Position on the field the pokemon is in
     * @return boolean
     */
    private boolean canTrainersPokeUseMove(@Nonnull Trainer trainer, int movePosition, int fieldPosition) {
        boolean isAbleToDoMove = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = side.isAbleToDoMove(movePosition, fieldPosition);
        }
        return isAbleToDoMove;
    }

    /**
     * Checks if the Pokemon on the field is prevented from switching out
     *
     * @param trainer       Trainer switching Poke
     * @param fieldPosition position on the field the pokemon is in
     * @return boolean
     */
    private boolean canTrainerSwitchPoke(@Nonnull Trainer trainer, int fieldPosition) {
        boolean isAbleToSwitch = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = side.isAbleToSwitchPoke(fieldPosition);
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
    public void doTrainerChoice(int fieldPosition) {
        for (PokeTrainerSide side : trainerSideMap.values()) {
            side.doChoice(fieldPosition);
        }
        // Execute the trainers choices if trainersReady() == true
    }

    private void pursuitisInvolved() {
/*
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

    /**
     * Checks each Pokemon and does any effects that occur after each Pokemon
     * has attempted to switch out or do a move
     */
    private void doPostTurnEffects() {

    }
}
