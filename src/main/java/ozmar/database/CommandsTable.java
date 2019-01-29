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
    private static final int INDEX_COMMAND_ID = 1;
    private static final int INDEX_COMMAND_NAME = 2;
    private static final int INDEX_COMMAND_PERMISSIONS = 3;

    private static final String CREATE_COMMANDS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + COMMANDS_TABLE + " ("
            + COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_COMMAND_NAME + " TEXT, "
            + COLUMN_COMMAND_PERMISSION + " INTEGER)";

    private final static String addCommandStatement = "INSERT INTO " + COMMANDS_TABLE + " (" + COLUMN_COMMAND_NAME + ", "
            + COLUMN_COMMAND_PERMISSION + ")" + " VALUES(?, ?)";

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
        commandList.add(new Command("!clearWordCount", CommandNumPermission.OWNER)); // CHANGE PERMISSION
        commandList.add(new Command("!31", CommandNumPermission.EVERYONE));
        commandList.add(new Command("!hugme", CommandNumPermission.EVERYONE));
//        commandList.add(new Command("!", CommandNumPermission.EVERYONE));                     // 10
//        commandList.add(new Command("!", CommandNumPermission.EVERYONE));

        addCommandsList(commandList);
    }

    public List<Command> queryCommands() {
        Connection connection = DatabaseHandler.openConnection();

        List<Command> commandList = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + COMMANDS_TABLE)) {

            commandList = new ArrayList<>();
            while (resultSet.next()) {
                int commandId = resultSet.getInt(INDEX_COMMAND_ID);
                String commandName = resultSet.getString(INDEX_COMMAND_NAME);
                int commandPermissions = resultSet.getInt(INDEX_COMMAND_PERMISSIONS);

                // TODO: Change enum.values()[] to something more efficient
                commandList.add(new Command(commandId, commandName, CommandNumPermission.values()[commandPermissions]));
            }

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
        return commandList;
    }

    private void addCommandsList(@Nonnull List<Command> commandList) {
        Connection connection = DatabaseHandler.openConnection();

        PreparedStatement preparedStatement = preparedStatementHelper(connection, addCommandStatement);
        for (Command command : commandList) {
            try {
                preparedStatement.setString(1, command.getCommand());
                preparedStatement.setInt(2, command.getPermission().getCommandLevel());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Adding command failed " + e.getMessage());
            }

        }

        DatabaseHandler.closeConnection(connection);
    }

    public void addCommand(@Nonnull Command command) {
        Connection connection = DatabaseHandler.openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(addCommandStatement)) {
            preparedStatement.setString(1, command.getCommand());
            preparedStatement.setInt(2, command.getPermission().getCommandLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Adding command failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
    }

    private PreparedStatement preparedStatementHelper(Connection connection, String statement) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(statement);
        } catch (SQLException e) {
            System.out.println("Failed to prepare statement " + e.getMessage());
        }
        return preparedStatement;
    }
}
