package ozmar.PokemonBattle.PokemonStatusConditions;

public enum StatusConditionNonVolatile {
    NONE(0),
    BURN(1),
    FREEZE(2),
    PARALYSIS(3),
    POISON(4),
    BADLY_POISONED(5),
    SLEEP(6);

    private final int id;

    StatusConditionNonVolatile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
