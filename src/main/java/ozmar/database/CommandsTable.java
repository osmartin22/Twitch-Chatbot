package ozmar.database;

import javafx.util.Pair;
import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandsTable {

    private static final String COMMANDS_TABLE = "commandsTable";
    private static final String COLUMN_COMMAND_ID = "id";
    private static final String COLUMN_COMMAND_NAME = "commandName";
    private static final String COLUMN_COMMAND_PERMISSIONS = "commandPermissions";
    private static final int INDEX_COMMAND_ID = 1;
    private static final int INDEX_COMMAND_NAME = 2;
    private static final int INDEX_COMMAND_PERMISSIONS = 3;

    private static final String CREATE_COMMANDS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + COMMANDS_TABLE + " ("
            + COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_COMMAND_NAME + " TEXT, "
            + COLUMN_COMMAND_PERMISSIONS + " TEXT)";

    private final static String addCommandStatement = "INSERT INTO " + COMMANDS_TABLE + " (" + COLUMN_COMMAND_NAME + ", "
            + COLUMN_COMMAND_PERMISSIONS + ")" + " VALUES(?, ?)";

    private CommandsTable() {

    }

    public static String getTableName() {
        return COMMANDS_TABLE;
    }

    public static String getCreateTableSql() {
        return CREATE_COMMANDS_TABLE;
    }


    public static void initializeCommands() {
        List<Pair<String, String>> commandPairs = new ArrayList<>();
        commandPairs.add(new Pair<>("!dice", "1111111111"));        //  0
        commandPairs.add(new Pair<>("!hello", "1111111111"));
        commandPairs.add(new Pair<>("!count", "1111111111"));
        commandPairs.add(new Pair<>("!uptime", "1111111111"));
        commandPairs.add(new Pair<>("!calc", "1111111111"));        // 4
        commandPairs.add(new Pair<>("!followage", "1111111111"));
        commandPairs.add(new Pair<>("!wordcount", "1111111111"));
        commandPairs.add(new Pair<>("!clearwordcount", "1111111111"));  // TEMP, CHANGE PERMISSIONS
        commandPairs.add(new Pair<>("!31", "1111111111"));
        commandPairs.add(new Pair<>("!hugme", "1111111111"));       // 9

        addCommandsList(commandPairs);
    }

    public static List<Command> queryCommands() {
        Connection connection = DatabaseHandler.openConnection();

        List<Command> commandList = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + COMMANDS_TABLE)) {

            commandList = new ArrayList<>();
            while (resultSet.next()) {
                int commandId = resultSet.getInt(INDEX_COMMAND_ID);
                String commandName = resultSet.getString(INDEX_COMMAND_NAME);
                String commandPermissions = resultSet.getString(INDEX_COMMAND_PERMISSIONS);

                commandList.add(new Command(commandId, commandName, commandPermissions));
            }

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
        return commandList;
    }

    private static void addCommandsList(List<Pair<String, String>> commandPairs) {
        Connection connection = DatabaseHandler.openConnection();

        for (Pair<String, String> pair : commandPairs) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(addCommandStatement)) {
                preparedStatement.setString(1, pair.getKey());
                preparedStatement.setString(2, pair.getValue());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Adding command failed " + e.getMessage());
            }
        }

        DatabaseHandler.closeConnection(connection);
    }

    public static void addCommand(@Nonnull Command command) {
        addCommand(command.getCommand(), command.convertPermissionsToString());
    }

    private static void addCommand(@Nonnull String command, @Nonnull String commandPermissions) {
        Connection connection = DatabaseHandler.openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(addCommandStatement)) {
            preparedStatement.setString(1, command);
            preparedStatement.setString(2, commandPermissions);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Adding command failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
    }
}
