package ozmar.pokemonBattle.pokemonStatusConditions;

public enum NonVolatileStatus {
    NONE(0),
    BURN(1),
    FREEZE(2),
    PARALYSIS(3),
    POISON(4),
    BADLY_POISONED(5),
    SLEEP(6),
    MULTIPLE(7);    // Tri Attack, Secret Power, Fling, Psycho Shift, Toxic Spikes

    private final int id;
    public static final NonVolatileStatus[] status = values();

    NonVolatileStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
