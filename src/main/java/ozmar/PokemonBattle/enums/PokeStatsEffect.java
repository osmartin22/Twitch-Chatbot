package ozmar.PokemonBattle.enums;

public enum PokeStatsEffect {
    LOWER_ATK(0),
    LOWER_DEF(1),
    LOWER_SPATK(2),
    LOWER_SPDEF(3),
    LOWER_SPD(4),
    LOWER_EVA(5),
    LOWER_ACC(6),
    INCREASE_ATK(7),
    INCREASE_DEF(8),
    INCREASE_SPATK(9),
    INCREASE_SPDEF(10),
    INCREASE_SPD(11),
    INCREASE_EVA(12),
    INCREASE_ACC(13);

    private final int id;

    PokeStatsEffect(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
