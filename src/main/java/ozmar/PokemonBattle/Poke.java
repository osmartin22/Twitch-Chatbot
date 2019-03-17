package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonNature.PokeNature;
import ozmar.PokemonBattle.PokemonStats.PokeAllStats;
import ozmar.PokemonBattle.PokemonStatusConditions.StatusConditionNonVolatile;
import ozmar.PokemonBattle.PokemonType.PokeType;

import java.util.List;

public class Poke {
    private final int id;
    private int level;
    private final String name;
    private final String nickname;
    private int height; // decimeters
    private int weight; // hectograms

    private final PokeType type;
    private final PokeNature nature;      // Natures can not change in battle
    private final PokeAllStats pokeStats;

    private final List<PokeMove> moveList;


    // Pokemon status conditions;
    private StatusConditionNonVolatile nonVolatile;
    private int badlyPoisonedN = 0;

    // ABILITIES GO HERE WHEN IMPLEMENTED

    public Poke(int id, int level, String name, String nickname, int height, int weight, PokeType type,
                PokeNature nature, PokeAllStats pokeStats, List<PokeMove> moveList) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.nature = nature;
        this.pokeStats = pokeStats;
        this.moveList = moveList;
        this.nonVolatile = StatusConditionNonVolatile.NONE;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PokeType getType() {
        return type;
    }

    public PokeNature getNature() {
        return nature;
    }

    public PokeAllStats getPokeStats() {
        return pokeStats;
    }

    public List<PokeMove> getMoveList() {
        return moveList;
    }

    public StatusConditionNonVolatile getNonVolatile() {
        return nonVolatile;
    }

    public void updateNonVolatile(StatusConditionNonVolatile nonVolatile) {
        if (this.nonVolatile == StatusConditionNonVolatile.NONE) {
            if (nonVolatile == StatusConditionNonVolatile.BADLY_POISONED) {
                this.badlyPoisonedN = 1;
            }
            this.nonVolatile = nonVolatile;
        }
    }

    public int getBadlyPoisonedN() {
        return badlyPoisonedN;
    }

    public void incrementBadlyPoisonedN() {
        this.badlyPoisonedN++;
    }
}
