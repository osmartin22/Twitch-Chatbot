package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
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


    public boolean setMove(@Nonnull Trainer trainer, @Nonnull PokeMove move) {
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainer, move);
        if (isAbleToDoMove) {
            // Set the pokemon's move
            // Set status to CHOICE_MOVE
        }

        return isAbleToDoMove;
    }

    /**
     * Attempts to set a Pokemon to switch in and returns whether the Pokemon can switch out or not
     *
     * @param trainer Trainer switching their Poke
     * @param poke    Poke to switch in
     * @return boolean
     */
    public boolean setPokeToSwitchIn(@Nonnull Trainer trainer, @Nonnull Poke poke) {
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainer);
        if (isAbleToSwitch) {
            trainerSideMap.get(trainer).setPokeToSwitchIn(poke);
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
     * @param trainer Trainer choosing the move
     * @param move    move the Poke will use
     * @return boolean
     */
    private boolean canTrainersPokeUseMove(@Nonnull Trainer trainer, @Nonnull PokeMove move) {
        boolean isAbleToDoMove = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getTrainerInBattle().getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = side.getTrainerInBattle().isAbleToDoMove(move);
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
        if (side.getTrainerInBattle().getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = side.getTrainerInBattle().isAbleToSwitchPoke();
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public TrainerChoice getTrainerStatus(@Nonnull Trainer trainer) {
        PokeTrainerSide side = trainerSideMap.get(trainer);
        return side.getTrainerInBattle().getCurrStatus();
    }

    /**
     * Checks if all trainers are ready, i.e. have chosen to switch their Poke or do a move
     *
     * @return boolean
     */
    public boolean trainersReady() {
        boolean trainersReady = true;
        for (PokeTrainerSide side : trainerSideMap.values()) {
            if (side.getTrainerInBattle().getCurrStatus() == TrainerChoice.CHOICE_WAITING) {
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
            side.getTrainerInBattle().doChoice();
        }
        // Execute the trainers choices if trainersReady() == true
    }

    private void pursuitisInvolved() {

    }

    private void doNonVolatileStatusDamage() {

    }
}
