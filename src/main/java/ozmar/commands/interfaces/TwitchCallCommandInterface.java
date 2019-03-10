package ozmar.commands.interfaces;

import com.github.twitch4j.chat.events.CommandEvent;

import javax.annotation.Nonnull;

public interface TwitchCallCommandInterface {

    @Nonnull
    String uptime(@Nonnull CommandEvent event);

    @Nonnull
    String followage(@Nonnull CommandEvent event);
}
