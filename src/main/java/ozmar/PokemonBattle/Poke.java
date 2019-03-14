package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonStats.PokeAllStats;
import ozmar.PokemonBattle.enums.StatusConditionNonVolatile;

import java.util.List;

public class Poke {
    private final int id;
    private int level;
    private final String name;
    private final String nickname;
    private final int height; // decimeters
    private final int weight; // hectograms


    private final PokeNature nature;      // Natures can not change in battle
    private final PokeAllStats pokeStats;

    private final List<PokeMove> moveList;


    // Pokemon status conditions;
    private StatusConditionNonVolatile nonVolatile;

    // ABILITIES GO HERE WHEN IMPLEMENTED


    public Poke(int id, int level, String name, String nickname, int height, int weight, PokeNature nature,
                PokeAllStats pokeStats, List<PokeMove> moveList) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.nature = nature;
        this.pokeStats = pokeStats;
        this.moveList = moveList;
        this.nonVolatile = StatusConditionNonVolatile.NONE;
    }


}
