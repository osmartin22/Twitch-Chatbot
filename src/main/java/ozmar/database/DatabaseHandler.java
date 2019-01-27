package ozmar.database;

import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {

    private static final String DATABASE_NAME = "TwitchBot.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Databases\\" + DATABASE_NAME;

    public DatabaseHandler() {
        initializeDb();
    }

    public static Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    private void initializeDb() {
        Connection connection = openConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(WordCountTable.getCreateTableSql());
            statement.execute(CommandsTable.getCreateTableSql());

            if (getCommands().isEmpty()) {
                initializeCommandsTable();
            }

        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }

        closeConnection(connection);
    }


    // CommandsTable
    private void initializeCommandsTable() {
        CommandsTable.initializeCommands();
    }

    public List<Command> getCommands() {
        return CommandsTable.queryCommands();
    }

    public void addCommand(@Nonnull Command command) {
        CommandsTable.addCommand(command);
    }


    // WordCountTable
    public Map<String, Integer> getWordCount() {
        return WordCountTable.queryWordCount();
    }

    public Map<String, Integer> getTop10Words() {
        return WordCountTable.getTop10Words();
    }

    public void updateOrInsertWordCount(Map<String, Integer> map) {
        WordCountTable.updateOrInsert(map);
    }

    public void clearWordCount() {
        WordCountTable.clearTable();
    }

}
