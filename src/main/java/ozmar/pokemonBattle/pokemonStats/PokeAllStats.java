package ozmar.pokemonBattle.pokemonStats;

import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class PokeAllStats {
    private int currHp;
    private final Map<PokeStat, Integer> pokeStats;

    public PokeAllStats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.currHp = hp;
        this.pokeStats = new HashMap<>();
        this.pokeStats.put(PokeStat.HP, hp);
        this.pokeStats.put(PokeStat.ATK, attack);
        this.pokeStats.put(PokeStat.DEF, defense);
        this.pokeStats.put(PokeStat.SPC_ATK, specialAttack);
        this.pokeStats.put(PokeStat.SPC_DEF, specialDefense);
        this.pokeStats.put(PokeStat.SPD, speed);
    }

    public int getCurrHp() {
        return currHp;
    }

    /**
     * Updates a Pokemon's HP
     * Damage should be passed in as a negative number
     * Healing should be passed in as a positive number
     *
     * @param hpChange the change in hp
     */
    public void updateCurrHp(int hpChange) {
        int newHp = currHp + hpChange;
        if (newHp > pokeStats.get(PokeStat.HP)) {
            newHp = pokeStats.get(PokeStat.HP);
        } else if (newHp < 0) {
            newHp = 0;
        }

        currHp = newHp;
    }

    /**
     * Gets the stat value
     *
     * @param statType stat to get
     * @return int
     */
    public int getPokeStatValue(@Nonnull PokeStat statType) {
        return pokeStats.get(statType);
    }
}
