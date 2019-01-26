package ozmar.database;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class WordCountTable {

    private static final String WORD_COUNT_TABLE = "wordCountTable";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_WORD = "word";
    private final static String COLUMN_COUNT = "count";
    private final static int INDEX_COLUMN_ID = 1;
    private final static int INDEX_COLUMN_WORD = 2;
    private final static int INDEX_COLUMN_COUNT = 3;

    private final static String CREATE_WORD_COUNT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + WORD_COUNT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_WORD + " TEXT, "
            + COLUMN_COUNT + " INTEGER, UNIQUE ("
            + COLUMN_WORD + "))";

    private final String updateCountStatement = "UPDATE "
            + WORD_COUNT_TABLE + " SET "
            + COLUMN_COUNT + " = "
            + COLUMN_COUNT + " + ? WHERE "
            + COLUMN_WORD + " = ?";

    private final String insertStatement = "INSERT OR IGNORE INTO "
            + WORD_COUNT_TABLE + " ("
            + COLUMN_WORD + ", "
            + COLUMN_COUNT + ") VALUES (?,  ? )";


    private Connection connection;

    public WordCountTable(Connection connection) {
        this.connection = connection;
    }

    public static String getWordCountTable() {
        return WORD_COUNT_TABLE;
    }

    public String getCREATE_WORD_COUNT_TABLE() {
        return CREATE_WORD_COUNT_TABLE;
    }

    public Map<String, Integer> queryWordCount() {
        String sql = "SELECT * FROM " + WORD_COUNT_TABLE;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            Map<String, Integer> map = new HashMap<>();
            while (resultSet.next()) {
                String word = resultSet.getString(INDEX_COLUMN_WORD);
                int count = resultSet.getInt(INDEX_COLUMN_COUNT);
                map.put(word, count);
            }

            return map;

        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            return null;
        }
    }

    // TODO: check resultset to lower firing trigger of sql statements

    public void updateOrInsert(@Nonnull Map<String, Integer> map) {
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
    }

    public void clearTable() {
        String sql = "DELETE FROM " + WORD_COUNT_TABLE;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }
    }

}
