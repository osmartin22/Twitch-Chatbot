package ozmar.database;

public class ChatTable {

    private static final String CHAT_TABLE = "chatTable";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_USER_ID = "userId";
    private final static String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_MOD_STATUS = "modStatus";
    private static final String COLUMN_VIP_STATUS = "vipStatus";
    private static final String COLUMN_MESSAGE_COUNT = "messageCount";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_SUB_STATUS = "subStatus";

    private static final int INDEX_COLUMN_ID = 1;
    private static final int INDEX_COLUMN_USER_ID = 2;
    private static final int INDEX_COLUMN_USER_NAME = 3;
    private static final int INDEX_COLUMN_MOD_STATUS = 4;
    private static final int INDEX_COLUMN_VIP_STATUS = 5;
    private static final int INDEX_COLUMN_MESSAGE_COUNT = 6;
    private static final int INDEX_COLUMN_POINTS = 7;
    private static final int INDEX_COLUMN_SUB_STATUS = 8;


    private final static String CREATE_CHAT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + CHAT_TABLE + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USER_ID + " INTEGER, "
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_MOD_STATUS + " INTEGER, "
            + COLUMN_VIP_STATUS + " INTEGER, "
            + COLUMN_MESSAGE_COUNT + " INTEGER, "
            + COLUMN_POINTS + " INTEGER, "
            + COLUMN_SUB_STATUS + " INTEGER )";


    private ChatTable() {

    }

    public static String getCreateTableSql() {
        return CREATE_CHAT_TABLE;
    }

}
