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


    private WordCountTable() {

    }

    public static String getTableName() {
        return WORD_COUNT_TABLE;
    }

    public static String getCreateTableSql() {
        return CREATE_WORD_COUNT_TABLE;
    }

    public static Map<String, Integer> queryWordCount() {
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

    public static Map<String, Integer> getTop10Words() {
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


    public static void updateOrInsert(@Nonnull Map<String, Integer> map) {
        Connection connection = DatabaseHandler.openConnection();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int returnValue = 0;

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateCountStatement)) {
                preparedStatement.setInt(1, entry.getValue());
                preparedStatement.setString(2, entry.getKey());
                returnValue = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Update failed" + e.getMessage());
            }

            // Only execute insert if update did not update a row (i.e. row word did not exist)
            if (returnValue == 0) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
                    preparedStatement.setString(1, entry.getKey());
                    preparedStatement.setInt(2, entry.getValue());
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("Insert failed " + e.getMessage());
                }
            }
        }

        DatabaseHandler.closeConnection(connection);
    }

    public static void clearTable() {
        String sql = "DELETE FROM " + WORD_COUNT_TABLE;
        Connection connection = DatabaseHandler.openConnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }

        DatabaseHandler.closeConnection(connection);
    }

}
