package ozmar.pokemonBattle.pokemonNature;

public enum PokeNatureEnum {
    HARDY(1),
    BOLD(2),
    MODEST(3),
    CALM(4),
    TIMID(5),
    LONELY(6),
    DOCILE(7),
    MILD(8),
    GENTLE(9),
    HASTY(10),
    ADAMANT(11),
    IMPISH(12),
    BASHFUL(13),
    CAREFUL(14),
    RASH(15),
    JOLLY(16),
    NAUGHTY(17),
    LAX(18),
    QUIRKY(19),
    NAIVE(20),
    BRAVE(21),
    RELAXED(22),
    QUIET(23),
    SASSY(24),
    SERIOUS(25);

    private final int id;
    public static final PokeNatureEnum[] natures = values();

    PokeNatureEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
