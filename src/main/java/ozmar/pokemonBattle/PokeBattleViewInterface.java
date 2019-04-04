package ozmar.pokemonBattle;

import javax.annotation.Nonnull;

public interface PokeBattleViewInterface {

    void sendUserMessage(long userId, @Nonnull String message);

    void sendMessageForAll(@Nonnull String message);
}
