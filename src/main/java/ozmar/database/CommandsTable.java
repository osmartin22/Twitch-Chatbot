package ozmar.database;

import javafx.util.Pair;
import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandsTable {

    private final static String COMMANDS_TABLE = "commandsTable";
    private final static String COLUMN_COMMAND_ID = "id";
    private final static String COLUMN_COMMAND_NAME = "commandName";
    private final static String COLUMN_COMMAND_PERMISSIONS = "commandPermissions";
    private final static int INDEX_COMMAND_ID = 1;
    private final static int INDEX_COMMAND_NAME = 2;
    private final static int INDEX_COMMAND_PERMISSIONS = 3;

    private final String CREATE_COMMANDS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + COMMANDS_TABLE + " ("
            + COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_COMMAND_NAME + " TEXT, "
            + COLUMN_COMMAND_PERMISSIONS + " TEXT)";

    private Connection connection;

    public CommandsTable(Connection connection) {
        this.connection = connection;
    }

    public String getCOMMANDS_TABLE() {
        return COMMANDS_TABLE;
    }

    public String getCREATE_COMMANDS_TABLE() {
        return CREATE_COMMANDS_TABLE;
    }

    public void initializeCommands() {
        List<Pair<String, String>> commandPairs = new ArrayList<>();
        commandPairs.add(new Pair<>("!dice", "1111111111"));        //  0
        commandPairs.add(new Pair<>("!hello", "1111111111"));
        commandPairs.add(new Pair<>("!count", "1111111111"));
        commandPairs.add(new Pair<>("!uptime", "1111111111"));
        commandPairs.add(new Pair<>("!calc", "1111111111"));        // 4
        commandPairs.add(new Pair<>("!followage", "1111111111"));
        commandPairs.add(new Pair<>("!hugme", "1111111111"));
        commandPairs.add(new Pair<>("!wordcount", "1111111111"));

        addCommandsList(commandPairs);
    }

    public List<Command> queryCommands() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + COMMANDS_TABLE)) {

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

    private void addCommandsList(List<Pair<String, String>> commandPairs) {
        String sqlStatement = "INSERT INTO " + COMMANDS_TABLE + " (" + COLUMN_COMMAND_NAME + ", "
                + COLUMN_COMMAND_PERMISSIONS + ")" + " VALUES( ";

        for (Pair<String, String> pair : commandPairs) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlStatement + "'" + pair.getKey() + "'" + ", " + pair.getValue() + ");");
            } catch (SQLException e) {
                System.out.println("Query failed " + e.getMessage());
            }
        }
    }

    private void addCommand(@Nonnull String command, @Nonnull String commandPermissions) {
        String sqlStatement = "INSERT INTO " + COMMANDS_TABLE + " (" + COLUMN_COMMAND_NAME + ", "
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
