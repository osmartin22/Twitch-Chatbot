package ozmar.pokemonBattle.pokemon;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonNature.PokeNatureEnum;
import ozmar.pokemonBattle.pokemonStats.PokeAllStats;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeType;

import java.util.List;

public class Poke {
    private final int id;
    private int level;
    private final String name;
    private final String nickname;
    private int height; // decimeters
    private int weight; // hectograms

    private final PokeType type;
    private final PokeNatureEnum nature;      // Natures can not change in
    private final PokeAllStats pokeStats;

    private final List<PokeMove> moveList;


    // Pokemon status conditions;
    private NonVolatileStatus nonVolatile;

    // ABILITIES GO HERE WHEN IMPLEMENTED

    public Poke(int id, int level, String name, String nickname, int height, int weight, PokeType type,
                PokeNatureEnum nature, PokeAllStats pokeStats, List<PokeMove> moveList) {
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
        this.nonVolatile = NonVolatileStatus.NONE;
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

    public PokeNatureEnum getNature() {
        return nature;
    }

    public PokeAllStats getPokeStats() {
        return pokeStats;
    }

    public List<PokeMove> getMoveList() {
        return moveList;
    }

    public NonVolatileStatus getNonVolatile() {
        return nonVolatile;
    }

    public void updateNonVolatile(NonVolatileStatus nonVolatile) {
        if (this.nonVolatile == NonVolatileStatus.NONE) {
            this.nonVolatile = nonVolatile;
        }
    }

    public boolean isFainted() {
        return pokeStats.getCurrHp() == 0;
    }
}
