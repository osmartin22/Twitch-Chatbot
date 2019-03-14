package ozmar.PokemonBattle.PokemonStats;

public class PokeStat {

    // The HP stat should not use this class as it is directly modified unlike the other stats
    // Pokemon stat modifications are done in "Stages" instead of directly increasing the stat value
    private int value;
    private int statStage;

    private static final int MAX_STAT_STAGE = 6;
    private static final int MIN_STAT_STAGE = -6;

    public PokeStat(int value) {
        this.value = value;
        this.statStage = 0;
    }

    public int getValue() {
        return value;
    }

    public int getStatStage() {
        return statStage;
    }

    /**
     * Adds the stat stage change to the current stat stage
     * Positive values are considered an increase to the stage
     * Negative values are considered a decrease to the stage
     * Returns false if the stage is already at the highest or lowest it can be at
     *
     * @param statStageChange change to the stat stage
     * @return boolean
     */
    public boolean updateStatStage(int statStageChange) {
        int tempStage = statStage + statStageChange;
        if (tempStage > MAX_STAT_STAGE || tempStage < MIN_STAT_STAGE) {
            return false;
        }

        statStage = tempStage;
        return true;
    }

    /**
     * Resets the stage back to its original stage 0
     */
    public void resetStatStage() {
        this.statStage = 0;
    }
}
