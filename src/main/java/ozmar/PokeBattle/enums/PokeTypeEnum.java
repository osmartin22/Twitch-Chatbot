package ozmar.PokeBattle.enums;

public enum PokeTypeEnum {
    NONE(0),    // NONE to be used only for the second type of a pokemon, (Fire/Flying), (Fire/None)
    NORMAL(1),
    FIGHTING(2),
    FLYING(3),
    POISON(4),
    GROUND(5),
    ROCK(6),
    BUG(7),
    GHOST(8),
    STEEL(9),
    FIRE(10),
    WATER(11),
    GRASS(12),
    ELECTRIC(13),
    PSYCHIC(14),
    ICE(15),
    DRAGON(16),
    DARK(17),
    FAIRY(18);

    private final int id;

    PokeTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
