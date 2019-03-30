package ozmar.commands.interfaces;

import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;

public interface TwitchCallCommandInterface {

    @Nonnull
    String uptime(@Nonnull CommandEvent event);

    @Nonnull
    String followage(@Nonnull CommandEvent event);
}
