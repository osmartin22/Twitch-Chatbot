package ozmar.PokemonBattle.PokemonStatusConditions;

public enum VolatileStatus {
    BOUND(0),
    CANT_ESCAPE(1),
    CONFUSION(2),
    CURSE(3),
    EMBARGO(4),
    ENCORE(5),
    FLINCH(6),
    HEAL_BLOCK(7),
    IDENTIFIED(8),
    INFATUATION(9),
    LEECH_SEED(10),
    NIGHTMARE(11),
    PERISH_SONG(12),
    TAUNT(13),
    TELEKINESIS(14),
    TORMENT(15);

    private final int id;

    VolatileStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
