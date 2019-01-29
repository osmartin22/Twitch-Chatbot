package ozmar;

public class ChatUser {

    private int id;
    private int userId;
    private String userName;
    private int messageCount = 0;
    private int points = 0;
    private int subStatus = 0;

    public ChatUser(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public int getPoints() {
        return points;
    }

    public int getSubStatus() {
        return subStatus;
    }

}
