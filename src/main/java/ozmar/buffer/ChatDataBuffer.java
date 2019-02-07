package ozmar.buffer;

import ozmar.ChatUser;
import ozmar.utils.RandomHelper;

import java.util.HashMap;
import java.util.Map;

public class ChatDataBuffer {
    private static boolean currentChatDataMap = true;    // true = chatData1     false = chatData1
    private static Map<Long, ChatUser> chatData1 = new HashMap<>();
    private static Map<Long, ChatUser> chatData2 = new HashMap<>();

    public ChatDataBuffer() {

    }

    /**
     * Gets the current map and flips the flag so the other map is used
     *
     * @return Map
     */
    public Map<Long, ChatUser> getMap() {
        Map<Long, ChatUser> currentMap = getCurrentMap();
        flipFlag();
        return currentMap;
    }

    /**
     * Clears the map of the one currently not in use
     */
    public void clearMap() {
        if (currentChatDataMap) {
            chatData2.clear();
        } else {
            chatData1.clear();
        }

        currentChatDataMap = !currentChatDataMap;
    }

    /**
     * Increments the message count and points of a ChatUser if the userId is contained in the map
     * If not found, a new ChatUser is added to the map
     *
     * @param userId   used as the key to check in the map
     * @param userName used only when storing a new ChatUser to the map
     */
    public void updateChatUser(long userId, String userName) {
        int randPoints = RandomHelper.getRandNumInRange(0, 1);
        Map<Long, ChatUser> currentMap = getCurrentMap();
        if (currentMap.containsKey(userId)) {
            ChatUser chatUser = currentMap.get(userId);
            chatUser.incrementMessageCount(1);
            chatUser.incrementPoints(randPoints);
        } else {
            ChatUser user = new ChatUser(userId, userName, 1, randPoints);
            currentMap.put(userId, user);
        }
    }

    /**
     * Gets the current map
     *
     * @return Map
     */
    private Map<Long, ChatUser> getCurrentMap() {
        return (currentChatDataMap) ? chatData1 : chatData2;
    }

    /**
     * Flip the flag
     */
    private void flipFlag() {
        currentChatDataMap = !currentChatDataMap;
    }
}
