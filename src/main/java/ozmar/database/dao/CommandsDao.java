package ozmar.database.dao;

import ozmar.commands.Command;
import ozmar.database.dao.interfaces.CommandsDaoInterface;
import ozmar.database.tables.interfaces.CommandsTableInterface;

import javax.annotation.Nonnull;
import java.util.List;

public class CommandsDao implements CommandsDaoInterface {

    private CommandsTableInterface commandsTable;

    public CommandsDao(CommandsTableInterface commandsTable) {
        this.commandsTable = commandsTable;
    }

    @Nonnull
    @Override
    public List<Command> queryCommands() {
        return commandsTable.queryCommands();
    }

    @Override
    public void insertCommand(@Nonnull Command command) {
        commandsTable.insertCommand(command);
    }

    @Override
    public void updateCommandUsage(@Nonnull Command command) {
        commandsTable.updateCommandUsage(command);
    }

    @Override
    public boolean updateCommandCooldown(@Nonnull String commandName, long cooldown) {
        return commandsTable.updateCommandCooldown(commandName, cooldown);
    }
}
