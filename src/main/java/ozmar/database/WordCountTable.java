package ozmar.database;

import ozmar.database.interfaces.WordCountTableInterface;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCountTable implements WordCountTableInterface {

    private static final String WORD_COUNT_TABLE = "wordCountTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_COUNT = "count";

    private static final String CREATE_WORD_COUNT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + WORD_COUNT_TABLE + " ( " +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_WORD + " TEXT, " +
                    COLUMN_COUNT + " INTEGER, UNIQUE ( " +
                    COLUMN_WORD + " ))";

    private static final String updateCountSql =
            "UPDATE " + WORD_COUNT_TABLE +
                    " SET " +
                    COLUMN_COUNT + " = " + COLUMN_COUNT + " + ? " +
                    " WHERE " +
                    COLUMN_WORD + " = ?";

    private static final String insertWordAndCountSql =
            "INSERT OR IGNORE INTO " + WORD_COUNT_TABLE + " ( " +
                    COLUMN_WORD + ", " +
                    COLUMN_COUNT + ") " +
                    " VALUES (?,  ? )";
    private static final String getSpecificWordCountSql =
            "SELECT " + COLUMN_COUNT +
                    " FROM " + WORD_COUNT_TABLE +
                    " WHERE " + COLUMN_WORD + " = ?";


    public WordCountTable() {

    }

    @Nonnull
    @Override
    public String getCreateTableSql() {
        return CREATE_WORD_COUNT_TABLE;
    }

    /**
     * Gets all the data in the table
     *
     * @return Map of Strings and Integers
     */
    @Nullable
    @Override
    public Map<String, Integer> queryWordCount() {
        String sql = "SELECT * FROM " + WORD_COUNT_TABLE;
        Connection connection = DatabaseHandler.openConnection();
        Map<String, Integer> map = null;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            map = new HashMap<>();
            while (resultSet.next()) {
                String word = resultSet.getString(COLUMN_WORD);
                int count = resultSet.getInt(COLUMN_COUNT);
                map.put(word, count);
            }
        } catch (SQLException e) {
            System.out.println("Failed getting wordCount data: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return map;
    }

    /**
     * Gets the 10 most used words from the table
     *
     * @return Map of Strings and Integers
     */
    @Nonnull
    @Override
    public Map<String, Integer> getTop10Words() {
        String sql = "SELECT " + COLUMN_WORD + ", " + COLUMN_COUNT +
                " FROM " + WORD_COUNT_TABLE + " ORDER BY " + COLUMN_COUNT + " DESC LIMIT 10";
        Map<String, Integer> map = new LinkedHashMap<>();
        Connection connection = DatabaseHandler.openConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String word = resultSet.getString(COLUMN_WORD);
                int count = resultSet.getInt(COLUMN_COUNT);
                map.put(word, count);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get top 10 words: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return map;
    }

    @Override
    public int getSpecificWordCount(@Nonnull String word) {
        Connection connection = DatabaseHandler.openConnection();
        int count = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSpecificWordCountSql)) {
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_COUNT);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get a word count: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return count;
    }

    /**
     * Uses the map to update the count for a word if the word is in the table
     * If it is not, the word and count are inserted as a new row
     *
     * @param map map of words and count
     */
    @Override
    public void updateOrInsert(@Nonnull Map<String, Integer> map) {
        Connection connection = DatabaseHandler.openConnectionCommitOff();
        PreparedStatement updatePreparedStatement = DatabaseHandler.prepareStatement(connection, updateCountSql);
        PreparedStatement insertPreparedStatement = DatabaseHandler.prepareStatement(connection, insertWordAndCountSql);

        try {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                updatePreparedStatement.setInt(1, entry.getValue());
                updatePreparedStatement.setString(2, entry.getKey());

                // Only execute insert if update did not update a row (i.e. row word did not exist)
                if (updatePreparedStatement.executeUpdate() == 0) {
                    insertPreparedStatement.setString(1, entry.getKey());
                    insertPreparedStatement.setInt(2, entry.getValue());
                    insertPreparedStatement.executeUpdate();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Update or insert failed: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(updatePreparedStatement);
            DatabaseHandler.closeStatement(insertPreparedStatement);
            DatabaseHandler.closeConnectionCommitOn(connection);
        }
    }

    /**
     * Clears the entire table
     */
    @Override
    public void clearTable() {
        String sql = "DELETE FROM " + WORD_COUNT_TABLE;
        Connection connection = DatabaseHandler.openConnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to clear the table: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }
    }
}
