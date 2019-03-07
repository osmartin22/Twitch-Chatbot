package ozmar.commands.interfaces;

import ozmar.CaughtPokeInfo;
import reactor.util.annotation.NonNull;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeName);

    @NonNull
    CaughtPokeInfo attemptCatch();
}
