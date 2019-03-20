package ozmar.pokemonBattle.pokemonMoves;

import ozmar.pokemonBattle.pokemonMoves.enums.PokeContestCondition;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveByUsage;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeTarget;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatsEffect;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;

import java.util.Set;

public class PokeMoveMetaData {

    private int generation;
    private PokeContestCondition contestCondition;

    private boolean isContact;
    private boolean isDirectAttack;

    private int flinchChance;
    private int confusionChance;
    private int nonVolatileChance;
    private NonVolatileStatus nonVolatile;

    private PokeTarget target;

    private int statsStageRaise;
    private int statsEffectChance;
    private Set<PokeStatsEffect> statsEffectSet;

    private Set<PokeMoveByUsage> moveByUsageSet;

    public PokeMoveMetaData(int generation, PokeContestCondition contestCondition, boolean isContact, boolean isDirectAttack,
                            int flinchChance, int confusionChance, int nonVolatileChance,
                            NonVolatileStatus nonVolatile, PokeTarget target, int statsStageRaise,
                            int statsEffectChance, Set<PokeStatsEffect> statsEffectSet, Set<PokeMoveByUsage> moveByUsageSet) {
        this.generation = generation;
        this.contestCondition = contestCondition;
        this.isContact = isContact;
        this.isDirectAttack = isDirectAttack;
        this.flinchChance = flinchChance;
        this.confusionChance = confusionChance;
        this.nonVolatileChance = nonVolatileChance;
        this.nonVolatile = nonVolatile;
        this.target = target;
        this.statsStageRaise = statsStageRaise;
        this.statsEffectChance = statsEffectChance;
        this.statsEffectSet = statsEffectSet;
        this.moveByUsageSet = moveByUsageSet;
    }

    public Set<PokeStatsEffect> getStatsEffectSet() {
        return statsEffectSet;
    }

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
