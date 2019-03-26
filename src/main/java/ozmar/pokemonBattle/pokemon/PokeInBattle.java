package ozmar.pokemonBattle.pokemon;

import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonBattleHelpers.TrainerChoice;
import ozmar.pokemonBattle.pokemonField.PokemonBinding.PokeBinding;
import ozmar.pokemonBattle.pokemonField.PokemonBinding.PokeBindingEnum;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.PokeAllStages;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO: Create a class that holds information pertaining to what was modified in a pokemon
//  this would then be returned and looked at to see if any output should be displayed
//  i.e. A Poke's stats and status was affected, these things should be displayed to the user whenever they change

// TODO: Whenever a Pokemon switches, only the Poke should change
//  the other objects can stay since stages can be passed, they should have a reset method
public class PokeInBattle {

    private final int position;
    private Poke poke;

    // TODO: Have a isSemiInvulnerable, isCharging, and isRecharging flag
    // TODO: Add the move struggle (every Pokemon knows it but it's only used when all other moves have 0 PP)
    // TODO: Have a counter for binding moves

    // Will hold the current move the Pokemon is using
    private PokeMove currMove;      // Necessary for semi invulnerable turn
    private PokeMove moveToUse;

    private final PokeAllStages pokeStages;
    private final Set<VolatileStatus> volatileList;
    private final Set<VolatileBattleStatus> volatileBattleStatusList;
    private PokeBinding binding;
    private int badlyPoisonedN;     // Used for the badly poisoned status effect
    private int critStage;

    private TrainerChoice trainerChoice;
    private Poke pokeToSwitchIn;

    private boolean isFlinched;

    private PokeTargetPosition targetPosition;

    /*
     Moves that copy other moves
     Mimic copies the last used move of the target and disappears after the user switches out
     Sketch copies the move permanently and is gone forever
         Replace the move entirely and forget Sketch
     Transform copies everything about the target i.e. moves, stats, stat stages, species, current form, all moves have 5 PP

     Other moves that copy only copy the move for the attacking phase

     To handle mimic
     Map containing moves copied
     */
    private final Map<Integer, PokeMove> copiedMoves;

    /*
    Object to handle mega evolutions
    Mega evolved pokemon will become this object
    This object will then take any attacks or execute any attacks
    When the Pokemon switches out or faints, this object combines with the original Poke object
    This object will then be sent to garbage collection as it is no longer needed
    NOTE* Can maybe be used for a Pokemon's alternate form as well
     */
    private Poke megaForm;

    /*
        Keep track of the type of the move as well(necessary for Conversion 2)
     */

    public PokeInBattle(@Nonnull Poke poke, int position) {
        this.position = position;
        this.poke = poke;
        this.currMove = null;
        this.moveToUse = null;
        this.pokeStages = new PokeAllStages();
        this.volatileList = new HashSet<>();
        this.volatileBattleStatusList = new HashSet<>();
        this.binding = new PokeBinding();
        this.copiedMoves = new HashMap<>();
        this.critStage = 0;
        this.trainerChoice = TrainerChoice.CHOICE_WAITING;
        this.pokeToSwitchIn = null;
        this.isFlinched = false;
    }

    public Poke getPoke() {
        if (megaForm != null) {
            return megaForm;
        }
        return poke;
    }

    public void setPoke(@Nonnull Poke poke) {
        this.poke = poke;
    }

    public PokeMove getCurrMove() {
        return currMove;
    }

    public void setCurrMove(@Nonnull PokeMove currMove) {
        this.currMove = currMove;
    }

    @Nonnull
    public PokeMove getMoveToUse() {
        return moveToUse;
    }

    public void setMoveToUse(@Nonnull PokeMove moveToUse, @Nonnull PokeTargetPosition targetPosition) {
        this.moveToUse = moveToUse;
        this.targetPosition = targetPosition;
    }

    public PokeAllStages getPokeStages() {
        return pokeStages;
    }

    public Set<VolatileStatus> getVolatileList() {
        return volatileList;
    }

    public Set<VolatileBattleStatus> getVolatileBattleStatusList() {
        return volatileBattleStatusList;
    }

    public int getCritStage() {
        return critStage;
    }

    public void updateCritHitStage(int critHitStage) {
        this.critStage += critHitStage;
    }

    public boolean bindPoke(@Nonnull PokeBindingEnum bindingEnum) {
        return binding.setBinding(bindingEnum);
    }

    public void copyMove(int movePosition, PokeMove moveToCopy) {
        copiedMoves.put(movePosition, moveToCopy);
    }

    public TrainerChoice getTrainerChoice() {
        return trainerChoice;
    }

    public void setTrainerChoice(@Nonnull TrainerChoice trainerChoice) {
        this.trainerChoice = trainerChoice;
    }

    public Poke getPokeToSwitchIn() {
        return pokeToSwitchIn;
    }

    public void setPokeToSwitchIn(@Nonnull Poke pokeToSwitchIn) {
        this.pokeToSwitchIn = pokeToSwitchIn;
    }

    // TODO: Check if the stats should be kept when switching (Baton Pass, U-Turn)
    public void switchPoke() {
        this.poke = pokeToSwitchIn;
        this.megaForm = null;
        this.pokeToSwitchIn = null;
    }

    public boolean isTypeFound(@Nonnull PokeTypeEnum type) {
        return poke.getType().isTypeFound(type);
    }

    public boolean isFlinched() {
        return isFlinched;
    }

    public void setFlinched(boolean flinched) {
        isFlinched = flinched;
    }

    public PokeTargetPosition getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(PokeTargetPosition targetPosition) {
        this.targetPosition = targetPosition;
    }
}
