package ozmar.PokeBattle;

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

    public boolean updateStatStage(int statStageChange) {
        int tempStage = statStage + statStageChange;
        if (tempStage > MAX_STAT_STAGE || tempStage < MIN_STAT_STAGE) {
            return false;
        }

        statStage = tempStage;
        return true;
    }

    public void resetStatStage() {
        this.statStage = 0;
    }
}
