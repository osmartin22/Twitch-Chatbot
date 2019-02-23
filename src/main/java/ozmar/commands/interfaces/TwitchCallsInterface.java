package ozmar.commands.interfaces;

import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

import javax.annotation.Nonnull;

public interface TwitchCallsInterface {

    @Nonnull
    String uptime(@Nonnull CommandEvent event);

    @Nonnull
    String followage(@Nonnull CommandEvent event, @Nonnull DatabaseHandlerInterface db);
}
