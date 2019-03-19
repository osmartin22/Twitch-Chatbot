package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonField.PokeField;
import ozmar.PokemonBattle.PokemonField.PokeSide;
import ozmar.PokemonBattle.PokemonField.PokeTrainerSide;
import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonTrainer.Trainer;
import ozmar.PokemonBattle.PokemonTrainer.TrainerInBattle;

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

    public boolean setSwitchPoke(@Nonnull Trainer trainer, @Nonnull Poke poke) {
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainer);
        if (isAbleToSwitch) {
            // Set Trainers poke to switch to
            // Set status to CHOICE_SWITCH
        }

        return isAbleToSwitch;
    }

    /**
     * Checks if the Poke on the field can do the selected move
     *
     * @param trainer Trainer choosing the move
     * @param move    move the Poke will use
     * @return boolean
     */
    public boolean canTrainersPokeUseMove(@Nonnull Trainer trainer, @Nonnull PokeMove move) {
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
    public boolean canTrainerSwitchPoke(@Nonnull Trainer trainer) {
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

    public void doTrainerChoice() {
        // Execute the trainers choices if trainersReady() == true
    }
/*
Sequence of events
Users select there Pokemon along with the first Pokemon to send out

PokeBattle object is created containing the above information

Starting at this point, the battle starts

    Entering Pokemon are damage by Entry Hazards (Only for Pokemon that are switching in)

    WAIT FOR MOVE
    Trainers are asked what they will do (switch Pokemon, select a move, get info pertaining to the battle)

    Stay at WAIT FOR MOVE until both users switch their Pokemon or select a move
    Asking for more info will tell the user the info ans still wait for the Trainer's decision

    BATTLE PHASE
    If a Trainer switches out their Pokemon, this will execute first before any moves (Pursuit is a special move that can by pass this)
    The entering Pokemon will then take damage from Entry Hazards if present
    The other Pokemon will then attack if it selected a move, or switch out and follow the above

    If both Pokemon selected a move
    Calculate who will go first
    Pokemon going first will then do their move
    Move effects will then occur
    Pokemon going second will then do their move
    Move effects will then occur

    POST BATTLE PHASE
    After both Pokemon have switched or done their move
    Status effects occur, e.g. burn status effect burns the target
    Other one turn effects are removed, e.g. protection moves disappear

    Goes back to waiting for user input -> WAIT FOR MOVE
 */

/*
Switching a Pokemon will always go first (Pursuit can hit first if it targets the Poke switching out

Check for Priority of the moves before deciding who will go first

Check if the move is affected from effects on the user/target Poke or on the field
e.g. Moves that are amplified in power or moves that the target Pokemon is immune to

Check if the move is prevented from effects on the user or target poke or on the field
 */
}
