package ozmar.PokemonBattle.PokemonStatusConditions;

public enum NonVolatileStatus {
    NONE(0),
    BURN(1),
    FREEZE(2),
    PARALYSIS(3),
    POISON(4),
    BADLY_POISONED(5),
    SLEEP(6);

    private final int id;
    public static final NonVolatileStatus[] status = values();

    NonVolatileStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
