package ozmar;

public class ChatUser {

    private int id;
    private long userId;
    private String userName;
    private int messageCount = 0;
    private int points = 0;

    public ChatUser(String userName) {
        this.userName = userName;
    }

    public ChatUser(long userId, String userName, int messageCount, int points) {
        this.userId = userId;
        this.userName = userName;
        this.messageCount = messageCount;
        this.points = points;
    }

    public ChatUser(int id, long userId, String userName, int messageCount, int points) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.messageCount = messageCount;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public long getUserId() {
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

    public void incrementMessageCount(int increment) {
        this.messageCount += increment;
    }

    public void incrementPoints(int increment) {
        this.points += increment;
    }
}
