package ozmar.database;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCountTable {

    private static final String WORD_COUNT_TABLE = "wordCountTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_COUNT = "count";
    private static final int INDEX_COLUMN_ID = 1;
    private static final int INDEX_COLUMN_WORD = 2;
    private static final int INDEX_COLUMN_COUNT = 3;

    private static final String CREATE_WORD_COUNT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + WORD_COUNT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_WORD + " TEXT, "
            + COLUMN_COUNT + " INTEGER, UNIQUE ("
            + COLUMN_WORD + "))";

    private static final String updateCountStatement = "UPDATE "
            + WORD_COUNT_TABLE + " SET "
            + COLUMN_COUNT + " = "
            + COLUMN_COUNT + " + ? WHERE "
            + COLUMN_WORD + " = ?";

    private static final String insertStatement = "INSERT OR IGNORE INTO "
            + WORD_COUNT_TABLE + " ("
            + COLUMN_WORD + ", "
            + COLUMN_COUNT + ") VALUES (?,  ? )";


    public WordCountTable() {

    }

    public String getTableName() {
        return WORD_COUNT_TABLE;
    }

    public String getCreateTableSql() {
        return CREATE_WORD_COUNT_TABLE;
    }

    public Map<String, Integer> queryWordCount() {
        String sql = "SELECT * FROM " + WORD_COUNT_TABLE;
        Connection connection = DatabaseHandler.openConnection();
        Map<String, Integer> map = null;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            map = new HashMap<>();
            while (resultSet.next()) {
                String word = resultSet.getString(INDEX_COLUMN_WORD);
                int count = resultSet.getInt(INDEX_COLUMN_COUNT);
                map.put(word, count);
            }

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
        return map;
    }

    public Map<String, Integer> getTop10Words() {
        String sql = "SELECT * FROM " + WORD_COUNT_TABLE + " ORDER BY " + COLUMN_COUNT + " DESC LIMIT 10";
        Map<String, Integer> map = new LinkedHashMap<>();
        Connection connection = DatabaseHandler.openConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String word = resultSet.getString(INDEX_COLUMN_WORD);
                int count = resultSet.getInt(INDEX_COLUMN_COUNT);
                map.put(word, count);
            }

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
        return map;
    }


    public void updateOrInsert(@Nonnull Map<String, Integer> map) {
        Connection connection = DatabaseHandler.openConnection();
        PreparedStatement preparedStatementUpdate = preparedStatementHelper(connection, updateCountStatement);
        PreparedStatement preparedStatementInsert = preparedStatementHelper(connection, insertStatement);

        if (preparedStatementUpdate != null && preparedStatementInsert != null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int returnValue = 0;

                try {
                    preparedStatementUpdate.setInt(1, entry.getValue());
                    preparedStatementUpdate.setString(2, entry.getKey());
                    returnValue = preparedStatementUpdate.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Update failed" + e.getMessage());
                }

                // Only execute insert if update did not update a row (i.e. row word did not exist)
                if (returnValue == 0) {
                    try {
                        preparedStatementInsert.setString(1, entry.getKey());
                        preparedStatementInsert.setInt(2, entry.getValue());
                        preparedStatementInsert.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println("Insert failed " + e.getMessage());
                    }
                }
            }
        }

        DatabaseHandler.closeConnection(connection);
    }

    public void clearTable() {
        String sql = "DELETE FROM " + WORD_COUNT_TABLE;
        Connection connection = DatabaseHandler.openConnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
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
