package ozmar.database;

import ozmar.ChatUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChatTable {

    private static final String CHAT_TABLE = "chatTable";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_USER_ID = "userId";
    private final static String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_MESSAGE_COUNT = "messageCount";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_SUB_STATUS = "subStatus";

    private static final int INDEX_COLUMN_ID = 1;
    private static final int INDEX_COLUMN_USER_ID = 2;
    private static final int INDEX_COLUMN_USER_NAME = 3;
    private static final int INDEX_COLUMN_MESSAGE_COUNT = 4;
    private static final int INDEX_COLUMN_POINTS = 5;
    private static final int INDEX_COLUMN_SUB_STATUS = 6;


    private final static String CREATE_CHAT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + CHAT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USER_ID + " INTEGER UNIQUE, "
            + COLUMN_USER_NAME + " TEXT UNIQUE, "
            + COLUMN_MESSAGE_COUNT + " INTEGER, "
            + COLUMN_POINTS + " INTEGER, "
            + COLUMN_SUB_STATUS + " INTEGER )";

    private static final String insertUserStatement = "INSERT OR IGNORE INTO "
            + CHAT_TABLE + " ("
            + COLUMN_USER_ID + ", "
            + COLUMN_USER_NAME + ", "
            + COLUMN_MESSAGE_COUNT + ", "
            + COLUMN_POINTS + ", "
            + COLUMN_SUB_STATUS + ") VALUES (?, ?, ?, ?, ?)";


    public ChatTable() {

    }

    public String getCreateTableSql() {
        return CREATE_CHAT_TABLE;
    }

    public void insertUserNames(List<ChatUser> chatUserList) {
        Connection connection = DatabaseHandler.openConnection();
        PreparedStatement preparedStatement = preparedStatementHelper(connection, insertUserStatement);
        int returnValue = 0;

        int count = 0;
        while (count < chatUserList.size()) {
            ChatUser chatUser = chatUserList.get(count);
            try {
                preparedStatement.setString(1, chatUser.getUserName());
                preparedStatement.setInt(2, chatUser.getMessageCount());
                preparedStatement.setInt(3, chatUser.getPoints());
                preparedStatement.setInt(4, chatUser.getSubStatus());

            } catch (SQLException e) {
                //
            }


            count++;
        }

        try {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to insert " + e.getMessage());
        }
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
