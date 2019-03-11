package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeNatureEnum;
import ozmar.PokeBattle.enums.PokeStatEnum;

import java.util.List;

public class PokeStat {
    private int value;
    private PokeStatEnum stat;
    private List<PokeNatureEnum> increaseStat;
    private List<PokeNatureEnum> decreaseStat;

    public PokeStat(int value, PokeStatEnum stat, List<PokeNatureEnum> increaseStat, List<PokeNatureEnum> decreaseStat) {
        this.value = value;
        this.stat = stat;
        this.increaseStat = increaseStat;
        this.decreaseStat = decreaseStat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PokeStatEnum getStat() {
        return stat;
    }

    public List<PokeNatureEnum> getIncreaseStat() {
        return increaseStat;
    }

    public List<PokeNatureEnum> getDecreaseStat() {
        return decreaseStat;
    }
}
