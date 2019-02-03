package ozmar.database;

import com.github.twitch4j.helix.domain.User;
import ozmar.ChatUser;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatTable {

    private static final String CHAT_TABLE = "chatTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_MESSAGE_COUNT = "messageCount";
    private static final String COLUMN_POINTS = "points";

    private static final int RANDOM_POINTS_RANGE = 2;

    private static final String CREATE_CHAT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CHAT_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USER_ID + " INTEGER UNIQUE, " +
                    COLUMN_USER_NAME + " TEXT UNIQUE, " +
                    COLUMN_MESSAGE_COUNT + " INTEGER, " +
                    COLUMN_POINTS + " INTEGER)";

    private static final String getUserSql =
            "SELECT * FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";

    private static final String updatePointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " +
                    COLUMN_USER_NAME + " = ?";

    private static final String updateCountAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_MESSAGE_COUNT + " = " + COLUMN_MESSAGE_COUNT + " + ?, " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE "
                    + COLUMN_USER_ID + " = ?";

    private static final String updateNameAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_USER_NAME + " = ?, " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE "
                    + COLUMN_USER_ID + " = ?";

    private static final String insertUserSql =
            "INSERT OR IGNORE INTO " + CHAT_TABLE + " ( " +
                    COLUMN_USER_ID + ", " +
                    COLUMN_USER_NAME + ", " +
                    COLUMN_MESSAGE_COUNT + ", " +
                    COLUMN_POINTS + " ) " +
                    " VALUES (?, ?, ?, ?)";

    private static final String getMessageCountByUserIdSql =
            "SELECT " + COLUMN_MESSAGE_COUNT + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";

    private static final String getMessageCountByUserNameSql =
            "SELECT " + COLUMN_MESSAGE_COUNT + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_NAME + " = ?";

    private static final String getPointsByUserIdSql =
            "SELECT " + COLUMN_POINTS + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";

    private static final String getPointsByUserNameSql =
            "SELECT " + COLUMN_POINTS + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_NAME + " = ?";

    public ChatTable() {

    }

    /**
     * get the name of the table
     *
     * @return String
     */
    public String getCreateTableSql() {
        return CREATE_CHAT_TABLE;
    }

    /**
     * Checks if a username from the list already exists in the table
     * If it does, it updates the points and removes the name from the list
     *
     * @param list list of names to check in the table
     */
    public void checkIfNameExists(@Nonnull List<String> list) {
        Connection connection = DatabaseHandler.openConnectionCommitOff();
        PreparedStatement updatePointsStatement = DatabaseHandler.prepareStatement(connection, updatePointsSql);

        try {
            for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
                String userName = iterator.next();
                updatePointsStatement.setInt(1, RandomHelper.getRandomNumberNotZero(RANDOM_POINTS_RANGE));
                updatePointsStatement.setString(2, userName);
                if (updatePointsStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update points while checking: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(updatePointsStatement);
            DatabaseHandler.closeConnectionCommitOn(connection);
        }
    }

    /**
     * Uses the list to add the user to the table if they are not found
     * Change the userName if the same userId is found with a different userName and update their points
     * Or
     * Update the users points if they are in the table
     *
     * @param list list of Users to add/update in the table
     */
    public void addUsersToTable(List<User> list) {
        updateNameAndPointsFromUserId(list);
        insertUserList(list);
        if (!list.isEmpty()) {
            System.out.println("FAILED TO INSERT USERS: " + list.size());
        }
    }

    /**
     * Uses the list to check if any of the ids are contained in the table
     * If the id is in the table, update the userName and points, and then remove it from the list
     *
     * @param list list of users
     */
    private void updateNameAndPointsFromUserId(@Nonnull List<User> list) {
        Connection connection = DatabaseHandler.openConnectionCommitOff();
        PreparedStatement updatePointsStatement = DatabaseHandler.prepareStatement(connection, updateNameAndPointsSql);

        User user = null;
        try {
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext(); ) {
                user = iterator.next();
                updatePointsStatement.setString(1, user.getLogin());
                updatePointsStatement.setInt(2, RandomHelper.getRandomNumberNotZero(RANDOM_POINTS_RANGE));
                updatePointsStatement.setLong(3, Long.parseLong(user.getId()));
                if (updatePointsStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("UserName: " + user.getLogin());
            System.out.println("Failed to update points from userName: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(updatePointsStatement);
            DatabaseHandler.closeConnectionCommitOn(connection);
        }
    }

    /**
     * Inserts users from the list into the table
     *
     * @param list list of users to insert into table
     */
    public void insertUserList(List<User> list) {
        Connection connection = DatabaseHandler.openConnectionCommitOff();
        PreparedStatement insertStatement = DatabaseHandler.prepareStatement(connection, insertUserSql);

        try {
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext(); ) {
                User user = iterator.next();
                insertStatement.setLong(1, Long.parseLong(user.getId()));
                insertStatement.setString(2, user.getLogin());
                insertStatement.setInt(3, 0);
                insertStatement.setInt(4, RandomHelper.getRandomNumberNotZero(RANDOM_POINTS_RANGE));

                if (insertStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to insert new row: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(insertStatement);
            DatabaseHandler.closeConnectionCommitOn(connection);
        }
    }

    /**
     * Updates the users points by searching for the user with data from the map
     * If the user is not in the table, they are added
     *
     * @param map map of users and their ids
     */
    public void updatePoints(Map<Long, ChatUser> map) {
        Connection connection = DatabaseHandler.openConnectionCommitOff();
        PreparedStatement updateStatement = DatabaseHandler.prepareStatement(connection, updateCountAndPointsSql);
        PreparedStatement insertRowStatement = DatabaseHandler.prepareStatement(connection, insertUserSql);

        try {
            for (Map.Entry<Long, ChatUser> entry : map.entrySet()) {
                ChatUser user = entry.getValue();

                updateStatement.setInt(1, user.getMessageCount());
                updateStatement.setInt(2, user.getPoints());
                updateStatement.setLong(3, user.getUserId());

                // UserId was not found in the table, insert user as a new row
                if (updateStatement.executeUpdate() == 0) {
                    insertRowStatement.setLong(1, user.getUserId());
                    insertRowStatement.setString(2, user.getUserName());
                    insertRowStatement.setInt(3, user.getMessageCount());
                    insertRowStatement.setInt(4, user.getPoints());
                    insertRowStatement.executeUpdate();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update points or insert new row " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(updateStatement);
            DatabaseHandler.closeStatement(insertRowStatement);
            DatabaseHandler.closeConnectionCommitOn(connection);
        }
    }

    /**
     * Gets the message count for the specified user by userId
     *
     * @param userId userId to look for in the table
     * @return int
     */
    public int getMessageCountByUserId(long userId) {
        Connection connection = DatabaseHandler.openConnection();
        int count = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMessageCountByUserIdSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_MESSAGE_COUNT);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get message count by userName: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return count;
    }

    /**
     * Gets the message count for the specified user by userName
     *
     * @param userName userName to look for in the table
     * @return int
     */
    public int getMessageCountByUserName(String userName) {
        Connection connection = DatabaseHandler.openConnection();
        int count = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMessageCountByUserNameSql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_MESSAGE_COUNT);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get message count by userName: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return count;
    }

    /**
     * Gets the number of points for the specified user by userId
     *
     * @param userId userId to look for in the table
     * @return int
     */
    public int getPointsByUserId(long userId) {
        Connection connection = DatabaseHandler.openConnection();
        int points = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPointsByUserIdSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points = resultSet.getInt(COLUMN_POINTS);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get points by userId: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return points;
    }

    /**
     * Gets the number of points for the specified user by userName
     *
     * @param userName userName to look for in the table
     * @return int
     */
    public int getPointsByUserName(String userName) {
        Connection connection = DatabaseHandler.openConnection();
        int points = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPointsByUserNameSql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points = resultSet.getInt(COLUMN_POINTS);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get points by userName: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return points;
    }
}
