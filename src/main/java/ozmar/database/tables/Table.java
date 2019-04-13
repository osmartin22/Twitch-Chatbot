package ozmar.database.tables;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Table {

    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\TwitchBotFiles\\Databases\\TwitchBot.db");

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
        }

        return connection;
    }

    public Connection openConnectionCommitOff() {
        Connection connection = openConnection();
        turnOffAutoCommit(connection);

        return connection;
    }

    public void closeConnectionCommitOn(Connection connection) {
        turnOnAutoCommit(connection);
        closeConnection(connection);
    }

    public void closeConnection(@Nonnull Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    public void turnOnAutoCommit(@Nonnull Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Failed to turn on auto commit " + e.getMessage());
        }
    }

    public void turnOffAutoCommit(@Nonnull Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Failed to turn off auto commit " + e.getMessage());
        }
    }

    public void rollBack(@Nonnull Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Failed to roll back " + e.getMessage());
        }
    }

    public void createTable(@Nonnull String createSql) {
        Connection connection = openConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(createSql);
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
