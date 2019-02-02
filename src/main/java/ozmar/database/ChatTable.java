package ozmar.database;

import com.github.twitch4j.helix.domain.User;
import ozmar.ChatUser;
import ozmar.utils.RandomHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private static final int RANDOM_POINTS_INTERVAL = 5;

    private static final String CREATE_CHAT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CHAT_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USER_ID + " INTEGER UNIQUE, " +
                    COLUMN_USER_NAME + " TEXT UNIQUE, " +
                    COLUMN_MESSAGE_COUNT + " INTEGER, " +
                    COLUMN_POINTS + " INTEGER)";

    private static final String getUserIdSql =
            "SELECT * FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";

    private static final String updateUserIdCountAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_USER_ID + " = ?, " +
                    COLUMN_MESSAGE_COUNT + " = " + COLUMN_MESSAGE_COUNT + " + ?, " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " +
                    COLUMN_USER_NAME + " = ?";

    private static final String updateUserPointsUseNameSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? " +
                    " WHERE " +
                    COLUMN_USER_NAME + " = ?";

    private static final String updateUserIdSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_USER_ID + " = ? " +
                    "WHERE " +
                    COLUMN_USER_NAME + " = ?";

    private static final String insertNameCountAndPointsSql =
            "INSERT OR IGNORE INTO " + CHAT_TABLE + " ( " +
                    COLUMN_USER_NAME + ", " +
                    COLUMN_MESSAGE_COUNT + ", " +
                    COLUMN_POINTS + " ) " +
                    " VALUES (?, ?, ?)";

    private static final String deleteIfUserIdSql =
            "DELETE FROM " + CHAT_TABLE +
                    " WHERE "
                    + COLUMN_USER_ID + " = ?";

    private static final String updateCountAndPointsSql =
            "UPDATE " + CHAT_TABLE +
                    " SET " +
                    COLUMN_MESSAGE_COUNT + " = " + COLUMN_MESSAGE_COUNT + " + ?, " +
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

    private static final String getMessageCountSql =
            "SELECT " + COLUMN_MESSAGE_COUNT + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";

    private static final String getPointsSql =
            "SELECT " + COLUMN_POINTS + " FROM " + CHAT_TABLE +
                    " WHERE " +
                    COLUMN_USER_ID + " = ?";


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
     * Inserts only unique(new) userNames to the table from the list along with random number of points
     * For names already in the table and in the list, a random number of points is added to them
     * userNames already in the table are removed from the list after getting points
     *
     * @param chatUserList list of userNames to store in the database
     */
    public void insertUserNames(List<String> chatUserList) {
        Connection connection = DatabaseHandler.openConnection();
        DatabaseHandler.turnOffAutoCommit(connection);
        PreparedStatement insertUserStatement = DatabaseHandler.prepareStatement(connection, insertNameCountAndPointsSql);
        PreparedStatement updatePointsStatement = DatabaseHandler.prepareStatement(connection, updateUserPointsUseNameSql);

        try {
            for (Iterator<String> iterator = chatUserList.iterator(); iterator.hasNext(); ) {
                String userName = iterator.next();
                insertUserStatement.setString(1, userName);
                insertUserStatement.setInt(2, 0);
                insertUserStatement.setInt(3, RandomHelper.getRandomNumberNotZero(RANDOM_POINTS_INTERVAL));

                // Update userNames already in the table with random number of points and remove them from
                // the list so that at the end of the loop, the list contains only newly inserted userNames
                // Remove userNames already in the table from the list to get a list of userNames that
                if (insertUserStatement.executeUpdate() == 0) {
                    updatePointsStatement.setInt(1, RandomHelper.getRandomNumberNotZero(RANDOM_POINTS_INTERVAL));
                    updatePointsStatement.setString(2, userName);
                    updatePointsStatement.executeUpdate();
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to insert or delete row: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        } finally {
            DatabaseHandler.closeStatement(insertUserStatement);
            DatabaseHandler.closeStatement(updatePointsStatement);
            DatabaseHandler.turnOnAutoCommit(connection);
            DatabaseHandler.closeConnection(connection);
        }
    }


    /**
     * Takes a list of Users and
     * 1) Figures out which ids from the list are already in the table, if the userId is are already in the table
     * it means the userName has changed and must be updated. The row info is stored in a ChatUser list
     * and the row is then deleted
     * NOTE: the passed in User list user is also removed from the list when an id already exists
     * 2) If the ChatUser list is not empty, it combines the row in the table with the desired userName from
     * th ChatUSer list
     * 3) It then updates the userId with the desired name from the User list
     * <p>
     * If any of the methods fail, then the database is roll backed and the methods that follow are skipped
     * <p>
     * It is ONLY meant to be used after using the Helix API getUsers() and using the returned data
     * to update the table
     *
     * @param userList data containing info to store
     */
    public void addChatDataToTable(List<User> userList) {
        Connection connection = DatabaseHandler.openConnection();
        DatabaseHandler.turnOffAutoCommit(connection);

        List<ChatUser> chatUserList = getIdCollisionRows(userList, connection);

        if (chatUserList == null) {
            return;
        }

        boolean returnValue = true;
        if (!chatUserList.isEmpty()) {
            returnValue = updateUserIdAndRow(chatUserList, connection);
        }

        if (returnValue) {
            updateIdWithUserName(userList, connection);
        }

        DatabaseHandler.turnOnAutoCommit(connection);
        DatabaseHandler.closeConnection(connection);
    }


    /**
     * Takes a list of Users and checks if any of the user's ids are already in the table.
     * It creates a ChatUser list containing all the data from the rows that have the same id as a User
     * It then deletes the row containing the data which is safely stored in the ChatUser list
     *
     * @param userList   list of user info (should be the list from twitch api to get chatters)
     * @param connection connection to database
     * @return ChatUser
     */
    private List<ChatUser> getIdCollisionRows(List<User> userList, Connection connection) {
        PreparedStatement getUserIdStatement = DatabaseHandler.prepareStatement(connection, ChatTable.getUserIdSql);
        PreparedStatement deleteRowStatement = DatabaseHandler.prepareStatement(connection, deleteIfUserIdSql);

        List<ChatUser> chatUserList = new ArrayList<>();
        try {
            for (Iterator<User> iterator = userList.iterator(); iterator.hasNext(); ) {
                User user = iterator.next();
                long currentUserId = Long.parseLong(user.getId());
                getUserIdStatement.setLong(1, currentUserId);
                ResultSet resultSet = getUserIdStatement.executeQuery();

                if (resultSet.next()) {
                    do {
                        int id = resultSet.getInt(COLUMN_ID);
                        long userId = resultSet.getLong(COLUMN_USER_ID);
                        int messageCount = resultSet.getInt(COLUMN_MESSAGE_COUNT);
                        int points = resultSet.getInt(COLUMN_POINTS);

                        chatUserList.add(new ChatUser(id, userId, user.getLogin(), messageCount, points));
                    } while (resultSet.next());

                    deleteRowStatement.setLong(1, currentUserId);
                    deleteRowStatement.executeUpdate();
                    iterator.remove();
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to query userIds " + e.getMessage());
            DatabaseHandler.rollBack(connection);
            return null;
        } finally {
            DatabaseHandler.closeStatement(getUserIdStatement);
            DatabaseHandler.closeStatement(deleteRowStatement);
        }

        return chatUserList;
    }


    /**
     * Updates the row with the desired userName using the ChatUser with the same name by adding the
     * column values which are ints
     *
     * @param chatUserList list of ChatUsers containing info to store
     * @param connection   connection to database
     */
    private boolean updateUserIdAndRow(List<ChatUser> chatUserList, Connection connection) {
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, updateUserIdCountAndPointsSql);
        try {
            for (ChatUser chatUser : chatUserList) {
                preparedStatement.setLong(1, chatUser.getUserId());
                preparedStatement.setInt(2, chatUser.getMessageCount());
                preparedStatement.setInt(3, chatUser.getPoints());
                preparedStatement.setString(4, chatUser.getUserName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update user1: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
            return false;
        } finally {
            DatabaseHandler.closeStatement(preparedStatement);
        }

        return true;
    }


    /**
     * Updates rows with desired userName with the userId from User with the same userName
     *
     * @param userList   list of Users containing userIds to store
     * @param connection connection to database
     */
    private boolean updateIdWithUserName(List<User> userList, Connection connection) {
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, updateUserIdSql);

        try {
            for (User user : userList) {
                preparedStatement.setLong(1, Long.parseLong(user.getId()));
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Failed to update user2: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
            return false;
        } finally {
            DatabaseHandler.closeStatement(preparedStatement);
        }

        return true;
    }

    public void updatePoints(Map<Long, ChatUser> chatUserMap) {
        Connection connection = DatabaseHandler.openConnection();
        DatabaseHandler.turnOffAutoCommit(connection);
        PreparedStatement updateStatement = DatabaseHandler.prepareStatement(connection, updateCountAndPointsSql);
        PreparedStatement insertRowStatement = DatabaseHandler.prepareStatement(connection, insertUserSql);
        try {
            for (Map.Entry<Long, ChatUser> entry : chatUserMap.entrySet()) {
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
            System.out.println("HI FINALLY");
            DatabaseHandler.closeStatement(updateStatement);
            DatabaseHandler.closeStatement(insertRowStatement);
            DatabaseHandler.turnOnAutoCommit(connection);
            DatabaseHandler.closeConnection(connection);
        }

        System.out.println("HI DONE");
    }

    public Integer getMessageCount(long userId) {
        Connection connection = DatabaseHandler.openConnection();
        Integer count = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMessageCountSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(COLUMN_MESSAGE_COUNT);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get message count: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return count;
    }

    public Integer getPoints(long userId) {
        Connection connection = DatabaseHandler.openConnection();
        Integer points = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPointsSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points = resultSet.getInt(COLUMN_POINTS);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get points: " + e.getMessage());
        } finally {
            DatabaseHandler.closeConnection(connection);
        }

        return points;
    }
}
