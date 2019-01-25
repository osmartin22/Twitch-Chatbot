package ozmar.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: do PreparedStatements to prevent SQL injection
public class DatabaseHelper {

    private static final String DATABASE_NAME = "TwitchBot.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Databases\\" + DATABASE_NAME;

    private Connection connection;

    public CommandsTable commandsTable;
    public WordCountTable wordCountTable;

    public DatabaseHelper() {

    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            commandsTable = new CommandsTable(connection);
            wordCountTable = new WordCountTable(connection);
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

            statement.execute(wordCountTable.getCREATE_WORD_COUNT_TABLE());
            statement.execute(commandsTable.getCREATE_COMMANDS_TABLE());
            if (commandsTable.queryCommands().isEmpty()) {
                commandsTable.initializeCommands();
            }

        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }

    }

}
