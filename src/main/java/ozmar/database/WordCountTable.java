package ozmar.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private final String CREATE_WORD_COUNT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + WORD_COUNT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_WORD + " TEXT, "
            + COLUMN_COUNT + " INTEGER UNIQUE ("
            + COLUMN_WORD + "));";


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
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + WORD_COUNT_TABLE)) {

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
    public void updateOrInsert(Map<String, Integer> map) {

        try (Statement statement = connection.createStatement()) {

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String sqlUpdate = "UPDATE " + WORD_COUNT_TABLE + " SET " + COLUMN_COUNT + " = "
                        + COLUMN_COUNT + entry.getValue() + " WHERE "
                        + COLUMN_WORD + " = " + "'" + entry.getKey() + "'";

                String sqlInsert = "INSERT OR IGNORE INTO " + WORD_COUNT_TABLE
                        + " (" + COLUMN_WORD + ", " + COLUMN_COUNT + ") VALUES ("
                        + "'" + entry.getKey() + "'" + ", " + entry.getValue() + ")";

                statement.execute(sqlUpdate);
                statement.execute(sqlInsert);
            }
        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }
    }

    public void clearTable() {
        String sql = "DELETE FROM " + WORD_COUNT_TABLE;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create connection " + e.getMessage());
        }
    }

}
