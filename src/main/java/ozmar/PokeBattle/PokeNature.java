package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeNatureEnum;
import ozmar.PokeBattle.enums.PokeStatEnum;

public class PokeNature {
    private PokeNatureEnum nature;
    private PokeStatEnum increaseStat;
    private PokeStatEnum decreaseStat;

    public PokeNature(PokeNatureEnum nature, PokeStatEnum increaseStat, PokeStatEnum decreaseStat) {
        this.nature = nature;
        this.increaseStat = increaseStat;
        this.decreaseStat = decreaseStat;
    }

    public PokeNatureEnum getNature() {
        return nature;
    }

    public PokeStatEnum getIncreaseStat() {
        return increaseStat;
    }

    public PokeStatEnum getDecreaseStat() {
        return decreaseStat;
    }
}
