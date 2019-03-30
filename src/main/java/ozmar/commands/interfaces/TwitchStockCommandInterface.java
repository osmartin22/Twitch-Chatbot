package ozmar.commands.interfaces;

import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface TwitchStockCommandInterface {

    @Nullable
    String getStock(@Nonnull CommandEvent event);
}
