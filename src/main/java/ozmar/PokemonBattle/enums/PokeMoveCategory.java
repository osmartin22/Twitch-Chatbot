package ozmar.PokemonBattle.enums;

public enum PokeMoveCategory {
    DAMAGE(1),
    AILMENT(2),
    NET_GOOD_STATS(3),
    HEAL(4),
    DAMAGE_AND_AILMENT(4),
    SWAGGER(5),
    DAMAGE_AND_LOWER(6),
    DAMAGE_AND_RAISE(7),
    DAMAGE_AND_HEAL(8),
    OHKO(9),
    WHOLE_FIELD_EFFECT(10),
    FIELD_EFFECT(11),
    FORCE_SWITCH(12),
    UNIQUE(13);

    private final int id;

    PokeMoveCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}