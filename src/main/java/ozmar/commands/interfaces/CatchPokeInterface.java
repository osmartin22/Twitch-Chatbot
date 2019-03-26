package ozmar.commands.interfaces;

import ozmar.CaughtPokeInfo;
import reactor.util.annotation.Nullable;

import javax.annotation.Nonnull;
import java.util.Set;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeInput);

    @Nonnull
    CaughtPokeInfo attemptCatch();

    @Nullable
    CaughtPokeInfo catchMissingNo();

    @Nonnull
    Set<String> getRegionNames();
}
