package ozmar;

import reactor.util.annotation.NonNull;

public class CaughtPokeInfo {

    private PokemonPoke poke;
    private boolean isCaptured;
    private boolean isUnreleased;
    private String catchResultString;

    public CaughtPokeInfo(@NonNull PokemonPoke poke, boolean isCaptured, boolean isUnreleased,
                          @NonNull String catchResultString) {
        this.poke = poke;
        this.isCaptured = isCaptured;
        this.isUnreleased = isUnreleased;
        this.catchResultString = catchResultString;
    }

    @NonNull
    public PokemonPoke getPoke() {
        return poke;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public boolean isUnreleased() {
        return isUnreleased;
    }

    @NonNull
    public String getCatchResultString() {
        return catchResultString;
    }
}
