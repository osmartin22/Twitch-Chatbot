package ozmar.PokemonBattle.PokemonType;

public enum PokeTypeEnum {
    NONE(0),    // Only to be used for the second and third type of a Pokemon
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
    FAIRY(18),
    UNKNOWN(19);  // Only to be used for the first type of a Pokemon in rare cases, also known as type "???"

    private final int id;
    public static final PokeTypeEnum[] types = values();

    PokeTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
