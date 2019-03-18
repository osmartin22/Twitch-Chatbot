package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.PokemonNature.PokeNature;
import ozmar.PokemonBattle.PokemonNature.PokeNatureEnum;
import ozmar.PokemonBattle.PokemonStats.PokeStatTypeEnum;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PokeInfoHelper {

    private PokeInfoHelper() {

    }

    // Left to Right is the type and its effectiveness against the other types
    // Top down is the type and how other types are effective against it
    // Order is
    //    NONE(0), NORMAL(1), FIGHTING(2), FLYING(3), POISON(4), GROUND(5), ROCK(6), BUG(7), GHOST(8), STEEL(9),
    //    FIRE(10), WATER(11), GRASS(12), ELECTRIC(13), PSYCHIC(14), ICE(15), DRAGON(16), DARK(17), FAIRY(18), UNKNOWN(19);
    public static final double[][] TYPE_CHART = {
//             0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},    // NONE(0)
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},    // NORMAL(1)
            {1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 0.5, 1.0},    // FIGHTING(2)
            {1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},    // FLYING(3)
            {1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 0.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0},    // POISON(4)
            {1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 2.0, 0.5, 1.0, 2.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},    // GROUND(5)
            {1.0, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0},    // ROCK(6)
            {1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 2.0, 0.5, 1.0},    // BUG(7)
            {1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0},    // GHOST(8)
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0},    // STEEL(9)
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 0.5, 0.5, 2.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0},    // FIRE(10)
            {1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0},    // WATER(11)
            {1.0, 1.0, 1.0, 0.5, 0.5, 2.0, 2.0, 0.5, 1.0, 0.5, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0},    // GRASS(12)
            {1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0},    // ELECTRIC(13)
            {1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0, 1.0, 1.0},    // PSYCHIC(14)
            {1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0},    // ICE(15)
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0},    // DRAGON(16)
            {1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0},    // DARK(17)
            {1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0},    // FAIRY(18)
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},    // UNKNOWN(19)
    };

    // HARDY(1), BOLD(2), MODEST(3), CALM(4), TIMID(5), LONELY(6), DOCILE(7), MILD(8), GENTLE(9), HASTY(10),
    // ADAMANT(11), IMPISH(12), BASHFUL(13), CAREFUL(14), RASH(15), JOLLY(16), NAUGHTY(17), LAX(18), QUIRKY(19),
    // NAIVE(20), BRAVE(21), RELAXED(22), QUIET(23), SASSY(24), SERIOUS(25)
    public static final Map<PokeNatureEnum, PokeNature> NATURE_MAP;

    static {
        Map<PokeNatureEnum, PokeNature> map = new HashMap<>();
        map.put(PokeNatureEnum.ADAMANT, new PokeNature(PokeNatureEnum.ADAMANT, PokeStatTypeEnum.ATTACK, PokeStatTypeEnum.SPECIAL_ATTACK));
        map.put(PokeNatureEnum.BASHFUL, new PokeNature(PokeNatureEnum.BASHFUL, PokeStatTypeEnum.SPECIAL_ATTACK, PokeStatTypeEnum.SPECIAL_ATTACK));
        map.put(PokeNatureEnum.BOLD, new PokeNature(PokeNatureEnum.BOLD, PokeStatTypeEnum.DEFENSE, PokeStatTypeEnum.ATTACK));
        map.put(PokeNatureEnum.BRAVE, new PokeNature(PokeNatureEnum.BRAVE, PokeStatTypeEnum.ATTACK, PokeStatTypeEnum.SPEED));
        map.put(PokeNatureEnum.CALM, new PokeNature(PokeNatureEnum.CALM, PokeStatTypeEnum.SPECIAL_DEFENSE, PokeStatTypeEnum.ATTACK));
        map.put(PokeNatureEnum.CAREFUL, new PokeNature(PokeNatureEnum.CAREFUL, PokeStatTypeEnum.SPECIAL_DEFENSE, PokeStatTypeEnum.SPECIAL_ATTACK));
        map.put(PokeNatureEnum.DOCILE, new PokeNature(PokeNatureEnum.DOCILE, PokeStatTypeEnum.DEFENSE, PokeStatTypeEnum.DEFENSE));
        map.put(PokeNatureEnum.GENTLE, new PokeNature(PokeNatureEnum.GENTLE, PokeStatTypeEnum.SPECIAL_DEFENSE, PokeStatTypeEnum.DEFENSE));
        map.put(PokeNatureEnum.HARDY, new PokeNature(PokeNatureEnum.HARDY, PokeStatTypeEnum.ATTACK, PokeStatTypeEnum.ATTACK));
        map.put(PokeNatureEnum.HASTY, new PokeNature(PokeNatureEnum.HASTY, PokeStatTypeEnum.SPEED, PokeStatTypeEnum.DEFENSE));
        map.put(PokeNatureEnum.IMPISH, new PokeNature(PokeNatureEnum.IMPISH, PokeStatTypeEnum.DEFENSE, PokeStatTypeEnum.SPECIAL_ATTACK));
        map.put(PokeNatureEnum.JOLLY, new PokeNature(PokeNatureEnum.JOLLY, PokeStatTypeEnum.SPEED, PokeStatTypeEnum.SPECIAL_ATTACK));
        map.put(PokeNatureEnum.LAX, new PokeNature(PokeNatureEnum.LAX, PokeStatTypeEnum.DEFENSE, PokeStatTypeEnum.SPECIAL_DEFENSE));
        map.put(PokeNatureEnum.LONELY, new PokeNature(PokeNatureEnum.LONELY, PokeStatTypeEnum.ATTACK, PokeStatTypeEnum.DEFENSE));
        map.put(PokeNatureEnum.MILD, new PokeNature(PokeNatureEnum.MILD, PokeStatTypeEnum.SPECIAL_ATTACK, PokeStatTypeEnum.DEFENSE));
        map.put(PokeNatureEnum.MODEST, new PokeNature(PokeNatureEnum.MODEST, PokeStatTypeEnum.SPECIAL_ATTACK, PokeStatTypeEnum.ATTACK));
        map.put(PokeNatureEnum.NAIVE, new PokeNature(PokeNatureEnum.NAIVE, PokeStatTypeEnum.SPEED, PokeStatTypeEnum.SPECIAL_DEFENSE));
        map.put(PokeNatureEnum.NAUGHTY, new PokeNature(PokeNatureEnum.NAUGHTY, PokeStatTypeEnum.ATTACK, PokeStatTypeEnum.SPECIAL_DEFENSE));
        map.put(PokeNatureEnum.QUIET, new PokeNature(PokeNatureEnum.QUIET, PokeStatTypeEnum.SPECIAL_ATTACK, PokeStatTypeEnum.SPEED));
        map.put(PokeNatureEnum.QUIRKY, new PokeNature(PokeNatureEnum.QUIRKY, PokeStatTypeEnum.SPECIAL_DEFENSE, PokeStatTypeEnum.SPECIAL_DEFENSE));
        map.put(PokeNatureEnum.RASH, new PokeNature(PokeNatureEnum.RASH, PokeStatTypeEnum.SPECIAL_ATTACK, PokeStatTypeEnum.SPECIAL_DEFENSE));
        map.put(PokeNatureEnum.RELAXED, new PokeNature(PokeNatureEnum.RELAXED, PokeStatTypeEnum.DEFENSE, PokeStatTypeEnum.SPEED));
        map.put(PokeNatureEnum.SASSY, new PokeNature(PokeNatureEnum.SASSY, PokeStatTypeEnum.SPECIAL_DEFENSE, PokeStatTypeEnum.SPEED));
        map.put(PokeNatureEnum.SERIOUS, new PokeNature(PokeNatureEnum.SERIOUS, PokeStatTypeEnum.SPEED, PokeStatTypeEnum.SPEED));
        map.put(PokeNatureEnum.TIMID, new PokeNature(PokeNatureEnum.TIMID, PokeStatTypeEnum.SPEED, PokeStatTypeEnum.ATTACK));
        NATURE_MAP = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, Double> STAT_STAGES;

    static {
        Map<Integer, Double> map = new HashMap<>();
        map.put(6, 8 / 2.0);
        map.put(5, 7 / 2.0);
        map.put(4, 6 / 2.0);
        map.put(3, 5 / 2.0);
        map.put(2, 4 / 2.0);
        map.put(1, 3 / 2.0);
        map.put(0, 2 / 2.0);
        map.put(-1, 2 / 3.0);
        map.put(-2, 2 / 4.0);
        map.put(-3, 2 / 5.0);
        map.put(-4, 2 / 6.0);
        map.put(-5, 2 / 7.0);
        map.put(-6, 2 / 8.0);
        STAT_STAGES = Collections.unmodifiableMap(map);
    }

    // NOTE* Accuracy and Evasion are inverses of each other
    // -6 accuracy multiplier is the same as +6 evasion multiplier
    public static final Map<Integer, Double> EVA_ACC_STAGES;

    static {
        Map<Integer, Double> map = new HashMap<>();
        map.put(6, 9 / 3.0);
        map.put(5, 8 / 3.0);
        map.put(4, 7 / 3.0);
        map.put(3, 6 / 3.0);
        map.put(2, 5 / 3.0);
        map.put(1, 4 / 3.0);
        map.put(0, 3 / 3.0);
        map.put(-1, 3 / 4.0);
        map.put(-2, 3 / 5.0);
        map.put(-3, 3 / 6.0);
        map.put(-4, 3 / 7.0);
        map.put(-5, 3 / 8.0);
        map.put(-6, 3 / 9.0);
        EVA_ACC_STAGES = Collections.unmodifiableMap(map);
    }

    public static int calculatePokeHp(int baseHp) {
        // HP = (((2 * BaseHp + IV + (EV/4)) * 100) / 100) + Level + 10
        return (int) (((2 * baseHp + 31 + (1 / 4.0)) * 100) / 100) + 100 + 10;
    }

    public static int calculateOtherStats(int baseStat, @Nonnull PokeStatTypeEnum stat, @Nonnull PokeNatureEnum nature) {
        double natureMultiplier = 1.0;
        PokeNature pokeNature = NATURE_MAP.get(nature);
        if (stat == pokeNature.getIncreaseStat()) {
            natureMultiplier = 1.1;
        } else if (stat == pokeNature.getDecreaseStat()) {
            natureMultiplier = 0.9;
        }

        // Stat = ((((2 * BaseStat + IV + (EV/4)) * 100) / 100) + 5) * Nature
        return (int) (((((2 * baseStat + 31 + (1 / 4.0)) * 100) / 100) + 5) * natureMultiplier);
    }

    // Should pass the target Pokemon (at least their evasion stage)
    public static boolean willMoveHit(PokeMove move) {
        // T = AccuracyMove * Adjusted_stages * Other_mods
//        The game then selects a random number r from 1 to 100 and compares it to T to determine whether the
//        move hits. If r is less than or equal to T, the move hits

        // AccuracyMove = moves accuracy
        // Adjusted_stages = is the equivalent accuracy stage multiplier of the user after the target's
        //      evasion stage is subtracted from the user's accuracy stage
        // Other_mods = Other multipliers (Ability effects, move effects, and item effectsMissingno
        return false;
    }

    // Need user and target Pokemon along with the move being used
    // Should only return the damage dealt and not remove it from the Pokemon
    public static int calculateDamage(PokeMove move, @Nonnull Poke user, @Nonnull Poke target) {

        return 0;
    }
}