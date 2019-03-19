package ozmar.pokemonBattle.pokemonStats;

import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class PokeAllStages {

    private Map<PokeStatStage, Integer> statStages;
    private static final int MAX_STAT_STAGE = 6;
    private static final int MIN_STAT_STAGE = -6;

    public PokeAllStages() {
        statStages = new HashMap<>();
        statStages.put(PokeStatStage.ATK_STAGE, 0);
        statStages.put(PokeStatStage.DEF_STAGE, 0);
        statStages.put(PokeStatStage.SPC_ATK_STAGE, 0);
        statStages.put(PokeStatStage.SPC_DEF_STAGE, 0);
        statStages.put(PokeStatStage.SPD_STAGE, 0);
        statStages.put(PokeStatStage.EVA_STAGE, 0);
        statStages.put(PokeStatStage.ACC_STAGE, 0);
    }


    public int getStateStage(@Nonnull PokeStatStage statType) {
        return statStages.get(statType);
    }

    /**
     * Modifies the stats stage
     * Max and Min stages are +6/-6
     * Negative stage changes should be passed in as a negative number, positive for positive stage changes
     * Returns whether the stage could be lowered/increased any further
     *
     * @param stat        stat to modify stage
     * @param stageChange change in stages
     * @return boolean
     */
    public boolean modifyStage(@Nonnull PokeStatStage stat, int stageChange) {
        boolean canStatChange = false;
        int stage = statStages.get(stat);

        if (stage > MIN_STAT_STAGE && stage < MAX_STAT_STAGE) {
            int tempStage = stage + stageChange;
            if (tempStage < MIN_STAT_STAGE) {
                tempStage = MIN_STAT_STAGE;
            } else if (tempStage > MAX_STAT_STAGE) {
                tempStage = MAX_STAT_STAGE;
            }

            canStatChange = true;
            statStages.put(stat, tempStage);
        }

        return canStatChange;
    }

    /**
     * Resets all the stages to their original stage of zero
     */
    public void resetAllStages() {
        for (PokeStatStage stage : statStages.keySet()) {
            statStages.put(stage, 0);
        }
    }

    /**
     * Resets the stat back to its original stage of zero
     *
     * @param stat stat to reset
     */
    public void resetStage(@Nonnull PokeStatStage stat) {
        statStages.put(stat, 0);
    }
}
