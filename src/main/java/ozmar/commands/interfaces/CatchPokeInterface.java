package ozmar.commands.interfaces;

import ozmar.CaughtPokeInfo;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeName);

    @NonNull
    CaughtPokeInfo attemptCatch();

    void saveCatch(long userId, @NonNull CaughtPokeInfo pokeInfo);

    void removeCatch(long userId);

    @Nullable
    CaughtPokeInfo getSavedCatch(long userId);
}
