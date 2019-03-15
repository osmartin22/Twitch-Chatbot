package ozmar.PokemonBattle.enums;

public enum PokeContestCondition {
    COOL(0),
    BEAUTIFUL(1),
    CUTE(2),
    CLEVER(3),
    TOUGH(4),
    UNKNOWN(5);

    private final int id;

    PokeContestCondition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
