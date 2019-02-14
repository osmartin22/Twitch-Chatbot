package ozmar.commands.interfaces;

import javax.annotation.Nonnull;

public interface CatchPokeInterface {

    int initialize(int pokeId);

    int initialize(String pokeName);

    @Nonnull
    String attemptCatch();
}
