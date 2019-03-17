package ozmar.PokemonBattle.enums;

public enum PokeStatsEffect {
    NONE(0),
    LOWER_ATK(1),
    LOWER_DEF(2),
    LOWER_SPATK(3),
    LOWER_SPDEF(4),
    LOWER_SPD(5),
    LOWER_EVA(6),
    LOWER_ACC(7),
    INCREASE_ATK(8),
    INCREASE_DEF(9),
    INCREASE_SPATK(10),
    INCREASE_SPDEF(11),
    INCREASE_SPD(12),
    INCREASE_EVA(13),
    INCREASE_ACC(14);

    private final int id;

    PokeStatsEffect(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
