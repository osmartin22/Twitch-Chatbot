package ozmar.pokemonBattle.pokemonNature;

import javax.annotation.Nonnull;

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

    @Nonnull
    public static PokeNatureEnum getEnum(@Nonnull String name) {
        PokeNatureEnum natureEnum;
        switch (name) {
            case "hardy":
                natureEnum = HARDY;
                break;
            case "bold":
                natureEnum = BOLD;
                break;
            case "modest":
                natureEnum = MODEST;
                break;
            case "calm":
                natureEnum = CALM;
                break;
            case "timid":
                natureEnum = TIMID;
                break;
            case "lonely":
                natureEnum = LONELY;
                break;
            case "docile":
                natureEnum = DOCILE;
                break;
            case "mild":
                natureEnum = MILD;
                break;
            case "gentle":
                natureEnum = GENTLE;
                break;
            case "hasty":
                natureEnum = HASTY;
                break;
            case "adamant":
                natureEnum = ADAMANT;
                break;
            case "impish":
                natureEnum = IMPISH;
                break;
            case "bashful":
                natureEnum = BASHFUL;
                break;
            case "careful":
                natureEnum = CAREFUL;
                break;
            case "rash":
                natureEnum = RASH;
                break;
            case "jolly":
                natureEnum = JOLLY;
                break;
            case "naughty":
                natureEnum = NAUGHTY;
                break;
            case "lax":
                natureEnum = LAX;
                break;
            case "quirky":
                natureEnum = QUIRKY;
                break;
            case "naive":
                natureEnum = NAIVE;
                break;
            case "brave":
                natureEnum = BRAVE;
                break;
            case "relaxed":
                natureEnum = RELAXED;
                break;
            case "quiet":
                natureEnum = QUIET;
                break;
            case "sassy":
                natureEnum = SASSY;
                break;
            case "serious":
            default:
                natureEnum = SERIOUS;
                break;
        }

        return natureEnum;
    }
}
