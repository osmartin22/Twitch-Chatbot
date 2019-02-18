package ozmar.commands.interfaces;

import javax.annotation.Nullable;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeName);

    @Nullable
    String attemptCatch();
}
