package ozmar.commands.interfaces;

import com.github.twitch4j.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface TwitchStockCommandInterface {

    @Nullable
    String getStock(@Nonnull CommandEvent event);
}
