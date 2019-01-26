package ozmar.database;

import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseHandler {

    private static final String DATABASE_NAME = "TwitchBot.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Databases\\" + DATABASE_NAME;

    private Connection connection;

    public CommandsTable commandsTable;
    public WordCountTable wordCountTable;

    public DatabaseHandler() {

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

            if (getCommands().isEmpty()) {
                initializeCommandsTable();
            }

        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }
    }

    private void initializeCommandsTable() {
        commandsTable.initializeCommands();
    }

    public List<Command> getCommands() {
        return commandsTable.queryCommands();
    }

    public void addCommand(@Nonnull Command command) {
        commandsTable.addCommand(command);
    }


}
