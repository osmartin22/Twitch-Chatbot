package ozmar.database;

import com.github.twitch4j.helix.domain.User;
import ozmar.ChatUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatTable {

    private static final String CHAT_TABLE = "chatTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_MESSAGE_COUNT = "messageCount";
    private static final String COLUMN_POINTS = "points";

    private static final int INDEX_COLUMN_ID = 1;
    private static final int INDEX_COLUMN_USER_ID = 2;
    private static final int INDEX_COLUMN_USER_NAME = 3;
    private static final int INDEX_COLUMN_MESSAGE_COUNT = 4;
    private static final int INDEX_COLUMN_POINTS = 5;


    private static final String CREATE_CHAT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + CHAT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USER_ID + " INTEGER UNIQUE, "
            + COLUMN_USER_NAME + " TEXT UNIQUE, "
            + COLUMN_MESSAGE_COUNT + " INTEGER, "
            + COLUMN_POINTS + " INTEGER)";

    private static final String getUserIdStatement = "SELECT * FROM "
            + CHAT_TABLE + " WHERE "
            + COLUMN_USER_ID + " = ?";


    private static final String updateUserStatement = "UPDATE "
            + CHAT_TABLE + " SET "
            + COLUMN_USER_ID + " = ?,"
            + COLUMN_MESSAGE_COUNT + " = " + COLUMN_MESSAGE_COUNT + " + ?,"
            + COLUMN_POINTS + " = " + COLUMN_POINTS + " + ? WHERE "
            + COLUMN_USER_NAME + " = ?";

    private static final String updateTemp = "UPDATE "
            + CHAT_TABLE + " SET "
            + COLUMN_USER_ID + " = ? WHERE "
            + COLUMN_USER_NAME + " = ?";

    private static final String insertUserStatement = "INSERT OR IGNORE INTO "
            + CHAT_TABLE + " ("
            + COLUMN_USER_NAME + ", "
            + COLUMN_MESSAGE_COUNT + ", "
            + COLUMN_POINTS + ") VALUES (?, ?, ?)";

    private static final String deleteIfIdStatement = "DELETE FROM "
            + CHAT_TABLE + " WHERE "
            + COLUMN_USER_ID + " = ?";


    public ChatTable() {

    }

    public String getCreateTableSql() {
        return CREATE_CHAT_TABLE;
    }


    /**
     * Inserts only unique userNames to the table from the list
     *
     * @param chatUserList list of userNames to store in the database
     */
    public void insertUserNames(List<String> chatUserList) {
        Connection connection = DatabaseHandler.openConnection();
        DatabaseHandler.turnOffAutoCommit(connection);
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, insertUserStatement);


        for (Iterator<String> iterator = chatUserList.iterator(); iterator.hasNext(); ) {
            String userName = iterator.next();
            try {
                preparedStatement.setString(1, userName);
                preparedStatement.setInt(2, 0);
                preparedStatement.setInt(3, 0);

                // Remove usernames already in the table from the list to get a list of usernames that
                // are new to the table at the end off the loop
                if (preparedStatement.executeUpdate() == 0) {
                    iterator.remove();
                }

            } catch (SQLException e) {
                System.out.println("Failed to insert or delete " + e.getMessage());
                DatabaseHandler.rollBack(connection);
            }
        }

        DatabaseHandler.turnOnAutoCommit(connection);
        DatabaseHandler.closeConnection(connection);
    }


    /**
     * Takes a list of Users and
     * 1) Figures out which ids from the list are already in the table, if the userId is are already in the table
     * it means the userName has changed and must be updated. The row info is stored in a ChatUser list
     * and the row is then deleted
     * NOTE: the passed in User list user is also removed from the list when an id already exists
     * <p>
     * 2) If the ChatUser list is not empty, it combines the row in the table with the desired userName from
     * th ChatUSer list
     * <p>
     * 3) It then updates the userId with the desired name from the User list
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
        if (!chatUserList.isEmpty()) {
            updateUserIdAndRow(chatUserList, connection);
        }
        updateIdWithUserName(userList, connection);

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
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, getUserIdStatement);
        PreparedStatement deleteStatement = DatabaseHandler.prepareStatement(connection, deleteIfIdStatement);

        List<ChatUser> chatUserList = new ArrayList<>();
        for (Iterator<User> iterator = userList.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            try {
                preparedStatement.setInt(1, Integer.parseInt(user.getId()));
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    do {
                        int id = resultSet.getInt(INDEX_COLUMN_ID);
                        long userId = resultSet.getLong(INDEX_COLUMN_USER_ID);
                        String userName = resultSet.getString(INDEX_COLUMN_USER_NAME);
                        int messageCount = resultSet.getInt(INDEX_COLUMN_MESSAGE_COUNT);
                        int points = resultSet.getInt(INDEX_COLUMN_POINTS);

                        chatUserList.add(new ChatUser(id, userId, user.getLogin(), messageCount, points));
                    } while (resultSet.next());

                    deleteStatement.setInt(1, Integer.parseInt(user.getId()));
                    deleteStatement.executeUpdate();
                    iterator.remove();
                }

            } catch (SQLException e) {
                System.out.println("Failed to query userIds " + e.getMessage());
                DatabaseHandler.rollBack(connection);
            }

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
    private void updateUserIdAndRow(List<ChatUser> chatUserList, Connection connection) {
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, updateUserStatement);

        try {
            for (ChatUser chatUser : chatUserList) {
                preparedStatement.setLong(1, chatUser.getUserId());
                preparedStatement.setInt(2, chatUser.getMessageCount());
                preparedStatement.setInt(3, chatUser.getPoints());
                preparedStatement.setString(4, chatUser.getUserName());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        }
    }


    /**
     * Updates rows with desired userName with the userId from User with the same userName
     *
     * @param userList   list of Users containing userIds to store
     * @param connection connection to database
     */
    private void updateIdWithUserName(List<User> userList, Connection connection) {
        PreparedStatement preparedStatement = DatabaseHandler.prepareStatement(connection, updateTemp);
        try {
            for (User user : userList) {
                preparedStatement.setLong(1, Long.parseLong(user.getId()));
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
            DatabaseHandler.rollBack(connection);
        }
    }
}
