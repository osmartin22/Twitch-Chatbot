package ozmar.pokemonBattle.pokemonNature;

import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;

public class PokeNature {
    private PokeNatureEnum nature;
    private PokeStat increaseStat;
    private PokeStat decreaseStat;

    public PokeNature(PokeNatureEnum nature, PokeStat increaseStat, PokeStat decreaseStat) {
        this.nature = nature;
        this.increaseStat = increaseStat;
        this.decreaseStat = decreaseStat;
    }

    public PokeNatureEnum getNature() {
        return nature;
    }

    public PokeStat getIncreaseStat() {
        return increaseStat;
    }

    public PokeStat getDecreaseStat() {
        return decreaseStat;
    }
}
