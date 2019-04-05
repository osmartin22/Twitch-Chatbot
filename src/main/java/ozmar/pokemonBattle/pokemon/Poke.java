package ozmar.pokemonBattle.pokemon;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonNature.PokeNatureEnum;
import ozmar.pokemonBattle.pokemonStats.PokeAllStats;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeType;

import javax.annotation.Nonnull;
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

    private int sleepCounter;
    private boolean isDrowsy;
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

    public boolean isFainted() {
        return pokeStats.getCurrHp() == 0;
    }

    public int getCurrHp() {
        return pokeStats.getCurrHp();
    }

    /**
     * Lowers the Poke's current hp value
     *
     * @param hpChange amount of hp to lower
     */
    public void lowerHp(int hpChange) {
        pokeStats.updateCurrHp(-hpChange);
    }

    /**
     * Restores the Poke's current hp value
     *
     * @param hpChange amount of hp to restore
     */
    public void restoreHp(int hpChange) {
        pokeStats.updateCurrHp(hpChange);
    }

    /**
     * Gets the stat value for the desired PokeStat
     *
     * @param stat PokeStat
     * @return stat value
     */
    public int getPokeStat(@Nonnull PokeStat stat) {
        return pokeStats.getPokeStatValue(stat);
    }

    public List<PokeMove> getMoveList() {
        return moveList;
    }

    public NonVolatileStatus getNonVolatile() {
        return nonVolatile;
    }

    /**
     * A Pokemon can only have one Non Volatile Status
     * The Non Volatile Status only affects the Pokemon if it does not have one already
     * NOTE: Some moves can turn the POISON status to BADLY POISONED
     * TODO: Should update method to handle changing POISON to BADLY POISONED
     *
     * @param nonVolatile NonVolatileStatus
     */
    public void updateNonVolatile(NonVolatileStatus nonVolatile) {
        if (this.nonVolatile == NonVolatileStatus.NONE) {
            this.nonVolatile = nonVolatile;
        }
    }

    public int getSleepCounter() {
        return sleepCounter;
    }

    public void decrementSleepCounter() {
        this.sleepCounter--;
    }

    public boolean isDrowsy() {
        return isDrowsy;
    }

    public void setDrowsy(boolean drowsy) {
        isDrowsy = drowsy;
    }
}
