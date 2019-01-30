package ozmar.database;

import ozmar.ChatUser;
import ozmar.Command;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {

    private static final String DATABASE_NAME = "TwitchBot.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Databases\\" + DATABASE_NAME;

    private CommandsTable commandsTable;
    private WordCountTable wordCountTable;
    private ChatTable chatTable;

    public DatabaseHandler() {
        commandsTable = new CommandsTable();
        wordCountTable = new WordCountTable();
        chatTable = new ChatTable();

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

    public static void closeConnection(@Nonnull Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    public static void turnOffAutoCommit(@Nonnull Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Failed to turn off auto commit " + e.getMessage());
        }
    }

    public static void turnOnAutoCommit(@Nonnull Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Failed to turn on auto commit " + e.getMessage());
        }
    }

    public static void rollBack(@Nonnull Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Failed to roll back " + e.getMessage());
        }
    }

    public static PreparedStatement prepareStatement(@Nonnull Connection connection, @Nonnull String statement) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(statement);
        } catch (SQLException e) {
            System.out.println("Failed to prepare statement " + e.getMessage());
        }
        return preparedStatement;
    }

    private void initializeDb() {
        Connection connection = openConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(commandsTable.getCreateTableSql());
            statement.execute(wordCountTable.getCreateTableSql());
            statement.execute(chatTable.getCreateTableSql());

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
        commandsTable.initializeCommands();
    }

    public List<Command> getCommands() {
        return commandsTable.queryCommands();
    }

    public void insertCommand(@Nonnull Command command) {
        commandsTable.insertCommand(command);
    }


    // WordCountTable
    public Map<String, Integer> getWordCount() {
        return wordCountTable.queryWordCount();
    }

    public Map<String, Integer> getTop10Words() {
        return wordCountTable.getTop10Words();
    }

    public void updateOrInsertWordCount(Map<String, Integer> map) {
        wordCountTable.updateOrInsert(map);
    }

    public void clearWordCount() {
        wordCountTable.clearTable();
    }

    public void insertChatUserList(List<ChatUser> list) {
        chatTable.insertUserNames(list);
    }
}