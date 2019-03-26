package ozmar;


import javax.annotation.Nonnull;

public class CaughtPokeInfo {

    private PokemonPoke poke;
    private boolean isCaptured;
    private boolean isUnreleased;
    private String catchResultString;

    public CaughtPokeInfo(@Nonnull PokemonPoke poke, boolean isCaptured, boolean isUnreleased,
                          @Nonnull String catchResultString) {
        this.poke = poke;
        this.isCaptured = isCaptured;
        this.isUnreleased = isUnreleased;
        this.catchResultString = catchResultString;
    }

    @Nonnull
    public PokemonPoke getPoke() {
        return poke;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public boolean isUnreleased() {
        return isUnreleased;
    }

    @Nonnull
    public String getCatchResultString() {
        return catchResultString;
    }
}
