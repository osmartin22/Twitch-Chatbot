package ozmar.commands.interfaces;

import ozmar.CaughtPokeInfo;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.Set;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeInput);

    @NonNull
    CaughtPokeInfo attemptCatch();

    @Nullable
    CaughtPokeInfo catchMissingNo();

    @NonNull
    Set<String> getRegionNames();
}
