package ozmar.PokemonBattle.PokemonStats;

public class PokeAllStats {

    private int startingHp;
    private int currHp;
    private final PokeStat attack;
    private final PokeStat defense;
    private final PokeStat specialAttack;
    private final PokeStat specialDefense;
    private final PokeStat speed;
    private final PokeStat accuracy;
    private final PokeStat evasion;

    public PokeAllStats(int startingHp, PokeStat attack, PokeStat defense, PokeStat specialAttack,
                        PokeStat specialDefense, PokeStat speed, PokeStat accuracy, PokeStat evasion) {
        this.startingHp = startingHp;
        this.currHp = startingHp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.accuracy = accuracy;
        this.evasion = evasion;
    }

    public int getStartingHp() {
        return startingHp;
    }

    public int getCurrHp() {
        return currHp;
    }

    // Damage should be a negative number
    // Healing should be a positive number
    public boolean updateCurrHp(int hpChange) {
        int tempHp = currHp + hpChange;
        if (tempHp <= 0) {
            return true;
        } else if (tempHp > currHp) {
            currHp = startingHp;
        }

        currHp = tempHp;
        return false;
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