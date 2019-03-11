package ozmar.PokeBattle;

public class PokeAllStats {
    private PokeStat hp;
    private PokeStat attack;
    private PokeStat specialAttack;
    private PokeStat defense;
    private PokeStat specialDefense;
    private PokeStat Speed;
    private PokeStat accuracy;
    private PokeStat evasion;

    public PokeAllStats(PokeStat hp, PokeStat attack, PokeStat specialAttack, PokeStat defense,
                        PokeStat specialDefense, PokeStat speed, PokeStat accuracy, PokeStat evasion) {
        this.hp = hp;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
        Speed = speed;
        this.accuracy = accuracy;
        this.evasion = evasion;
    }

    public PokeStat getHp() {
        return hp;
    }

    public void setHp(PokeStat hp) {
        this.hp = hp;
    }

    public PokeStat getAttack() {
        return attack;
    }

    public void setAttack(PokeStat attack) {
        this.attack = attack;
    }

    public PokeStat getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(PokeStat specialAttack) {
        this.specialAttack = specialAttack;
    }

    public PokeStat getDefense() {
        return defense;
    }

    public void setDefense(PokeStat defense) {
        this.defense = defense;
    }

    public PokeStat getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(PokeStat specialDefense) {
        this.specialDefense = specialDefense;
    }

    public PokeStat getSpeed() {
        return Speed;
    }

    public void setSpeed(PokeStat speed) {
        Speed = speed;
    }

    public PokeStat getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(PokeStat accuracy) {
        this.accuracy = accuracy;
    }

    public PokeStat getEvasion() {
        return evasion;
    }

    public void setEvasion(PokeStat evasion) {
        this.evasion = evasion;
    }
}
