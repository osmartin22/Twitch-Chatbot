package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonStatusConditions.VolatileBattleStatus;
import ozmar.PokemonBattle.PokemonStatusConditions.VolatileStatus;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class PokeInBattle {

    private final Poke poke;

    // TODO: Have a isSemiInvulnerable, isCharging, and isRecharging flag
    // TODO: Add the move struggle (every Pokemon knows it but it's only used when all other moves have 0 PP)
    // TODO: Have a counter for binding moves

    // Will hold the current move the Pokemon is using
    private PokeMove currMove;      // Necessary for semi invulnerable turn


    private final Set<VolatileStatus> volatileList;
    private final Set<VolatileBattleStatus> volatileBattleStatusList;

    /*
        Keep track of the type of the move as well(necessary for Conversion 2)
     */

    public PokeInBattle(@Nonnull Poke poke) {
        this.poke = poke;
        this.currMove = null;
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

    public Set<VolatileStatus> getVolatileList() {
        return volatileList;
    }

    public Set<VolatileBattleStatus> getVolatileBattleStatusList() {
        return volatileBattleStatusList;
    }
}
