package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonStatusConditions.StatusConditionVolatile;
import ozmar.PokemonBattle.PokemonStatusConditions.StatusConditionVolatileBattleStatus;

import java.util.HashSet;
import java.util.Set;

public class PokeInBattle {

    private final Poke poke;

    private PokeMove currMove;      // Necessary for semi invulnerable turn
    private PokeMove lastUsedMove;  // Necessary for moves that depend on the last used move


    private final Set<StatusConditionVolatile> volatileList;
    private final Set<StatusConditionVolatileBattleStatus> volatileBattleStatusList;

    /*
        Keep track of the type of the move as well(necessary for Conversion 2)
     */

    public PokeInBattle(Poke poke) {
        this.poke = poke;
        this.currMove = null;
        this.lastUsedMove = null;
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

    public PokeMove getLastUsedMove() {
        return lastUsedMove;
    }

    public void setLastUsedMove(PokeMove lastUsedMove) {
        this.lastUsedMove = lastUsedMove;
    }

    public Set<StatusConditionVolatile> getVolatileList() {
        return volatileList;
    }

    public Set<StatusConditionVolatileBattleStatus> getVolatileBattleStatusList() {
        return volatileBattleStatusList;
    }
}
