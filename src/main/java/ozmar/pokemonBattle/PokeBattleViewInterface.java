package ozmar.pokemonBattle;

import javax.annotation.Nonnull;

public interface PokeBattleViewInterface {

    void sendUserMessage(@Nonnull String message);

    void sendMessageForAll(@Nonnull String message);
}
