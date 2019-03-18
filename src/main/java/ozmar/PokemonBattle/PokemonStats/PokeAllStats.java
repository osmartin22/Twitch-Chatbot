package ozmar.PokemonBattle.PokemonStats;

public class PokeAllStats {

    private int currHp;
    private int maxHp;
    private final PokeStat attack;
    private final PokeStat defense;
    private final PokeStat specialAttack;
    private final PokeStat specialDefense;
    private final PokeStat speed;
    private final PokeStat evasion;
    private final PokeStat accuracy;

    public PokeAllStats(int maxHp, PokeStat attack, PokeStat defense, PokeStat specialAttack,
                        PokeStat specialDefense, PokeStat speed, PokeStat evasion, PokeStat accuracy) {
        this.currHp = maxHp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.evasion = evasion;
        this.accuracy = accuracy;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrHp() {
        return currHp;
    }

    public boolean isAlive() {
        return currHp > 0;
    }

    // Damage should be a negative number
    // Healing should be a positive number
    public void updateCurrHp(int hpChange) {
        int tempHp = currHp + hpChange;
        if (tempHp <= 0) {
            currHp = 0;
        } else if (tempHp > currHp) {
            currHp = maxHp;
        }

        currHp = tempHp;
    }

    public boolean updateAttackStage(int stageChange) {
        return attack.updateStatStage(stageChange);
    }

    public void resetAttackStage() {
        attack.resetStatStage();
    }

    public boolean updateDefenseStage(int stageChange) {
        return defense.updateStatStage(stageChange);
    }

    public void resetDefenseStage() {
        defense.resetStatStage();
    }

    public boolean updateSpecialAttackStage(int stageChange) {
        return specialAttack.updateStatStage(stageChange);
    }

    public void resetSpecialAttackStage() {
        specialAttack.resetStatStage();
    }

    public boolean updateSpecialDefenseStage(int stageChange) {
        return specialDefense.updateStatStage(stageChange);
    }

    public void resetSpecialDefenseStage() {
        specialDefense.resetStatStage();
    }

    public boolean updateSpeedStage(int stageChange) {
        return speed.updateStatStage(stageChange);
    }

    public void resetSpeedStage() {
        speed.resetStatStage();
    }

    public boolean updateAccuracyStage(int stageChange) {
        return accuracy.updateStatStage(stageChange);
    }

    public void resetAccuracyStage() {
        accuracy.resetStatStage();
    }

    public boolean updateEvasionStage(int stageChange) {
        return evasion.updateStatStage(stageChange);
    }

    public void resetEvasionStage() {
        evasion.resetStatStage();
    }
}
