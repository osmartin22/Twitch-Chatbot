package ozmar.commands.interfaces;

import ozmar.CaughtPokeInfo;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeName);

    @NonNull
    CaughtPokeInfo attemptCatch();

    @Nullable
    CaughtPokeInfo catchMissingNo();
}
