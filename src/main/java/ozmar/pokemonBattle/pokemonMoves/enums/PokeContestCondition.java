package ozmar.pokemonBattle.pokemonMoves.enums;

public enum PokeContestCondition {
    COOL(0),
    BEAUTIFUL(1),
    CUTE(2),
    CLEVER(3),
    TOUGH(4),
    UNKNOWN(5);

    private final int id;
    public static final PokeContestCondition[] condiitons = values();

    PokeContestCondition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
