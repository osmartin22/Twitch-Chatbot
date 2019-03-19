package ozmar.pokemonBattle.pokemon;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.PokeAllStages;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileBattleStatus;
import ozmar.pokemonBattle.pokemonStatusConditions.VolatileStatus;

import javax.annotation.Nonnull;
import java.util.HashSet;
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

    private final PokeAllStages pokeStages;
    private final Set<VolatileStatus> volatileList;
    private final Set<VolatileBattleStatus> volatileBattleStatusList;

    /*
        Keep track of the type of the move as well(necessary for Conversion 2)
     */

    public PokeInBattle(@Nonnull Poke poke) {
        this.poke = poke;
        this.currMove = null;
        this.pokeStages = new PokeAllStages();
        this.volatileList = new HashSet<>();
        this.volatileBattleStatusList = new HashSet<>();
    }

    public Poke getPoke() {
        return poke;
    }

    public PokeMove getCurrMove() {
        return currMove;
    }

    public void setCurrMove(PokeMove currMove) {
        this.currMove = currMove;
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
}
