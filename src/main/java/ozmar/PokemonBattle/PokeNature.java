package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonStats.PokeStatTypeEnum;
import ozmar.PokemonBattle.enums.PokeNatureEnum;

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
