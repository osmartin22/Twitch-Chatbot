package ozmar.database.dao.interfaces;

import ozmar.commands.Command;

import javax.annotation.Nonnull;
import java.util.List;

public interface CommandsDaoInterface {

    @Nonnull
    List<Command> queryCommands();

    void insertCommand(@Nonnull Command command);

    void updateCommandUsage(@Nonnull Command command);

    boolean updateCommandCooldown(@Nonnull String commandName, long cooldown);
}
