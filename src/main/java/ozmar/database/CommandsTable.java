package ozmar.database;

import ozmar.Command;
import ozmar.enums.CommandNumPermission;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandsTable {

    private static final String COMMANDS_TABLE = "commandsTable";
    private static final String COLUMN_COMMAND_ID = "id";
    private static final String COLUMN_COMMAND_NAME = "commandName";
    private static final String COLUMN_COMMAND_PERMISSION = "commandPermission";

    private static final String CREATE_COMMANDS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + COMMANDS_TABLE + " ( " +
                    COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_COMMAND_NAME + " TEXT, " +
                    COLUMN_COMMAND_PERMISSION + " INTEGER)";

    private final static String insertCommandSql =
            "INSERT INTO " + COMMANDS_TABLE + " ( " +
                    COLUMN_COMMAND_NAME + " , " +
                    COLUMN_COMMAND_PERMISSION + " ) " +
                    " VALUES(?, ?)";

    public CommandsTable() {

    }

    public String getTableName() {
        return COMMANDS_TABLE;
    }

    public String getCreateTableSql() {
        return CREATE_COMMANDS_TABLE;
    }

    public void initializeCommands() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Command("!dice", CommandNumPermission.EVERYONE));           // 0
        commandList.add(new Command("!hello", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!count", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!uptime", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!calc", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!followage", CommandNumPermission.EVERYONE));      // 5
        commandList.add(new Command("!wordCount", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!clearWordCount", CommandNumPermission.MODERATOR));
        commandList.add(new Command("!31", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!messageCount", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!points", CommandNumPermission.EVERYONE));         // 10
        insertCommandsList(commandList);
    }

    public List<Command> queryCommands() {
        Connection connection = DatabaseHandler.openConnection();

        List<Command> commandList = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + COMMANDS_TABLE)) {

            commandList = new ArrayList<>();
            while (resultSet.next()) {
                int commandId = resultSet.getInt(COLUMN_COMMAND_ID);
                String commandName = resultSet.getString(COLUMN_COMMAND_NAME);
                int commandPermissions = resultSet.getInt(COLUMN_COMMAND_PERMISSION);

                // TODO: Change enum.values()[] to something more efficient
                commandList.add(new Command(commandId, commandName, CommandNumPermission.values()[commandPermissions]));
            }

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
        return commandList;
    }

    private void insertCommandsList(@Nonnull List<Command> commandList) {
        Connection connection = DatabaseHandler.openConnection();
        DatabaseHandler.turnOffAutoCommit(connection);
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, insertCommandSql);

        try {
            for (Command command : commandList) {
                preparedStatement.setString(1, command.getCommand());
                preparedStatement.setInt(2, command.getPermission().getCommandLevel());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Inserting command list failed: " + e.getMessage());
        }

        DatabaseHandler.closeStatement(preparedStatement);
        DatabaseHandler.turnOnAutoCommit(connection);
        DatabaseHandler.closeConnection(connection);
    }

    public void insertCommand(@Nonnull Command command) {
        Connection connection = DatabaseHandler.openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCommandSql)) {
            preparedStatement.setString(1, command.getCommand());
            preparedStatement.setInt(2, command.getPermission().getCommandLevel());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Adding command failed: " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
    }
}
