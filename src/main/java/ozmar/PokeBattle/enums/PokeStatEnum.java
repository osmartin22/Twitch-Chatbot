package ozmar.PokeBattle.enums;

public enum PokeStatEnum {
    HP(1),
    ATTACK(2),
    DEFENSE(3),
    SPECIAL_ATTACK(4),
    SPECIAL_DEFENSE(5),
    SPEED(6),
    ACCURACY(7),
    EVASION(8);

    private final int id;

    PokeStatEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
