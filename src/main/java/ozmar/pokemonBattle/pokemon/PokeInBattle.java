package ozmar.pokemonBattle.pokemon;

import ozmar.pokemonBattle.pokemonField.PokemonBinding.PokeBinding;
import ozmar.pokemonBattle.pokemonField.PokemonBinding.PokeBindingEnum;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.PokeAllStages;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;

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
        Keep track of the type of the move as well(necessary for Conversion 2)
     */

    public PokeInBattle(@Nonnull Poke poke) {
        this.poke = poke;
        this.currMove = null;
        this.moveToUse = null;
        this.pokeStages = new PokeAllStages();
        this.volatileList = new HashSet<>();
        this.volatileBattleStatusList = new HashSet<>();
        this.binding = new PokeBinding();
        this.copiedMoves = new HashMap<>();
    }

    public Poke getPoke() {
        return poke;
    }

    public void setPoke(Poke poke) {
        this.poke = poke;
    }

    public PokeMove getCurrMove() {
        return currMove;
    }

    public void setCurrMove(PokeMove currMove) {
        this.currMove = currMove;
    }

    @Nonnull
    public PokeMove getMoveToUse() {
        return moveToUse;
    }

    public void setMoveToUse(@Nonnull PokeMove moveToUse) {
        this.moveToUse = moveToUse;
    }

    public void doMove() {
        // Do move
        // Moves in the copiedMovesMap have priority before the Poke's original moves
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

    public boolean bindPoke(PokeBindingEnum bindingEnum) {
        return binding.setBinding(bindingEnum);
    }

    public void doBindingDamage() {
        binding.doBindingDamage(poke);
    }

    public void doNonVolatileDamage() {
        // TODO: Non volatile status damage is done at the end of each turn, not all statuses cause damage
    }

    public void copyMove(int movePosition, PokeMove moveToCopy) {
        copiedMoves.put(movePosition, moveToCopy);
    }


}
