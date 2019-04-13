package ozmar.pokemonBattle.pokemonField.PokemonBinding;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;

public class PokeBinding {

    private int bindingTurns;
    private PokeBindingEnum binding;

    public PokeBinding() {
        this.bindingTurns = 0;
        this.binding = PokeBindingEnum.NONE;
    }

    public int getBindingTurns() {
        return bindingTurns;
    }

    public boolean setBinding(@Nonnull PokeBindingEnum binding) {
        boolean canBind = false;
        if (binding == PokeBindingEnum.NONE) {
            if (RandomHelper.getRandNumInRange(0, 1) == 0) {
                bindingTurns = 4;
            } else {
                bindingTurns = 5;
            }

            this.binding = binding;
            canBind = true;
        }

        return canBind;
    }

    /**
     * Damages the Poke from the binding effects and lowers the turn counter
     * All binding moves do 1/8th of the targets max Hp
     *
     * @param poke Poke to damage
     */
    public void doBindingDamage(@Nonnull Poke poke) {
        if (!poke.isFainted()) {
            int maxHp = poke.getPokeStat(PokeStat.HP);
            int damageDealt = (int) (maxHp * (1 / 8.0));
            poke.lowerHp(damageDealt);
            bindingTurns--;
        }
    }

    public void clearBinding() {
        binding = PokeBindingEnum.NONE;
    }
}
