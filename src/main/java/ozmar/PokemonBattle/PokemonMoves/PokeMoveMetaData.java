package ozmar.PokemonBattle.PokemonMoves;

import ozmar.PokemonBattle.PokemonStats.PokeStatsEffect;
import ozmar.PokemonBattle.PokemonStatusConditions.StatusConditionNonVolatile;
import poke_models.contests.ContestType;

import java.util.Set;

public class PokeMoveMetaData {

    private int generation;
    private ContestType contestType;

    private int nonVolatileChance;
    private Set<StatusConditionNonVolatile> nonVolatileSet;

    private int flinchChance;
    private int confusionChance;

    private PokeTarget target;

    private boolean isContact;

    private int statsStageRaise;
    private int statsEffectChance;
    private Set<PokeStatsEffect> statsEffectSet;

    private Set<PokeMoveByUsage> moveByUsageSet;


//    PokeMoveAilment moveAilment;
//    PokeMoveCategory moveCategory;
//    private int critRate;
//    private int drain;
//    private int flinch_chance;
//    private int healing;
//    private int maxHits;
//    private int maxTurns;
//    private int minHits;
//    private int minTurns;
//    private int statChance;

}
