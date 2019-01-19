package ozmar.database;

import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "TwitchBot.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Databases\\" + DATABASE_NAME;

    private static final String TABLE_COMMANDS = "commandsTable";
    private static final String COLUMN_COMMAND_ID = "id";
    private static final String COLUMN_COMMAND_NAME = "commandName";
    private static final String COLUMN_COMMAND_PERMISSIONS = "commandPermissions";
    private static final int INDEX_COMMAND_ID = 1;
    private static final int INDEX_COMMAND_NAME = 2;
    private static final int INDEX_COMMAND_PERMISSIONS = 3;

    private static final String CREATE_TABLE_COMMANDS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_COMMANDS + " ("
            + COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_COMMAND_NAME + " TEXT, "
            + COLUMN_COMMAND_PERMISSIONS + " TEXT)";

    private Connection connection;

    public DatabaseHelper() {
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    public void initializeDb() {
        try (Statement statement = connection.createStatement()) {

            statement.execute(CREATE_TABLE_COMMANDS);
            if (queryCommands().isEmpty()) {
                initializeCommands();
            }

        } catch (SQLException e) {
            System.out.println("Failed to create table " + e.getMessage());
        }

    }

    // Should only run when the Table for commands is empty
    private void initializeCommands() {
        addCommand("!dice", "1111111111");
        addCommand("!hello", "1111111111");
        addCommand("!count", "1111111111");
        addCommand("!uptime", "1111111111");
    }

    public List<Command> queryCommands() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_COMMANDS)) {

            List<Command> commandsList = new ArrayList<>();
            while (resultSet.next()) {
                int commandId = resultSet.getInt(INDEX_COMMAND_ID);
                String commandName = resultSet.getString(INDEX_COMMAND_NAME);
                String commandPermissions = resultSet.getString(INDEX_COMMAND_PERMISSIONS);

                commandsList.add(new Command(commandId, commandName, commandPermissions));
            }

            return commandsList;

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            return null;
        }
    }

    public void addCommand(@Nonnull Command command) {
        addCommand(command.getCommand(), command.convertPermissionsToString());
    }

    private void addCommand(@Nonnull String command, @Nonnull String commandPermissions) {
        String sqlStatement = "INSERT INTO " + TABLE_COMMANDS + " (" + COLUMN_COMMAND_NAME + ", "
                + COLUMN_COMMAND_PERMISSIONS + ")" + " VALUES( ";

        try (Statement statement = connection.createStatement()) {

            statement.execute(sqlStatement + "'" + command + "'" + ", " + commandPermissions + ");");

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }
    }

    public void storeCommands(List<Command> commandsList) {

    }
}
