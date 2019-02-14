package ozmar.commands.interfaces;

import com.github.twitch4j.chat.events.CommandEvent;

import javax.annotation.Nonnull;

public interface HandleCommandInterface {

    void setCommandEvent(@Nonnull CommandEvent commandEvent);

    @Nonnull
    String decideCommand();
}
