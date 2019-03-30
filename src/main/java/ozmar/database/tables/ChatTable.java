package ozmar.database.tables;

import ozmar.database.tables.interfaces.ChatTableInterface;
import ozmar.user.ChatUser;
import ozmar.utils.RandomHelper;
import twitch4j_packages.helix.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatTable extends Table implements ChatTableInterface {

    private static final String CHAT_TABLE = "chatTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_MESSAGE_COUNT = "messageCount";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_PARTNER = "partner";

    private static final int RANDOM_POINTS_RANGE_START = 1;
    private static final int RANDOM_POINTS_RANGE_END = 5;

    private static final String CREATE_CHAT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CHAT_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USER_ID + " INTEGER UNIQUE, " +
                    COLUMN_USER_NAME + " TEXT UNIQUE, " +
                    COLUMN_MESSAGE_COUNT + " INTEGER, " +
                    COLUMN_POINTS + " INTEGER, " +
                    COLUMN_PARTNER + " TEXT)";

    private static final String getUserIdSql =
            "SELECT " + COLUMN_USER_ID + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_NAME + " = ?";

    private static final String updatePointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " + COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " + COLUMN_USER_NAME + " = ?";

    private static final String updateCountAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_MESSAGE_COUNT + " = " + COLUMN_MESSAGE_COUNT + " + ?, " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String updateNameAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_USER_NAME + " = ?, " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String insertUserSql =
            "INSERT OR IGNORE INTO " + CHAT_TABLE + " ( " +
                    COLUMN_USER_ID + ", " +
                    COLUMN_USER_NAME + ", " +
                    COLUMN_MESSAGE_COUNT + ", " +
                    COLUMN_POINTS + " ) " +
                    " VALUES (?, ?, ?, ?)";

    private static final String getMessageCountByUserIdSql =
            "SELECT " + COLUMN_MESSAGE_COUNT + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String getMessageCountByUserNameSql =
            "SELECT " + COLUMN_MESSAGE_COUNT + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_NAME + " = ?";

    private static final String getPointsByUserIdSql =
            "SELECT " + COLUMN_POINTS + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String getPointsByUserNameSql =
            "SELECT " + COLUMN_POINTS + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_NAME + " = ?";

    private static final String updatePartnerSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " + COLUMN_PARTNER + " = ? " +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String getPartnerSql =
            "SELECT " + COLUMN_PARTNER + " FROM " + CHAT_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    public ChatTable() {
        createTable(CREATE_CHAT_TABLE);
    }


    /**
     * @param userName name of the desired user to get userId
     * @return long
     */
    @Override
    public long getUserId(@Nonnull String userName) {
        long userId = -1;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserIdSql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt(COLUMN_USER_ID);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get user id: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return userId;
    }

    /**
     * Checks if a username from the list already exists in the table
     * If it does, it updates the points and removes the name from the list
     *
     * @param list list of names to check in the table
     */
    @Override
    public void checkIfNameExists(@Nonnull List<String> list) {
        Connection connection = openConnectionCommitOff();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePointsSql)) {
            for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
                String userName = iterator.next();
                preparedStatement.setInt(1,
                        RandomHelper.getRandNumInRange(RANDOM_POINTS_RANGE_START, RANDOM_POINTS_RANGE_END));
                preparedStatement.setString(2, userName);
                if (preparedStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update points while checking: " + e.getMessage());
            rollBack(connection);
        } finally {
            closeConnectionCommitOn(connection);
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
    @Override
    public void addUserList(@Nonnull List<User> list) {
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
        Connection connection = openConnectionCommitOff();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameAndPointsSql)) {
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext(); ) {
                User user = iterator.next();
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setInt(2,
                        RandomHelper.getRandNumInRange(RANDOM_POINTS_RANGE_START, RANDOM_POINTS_RANGE_END));
                preparedStatement.setLong(3, user.getId());
                if (preparedStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update points from userName: " + e.getMessage());
            rollBack(connection);
        } finally {
            closeConnectionCommitOn(connection);
        }
    }

    /**
     * Inserts users from the list into the table
     *
     * @param list list of users to insert into table
     */
    @Override
    public void insertUserList(@Nonnull List<User> list) {
        Connection connection = openConnectionCommitOff();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSql)) {
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext(); ) {
                User user = iterator.next();
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4,
                        RandomHelper.getRandNumInRange(RANDOM_POINTS_RANGE_START, RANDOM_POINTS_RANGE_END));

                if (preparedStatement.executeUpdate() != 0) {
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to insert new row: " + e.getMessage());
            rollBack(connection);
        } finally {
            closeConnectionCommitOn(connection);
        }
    }

    /**
     * Updates the users points by searching for the user with data from the map
     * If the user is not in the table, they are added
     *
     * @param map map of users and their ids
     */
    @Override
    public void updatePoints(@Nonnull Map<Long, ChatUser> map) {
        Connection connection = openConnectionCommitOff();

        try (PreparedStatement updateStatement = connection.prepareStatement(updateCountAndPointsSql);
             PreparedStatement insertRowStatement = connection.prepareStatement(insertUserSql)) {
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
            rollBack(connection);
        } finally {
            closeConnectionCommitOn(connection);
        }
    }

    /**
     * Gets the message count for the specified user by userId
     *
     * @param userId userId to look for in the table
     * @return int
     */
    @Override
    public int getMessageCount(long userId) {
        int count = -1;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMessageCountByUserIdSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_MESSAGE_COUNT);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get message count by userName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return count;
    }

    /**
     * Gets the message count for the specified user by userName
     *
     * @param userName userName to look for in the table
     * @return int
     */
    @Override
    public int getMessageCount(@Nonnull String userName) {
        int count = -1;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMessageCountByUserNameSql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_MESSAGE_COUNT);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get message count by userName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return count;
    }

    /**
     * Gets the number of points for the specified user by userId
     *
     * @param userId userId to look for in the table
     * @return int
     */
    @Override
    public int getPoints(long userId) {
        int points = -1;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPointsByUserIdSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points = resultSet.getInt(COLUMN_POINTS);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get points by userId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return points;
    }

    /**
     * Gets the number of points for the specified user by userName
     *
     * @param userName userName to look for in the table
     * @return int
     */
    @Override
    public int getPoints(@Nonnull String userName) {
        int points = -1;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPointsByUserNameSql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points = resultSet.getInt(COLUMN_POINTS);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get points by userName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return points;
    }

    /**
     * @param userId id of the desired user
     * @return String
     */
    @Nullable
    @Override
    public String getPartner(long userId) {
        String partner = null;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPartnerSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                partner = resultSet.getString(COLUMN_PARTNER);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get points by userId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return partner;
    }

    /**
     * @param userId     id of the desired user to update
     * @param newPartner name to update column with
     */
    @Override
    public void updatePartner(long userId, @Nonnull String newPartner) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePartnerSql)) {
            preparedStatement.setString(1, newPartner);
            preparedStatement.setLong(2, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to update partner: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
