package ozmar.commands.interfaces;

import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface PokeCommandInterface {

    @Nonnull
    String catchPokeCommand(@Nonnull CommandEvent event);

    @Nonnull
    String myPoke(@Nonnull CommandEvent event);

    @Nullable
    String replacePoke(@Nonnull CommandEvent event);
}
