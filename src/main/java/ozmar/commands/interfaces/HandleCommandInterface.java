package ozmar.commands.interfaces;

import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface HandleCommandInterface {

    void setCommandEvent(@Nonnull CommandEvent commandEvent);

    @Nullable
    String decideCommand();
}
