package ozmar.pokemonBattle.pokemonMoves;

import ozmar.pokemonBattle.pokemonMoves.enums.PokeContestCondition;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveByUsage;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeTarget;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatsEffect;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;

import java.util.Set;

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
public class PokeMoveMetaData {

    private final int generation;
    private final PokeContestCondition contestCondition;

    private final boolean isContact;
    private final boolean isDirectAttack;

    private final int flinchChance;
    private final int confusionChance;
    private final int nonVolatileChance;
    private final Set<NonVolatileStatus> nonVolatileStatusSet;

    private final PokeTarget target;

    private final int statsStageRaise;
    private final int statsEffectChance;
    private final Set<PokeStatsEffect> statsEffectSet;

    private final Set<PokeMoveByUsage> moveByUsageSet;

    private final int critStage;

    public PokeMoveMetaData(int generation, PokeContestCondition contestCondition, boolean isContact, boolean isDirectAttack,
                            int flinchChance, int confusionChance, int nonVolatileChance,
                            Set<NonVolatileStatus> nonVolatileStatusSet, PokeTarget target, int statsStageRaise,
                            int statsEffectChance, Set<PokeStatsEffect> statsEffectSet, Set<PokeMoveByUsage> moveByUsageSet,
                            int critStage) {
        this.generation = generation;
        this.contestCondition = contestCondition;
        this.isContact = isContact;
        this.isDirectAttack = isDirectAttack;
        this.flinchChance = flinchChance;
        this.confusionChance = confusionChance;
        this.nonVolatileChance = nonVolatileChance;
        this.nonVolatileStatusSet = nonVolatileStatusSet;
        this.target = target;
        this.statsStageRaise = statsStageRaise;
        this.statsEffectChance = statsEffectChance;
        this.statsEffectSet = statsEffectSet;
        this.moveByUsageSet = moveByUsageSet;
        this.critStage = critStage;
    }

    public int getGeneration() {
        return generation;
    }

    public PokeContestCondition getContestCondition() {
        return contestCondition;
    }

    public boolean isContact() {
        return isContact;
    }

    public boolean isDirectAttack() {
        return isDirectAttack;
    }

    public int getFlinchChance() {
        return flinchChance;
    }

    public int getConfusionChance() {
        return confusionChance;
    }

    public int getNonVolatileChance() {
        return nonVolatileChance;
    }

    public Set<NonVolatileStatus> getNonVolatileStatusSet() {
        return nonVolatileStatusSet;
    }

    public PokeTarget getTarget() {
        return target;
    }

    public int getStatsStageRaise() {
        return statsStageRaise;
    }

    public int getStatsEffectChance() {
        return statsEffectChance;
    }

    public Set<PokeStatsEffect> getStatsEffectSet() {
        return statsEffectSet;
    }

    public Set<PokeMoveByUsage> getMoveByUsageSet() {
        return moveByUsageSet;
    }

    public int getCritStage() {
        return critStage;
    }
}
