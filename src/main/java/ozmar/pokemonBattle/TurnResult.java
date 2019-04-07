package ozmar.pokemonBattle;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class TurnResult {

    private final String result;
    private final Map<Long, Set<Integer>> faintedPokemon;

    public TurnResult(String result, Map<Long, Set<Integer>> faintedPokemon) {
        this.result = result;
        this.faintedPokemon = faintedPokemon;
    }

    @Nonnull
    public String getResult() {
        return result;
    }

    @Nonnull
    public Map<Long, Set<Integer>> getFaintedPokemon() {
        return faintedPokemon;
    }
}
