package ozmar.pokemonBattle.pokemonMoves;

import ozmar.pokemonBattle.pokemonStats.enums.PokeStatsEffect;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import poke_models.contests.ContestType;

import java.util.Set;

public class PokeMoveMetaData {

    private int generation;
    private ContestType contestType;

    private boolean isContact;
    private boolean isDirectAttack;

    private int flinchChance;
    private int confusionChance;
    private int nonVolatileChance;
    private Set<NonVolatileStatus> nonVolatileSet;

    private PokeTarget target;

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
