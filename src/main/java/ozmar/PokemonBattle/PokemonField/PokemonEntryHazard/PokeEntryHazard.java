package ozmar.PokemonBattle.PokemonField.PokemonEntryHazard;

import ozmar.PokemonBattle.Poke;
import ozmar.PokemonBattle.PokeInBattle;
import ozmar.PokemonBattle.PokemonStatusConditions.StatusConditionNonVolatile;
import ozmar.PokemonBattle.PokemonType.PokeType;
import ozmar.PokemonBattle.PokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class PokeEntryHazard {

    private static final int MAX_HAZARD_STACKS = 3;
    private Map<PokeEntryHazardEnum, Integer> entryHazardMap;

    public PokeEntryHazard() {
        this.entryHazardMap = new HashMap<>();
    }

    /**
     * Adds the entry hazard used to the map or increases the stacks if already present
     *
     * @param entryHazard entry hazard to add
     */
    public void entryHazardMoveUsed(@Nonnull PokeEntryHazardEnum entryHazard) {
        if (!entryHazardMap.containsKey(entryHazard)) {
            entryHazardMap.put(entryHazard, 1);
        } else {
            int stacks = entryHazardMap.get(entryHazard) + 1;
            if (stacks <= MAX_HAZARD_STACKS) {
                entryHazardMap.put(entryHazard, stacks);
            }
        }
    }

    /**
     * Clears the specified entry hazard
     *
     * @param entryHazard entry hazard to clear
     */
    public void clearEntryHazard(@Nonnull PokeEntryHazardEnum entryHazard) {
        entryHazardMap.remove(entryHazard);
    }

    /**
     * Clears all entry hazards present
     */
    public void clearAllEntryHazards() {
        entryHazardMap.clear();
    }

    /**
     * Checks if there is an entry hazard present
     *
     * @return boolean
     */
    public boolean isEntryHazardPresent() {
        return !entryHazardMap.isEmpty();
    }

    /**
     * Affects the Pokemon with all of the entry hazards present
     *
     * @param pokeInBattle The Pokemon to be affected
     */
    public void doEntryHazardEffect(@Nonnull PokeInBattle pokeInBattle) {
        if (entryHazardMap.containsKey(PokeEntryHazardEnum.SPIKES)) {
            spikesHazard(pokeInBattle);
        }
        if (entryHazardMap.containsKey(PokeEntryHazardEnum.STEALTH_ROCK)) {
            stealthRockHazard(pokeInBattle.getPoke());
        }
        if (entryHazardMap.containsKey(PokeEntryHazardEnum.STICKY_WEB)) {
            stickyWebHazard(pokeInBattle.getPoke());
        }
        if (entryHazardMap.containsKey(PokeEntryHazardEnum.TOXIC_SPIKES)) {
            toxicSpikesHazard(pokeInBattle);
        }
    }


    /**
     * Damages the target for a certain percentage based on the number of stacks
     * Flying Pokemon immune except if they are affected by Gravity or Magnet Rise
     * 1 stack = 1/8, 2 stacks = 1/6, 3 stacks = 1/4 max HP damage, 3 stacks max
     *
     * @param pokeInBattle Pokemon to be affected
     */
    private void spikesHazard(@Nonnull PokeInBattle pokeInBattle) {
        // TODO: Still check if the Pokemon is affected by Magnet Rise or Gravity
        /*
        Deals damage based on the number of stacks
        Flying Pokemon immune as well as those affected by Magnet Rise
        Flying are affected only if Gravity is active
         */
        Poke poke = pokeInBattle.getPoke();
        if (!poke.getType().isTypeFound(PokeTypeEnum.FLYING)) {
            double damageDealtPercent = getSpikesStackDamage(entryHazardMap.get(PokeEntryHazardEnum.SPIKES));
            int maxHp = poke.getPokeStats().getMaxHp();
            int damageDealt = (int) (maxHp * damageDealtPercent);
            poke.getPokeStats().updateCurrHp(damageDealt);
        }

    }

    /**
     * Gets the damage dealt based on the number of stacks
     *
     * @param stacks number of stacks
     * @return double
     */
    private double getSpikesStackDamage(int stacks) {
        double damage;
        switch (stacks) {
            case 1:
            default:
                damage = 1 / 8.0;
                break;
            case 2:
                damage = 1 / 6.0;
                break;
            case 3:
                damage = 1 / 4.0;
        }

        return damage;
    }

    /**
     * Damages the Pokemon based on the effectiveness Rock types have on the Pokemon
     * Additional stacks have no effect
     *
     * @param poke Pokemon to be affected
     */
    private void stealthRockHazard(@Nonnull Poke poke) {
        double damageDealtPercent = getStealthRockDamage(poke.getType());
        int maxHp = poke.getPokeStats().getMaxHp();
        int damageDealt = (int) (maxHp * damageDealtPercent);
        poke.getPokeStats().updateCurrHp(damageDealt);
    }

    /**
     * Gets the percentage Rock Stealth will have on the Pokemon
     *
     * @param pokeType The types of the Pokemon to be affected
     * @return double
     */
    private double getStealthRockDamage(@Nonnull PokeType pokeType) {
        double damageMultiplier = pokeType.damageReceivedMultiplier(PokeTypeEnum.ROCK);
        double damageDealt = 12.5; // Use 1x damage as the default
        if (damageMultiplier == 0.25) {
            damageDealt = 3.125;
        } else if (damageMultiplier == 0.5) {
            damageDealt = 6.25;
        } else if (damageMultiplier == 2) {
            damageDealt = 25;
        } else if (damageMultiplier == 4) {
            damageDealt = 50;
        }

        return damageDealt;
    }

    /**
     * Lowers the Pokemon's speed stat by one stage
     *
     * @param poke Pokemon to be affected
     */
    private void stickyWebHazard(@Nonnull Poke poke) {
        if (!poke.getType().isTypeFound(PokeTypeEnum.FLYING)) {
            poke.getPokeStats().updateSpeedStage(-1);
        }
    }

    /**
     * Poisons the Pokemon
     * At 1 stack, it affects the non volatile status Poison
     * At 2+ stacks, it affects the non Volatile status Badly Poisoned
     * Flying, Poison, and Steel Types are immune
     * Poison types that are not also Flying(unless Gravity is in effect) will absorb Toxic Spikes
     * Flying Pokemon are affected if Gravity is in effect
     *
     * @param pokeInBattle Pokemon to be affected
     */
    private void toxicSpikesHazard(@Nonnull PokeInBattle pokeInBattle) {
        /*
        Flying, Poison, Steel Types immune
        Flying pokemon affected if they are ingrained though baton pass
        Poison type that is not Flying(unless Gravity is in effect) will absorb Toxic Spikes

        1 stack causes Poison
        2+ stacks causes Badly Poisoned
         */
        // TODO: Incomplete
        Poke poke = pokeInBattle.getPoke();
        if (!poke.getType().isTypeFound(PokeTypeEnum.FLYING) && !poke.getType().isTypeFound(PokeTypeEnum.POISON) &&
                !poke.getType().isTypeFound(PokeTypeEnum.STEEL)) {
            int stacks = entryHazardMap.get(PokeEntryHazardEnum.TOXIC_SPIKES);
            if (stacks == 1) {
                poke.updateNonVolatile(StatusConditionNonVolatile.POISON);
            } else {
                poke.updateNonVolatile(StatusConditionNonVolatile.BADLY_POISONED);
            }
        }
    }
}
