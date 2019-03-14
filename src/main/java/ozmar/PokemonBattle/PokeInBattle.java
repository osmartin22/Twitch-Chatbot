package ozmar.PokemonBattle;

import ozmar.PokemonBattle.enums.StatusConditionVolatile;
import ozmar.PokemonBattle.enums.StatusConditionVolatileBattleStatus;

import java.util.List;

public class PokeInBattle {

    private Poke poke;

    private PokeMove currMove;      // Necessary for semi invulnerable turn
    private PokeMove lastUsedMove;  // Necessary for moves that depend on the last used move


    private List<StatusConditionVolatile> volatileList;
    private List<StatusConditionVolatileBattleStatus> volatileBattleStatusList;

    /*
        Keep track of the type of the move as well(necessary for Conversion 2)
     */
}
