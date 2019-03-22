package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokemonWeather.PokeWeatherEnum;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;
import ozmar.utils.RandomHelper;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

    /*
    Damage calculations

    Damage Formula
    a = ((2*Level)/5 + 2)
    b = A/D
    c = (a * POWER * b)
    DMG = ((c/50) + 2) * MODIFIER
    MODIFIER = Targets * Weather * Critical * random * STAB * Type * Burn * Other

    Targets = 0.75 if hitting multiple else 1
    Weather = 1.5 for water type during Rain of fire type used during Harsh Sunlight, 0.5 for the opposite
    Critical = Check critical hit below
    Random = random number from 0.85 - 1.00
    STAB = 1.5 if any of the user's types matches the moves type else 1
    Type = type effectiveness 0(ineffective), 0.25, 0.5(not very effective), 1(normal), 2, 4(super effective)
    Burn = 0.5 if the attacker is burned and uses a physical move, else 1
    Other = multipliers from moves, abilities, items https://bulbapedia.bulbagarden.net/wiki/Damage

    Critical hits do 1.5x a moves damage
    Attackers negative stat stages are ignored
    Defenders positive stat stages are ignored
     */

public class PokeBattleCalculator {

    private final Map<Integer, Double> critChance;

    public PokeBattleCalculator() {
        this.critChance = new HashMap<>(4);
        this.critChance.put(0, 1 / 24.0);   // Stage 0 = Normal Crit
        this.critChance.put(1, 1 / 8.0);    // Stage 1 = High Crit
        this.critChance.put(2, 1 / 2.0);    // Stage 2 = 10,000,00 Volt Thunderbolt
        this.critChance.put(3, 1.0);        // Stage 3+
    }

    public int calculateDamage(PokeInBattle attacker, PokeInBattle target, PokeMove move, PokeWeatherEnum weather) {
        boolean isSpecial = move.getDamageClass() == PokeMoveDamageClass.SPECIAL;

        double critMultiplier = getCritMultiplier();
        boolean isCrit = critMultiplier != 1;

        int a = ((2 * attacker.getPoke().getLevel()) / 5) + 2;
        double b = getAttackerStat(attacker, isSpecial, isCrit) / getDefenderStat(target, isSpecial, isCrit);
        System.out.println(b);
        double c = a * move.getPower() * b;
        double modifier = getModifierMultiplier(attacker, target, move, weather);

        return (int) (((c / 50) + 2) * modifier * critMultiplier);
    }

    public double getAttackerStat(PokeInBattle attacker, boolean isSpecial, boolean isCrit) {
        int stat;
        int stage;
        if (isSpecial) {
            stat = attacker.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPC_ATK);
            stage = attacker.getPokeStages().getStateStage(PokeStatStage.SPC_ATK_STAGE);
        } else {
            stat = attacker.getPoke().getPokeStats().getPokeStatValue(PokeStat.ATK);
            stage = attacker.getPokeStages().getStateStage(PokeStatStage.ATK_STAGE);
        }

        stage = isCrit && stage < 0 ? 0 : stage;
        return stat * PokeInfoHelper.STAT_STAGES.get(stage);
    }

    public double getDefenderStat(@NonNull PokeInBattle defender, boolean isSpecial, boolean isCrit) {
        int stat;
        int stage;
        if (isSpecial) {
            stat = defender.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPC_DEF);
            stage = defender.getPokeStages().getStateStage(PokeStatStage.SPC_DEF_STAGE);
        } else {
            stat = defender.getPoke().getPokeStats().getPokeStatValue(PokeStat.DEF);
            stage = defender.getPokeStages().getStateStage(PokeStatStage.DEF_STAGE);
        }

        stage = isCrit && stage > 0 ? 0 : stage;
        return stat * PokeInfoHelper.STAT_STAGES.get(stage);
    }

    public double getModifierMultiplier(@Nonnull PokeInBattle attacker, @Nonnull PokeInBattle target,
                                        @Nonnull PokeMove move, @Nonnull PokeWeatherEnum weather) {
        double weatherMultiplier = getWeatherMultiplier(weather, move.getMoveType());
        double random = RandomHelper.getRandNumInRange(85, 100) / 100.0;
        double stab = attacker.getPoke().getType().moveWillHaveStab(move.getMoveType()) ? 1.5 : 1.0;
        double typeEffectiveness = target.getPoke().getType().damageReceivedMultiplier(move.getMoveType());
        double burnStatus = attacker.getPoke().getNonVolatile() == NonVolatileStatus.BURN ? 0.5 : 1.0;

        return weatherMultiplier * random * stab * typeEffectiveness * burnStatus;
    }

    /**
     * Gets the multiplier for the given weather
     * Water moves are stronger in Rain, fire moves are weaker
     * Fire moves are stronger in Harsh Sunlight, water moves are weaker
     * <p>
     * NOTE* Doesn't take into consideration the Weather Trio weather as they come from abilities
     *
     * @param weather  weather currently active
     * @param moveType move's type
     * @return double
     */
    private double getWeatherMultiplier(@NonNull PokeWeatherEnum weather, @NonNull PokeTypeEnum moveType) {
        double weatherMultiplier = 1.0;
        if (weather == PokeWeatherEnum.RAIN) {
            if (moveType == PokeTypeEnum.WATER) {
                weatherMultiplier = 1.5;
            } else if (moveType == PokeTypeEnum.FIRE) {
                weatherMultiplier = 0.5;
            }
        } else if (weather == PokeWeatherEnum.HARSH_SUNLIGHT) {
            if (moveType == PokeTypeEnum.WATER) {
                weatherMultiplier = 0.5;
            } else if (moveType == PokeTypeEnum.FIRE) {
                weatherMultiplier = 1.5;
            }
        }

        return weatherMultiplier;
    }

    // TODO: Handle moves that have high crit and perfect crit
    private double getCritMultiplier() {
        return RandomHelper.getRandNumInRange(1, 24) == 1 ? 1.5 : 1;
    }
}
