package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeTypeEnum;
import ozmar.PokeBattle.enums.StatusConditionNonVolatile;
import ozmar.PokeBattle.enums.StatusConditionVolatile;
import ozmar.PokeBattle.enums.StatusConditionVolatileBattleStatus;

import java.util.List;

public class Poke {
    // Pokemon Id
    private int id;

    // Pokemon Level
    private int level;

    // Pokemon Names
    private String name;
    private String nickname;

    private int height; // decimeters
    private int weight; // hectograms

    // Pokemon Nature
    private PokeNature nature;

    // Pokemon Stats
    private PokeAllStats startingStats;
    private PokeAllStats battleStats;

    // Pokemon Moves
    private List<PokeMove> moveList;

    // Pokemon types
    private PokeTypeEnum pokeType1;
    private PokeTypeEnum getPokeType2;

    // Pokemon status conditions;
    private StatusConditionNonVolatile nonVolatile;
    private List<StatusConditionVolatile> volatileList;
    private List<StatusConditionVolatileBattleStatus> volatileBattleStatusList;

    // ABILITIES GO HERE WHEN IMPLEMENTED
}
