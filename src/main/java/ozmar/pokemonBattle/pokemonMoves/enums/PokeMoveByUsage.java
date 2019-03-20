package ozmar.pokemonBattle.pokemonMoves.enums;

public enum PokeMoveByUsage {
    NONE(0),
    AURA_AND_PULSE(1),
    BALL_AND_BOMB(2),
    BITING(3),
    DANCE(4),
    EXPLOSIVE(5),
    POWDER_AND_SPORE(6),
    PUNCHING(7),
    SOUND_BASED(8);

    private final int id;
    public static final PokeMoveByUsage[] usage = values();

    PokeMoveByUsage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
