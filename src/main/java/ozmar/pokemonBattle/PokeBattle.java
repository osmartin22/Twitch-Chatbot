package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * @param trainer   Trainer choosing the move
     * @param positions Positions required to set a move
     * @return boolean
     */
    public boolean setMoveToUse(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainer, positions);
        if (isAbleToDoMove) {
            trainerSideMap.get(trainer).setMoveToUse(positions);
        }
        return isAbleToDoMove;
    }

    /**
     * Attempts to set a Pokemon to switch in
     * Failing to be able to switch returns false
     *
     * @param trainer   Trainer switching their Poke
     * @param positions Position required to switch a Poke
     * @return boolean
     */
    public boolean setPokeToSwitchIn(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainer, positions);
        if (isAbleToSwitch) {
            isAbleToSwitch = trainerSideMap.get(trainer).setPokeToSwitchIn(positions);
        }

        return isAbleToSwitch;
    }

    /**
     * Gets the Pokemon at the given position for the given trainer
     *
     * @param trainer   trainer to ger Pokemon from
     * @param positions Positions required for get a Poke
     * @return PokeInBattle
     */
    @Nonnull
    public PokeInBattle getPokeInBattle(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        PokeTrainerSide side = trainerSideMap.get(trainer);
        TrainerInBattle trainerInBattle = side.getTrainerInBattle(positions);

        return side.getTrainerInBattle(positions).getPokeInBattle(positions);
    }

    /**
     * Checks if the Poke on the field can do the selected move
     *
     * @param trainer   Trainer choosing the move
     * @param positions Positions required to check moves
     * @return boolean
     */
    private boolean canTrainersPokeUseMove(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        boolean isAbleToDoMove = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getTrainerChoice(positions) == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = side.isAbleToDoMove(positions);
        }
        return isAbleToDoMove;
    }

    /**
     * Checks if the Pokemon on the field is prevented from switching out
     *
     * @param trainer   Trainer switching Poke
     * @param positions Position on the field the pokemon is in
     * @return boolean
     */
    private boolean canTrainerSwitchPoke(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        boolean isAbleToSwitch = false;
        PokeTrainerSide side = trainerSideMap.get(trainer);
        if (side.getTrainerChoice(positions) == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = side.isAbleToSwitchPoke(positions);
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public TrainerChoice getTrainerStatus(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        return trainerSideMap.get(trainer).getTrainerChoice(positions);
    }

    /**
     * Checks if all trainers are ready, i.e. have chosen to switch their Poke or do a move
     *
     * @return boolean
     */
    public boolean trainersReady() {
        for (PokeTrainerSide side : trainerSideMap.values()) {
            List<TrainerInBattle> trainerInBattleList = side.getTrainerInBattleList();
            for (TrainerInBattle trainerInBattle : trainerInBattleList) {
                List<PokeInBattle> pokeInBattleList = trainerInBattle.getPokeInBattleList();
                for (PokeInBattle pokeInBattle : pokeInBattleList) {
                    if (pokeInBattle.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // TODO: Check if one of the Pokemon will use pursuit before switching out
    //  Pursuit activates if the Poke manually switches out,
    //  or uses U-turn, Volt Switch, or Parting Shot and would attack second
    // NOTE* If both trainers switch out on the same turn, the faster pokemon switches out first
    public void doTrainerChoice(@NonNull PositionHelper positions) {
//        Set<>
        List<PokeInBattle> pokesWithPursuit = new ArrayList<>();
        for (PokeTrainerSide side : trainerSideMap.values()) {
//            for(TrainerInBattle trainerInBattle : side)
        }

        for (PokeTrainerSide side : trainerSideMap.values()) {
            side.doChoice(positions);
        }
        // Execute the trainers choices if trainersReady() == true
    }

    /**
     * Checks each Pokemon and does any effects that occur after each Pokemon
     * has attempted to switch out or do a move
     */
    private void doPostTurnEffects() {

    }
}
