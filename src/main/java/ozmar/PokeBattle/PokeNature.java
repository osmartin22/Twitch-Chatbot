package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeNatureEnum;
import ozmar.PokeBattle.enums.PokeStatTypeEnum;

public class PokeNature {
    private PokeNatureEnum nature;
    private PokeStatTypeEnum increaseStat;
    private PokeStatTypeEnum decreaseStat;

    public PokeNature(PokeNatureEnum nature, PokeStatTypeEnum increaseStat, PokeStatTypeEnum decreaseStat) {
        this.nature = nature;
        this.increaseStat = increaseStat;
        this.decreaseStat = decreaseStat;
    }

    public PokeNatureEnum getNature() {
        return nature;
    }

    public PokeStatTypeEnum getIncreaseStat() {
        return increaseStat;
    }

    public PokeStatTypeEnum getDecreaseStat() {
        return decreaseStat;
    }
}
