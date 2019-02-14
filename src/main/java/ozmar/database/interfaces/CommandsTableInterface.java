package ozmar.database.interfaces;

import ozmar.Command;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface CommandsTableInterface {

    @Nonnull
    String getCreateTableSql();

    void initializeCommands();

    @Nullable
    List<Command> queryCommands();

    void insertCommand(@Nonnull Command command);
}
