package ozmar.database.tables.interfaces;

import ozmar.commands.Command;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface CommandsTableInterface {

    @Nullable
    List<Command> queryCommands();

    void insertCommand(@Nonnull Command command);

    void updateCommandUsage(@Nonnull Command command);
}
