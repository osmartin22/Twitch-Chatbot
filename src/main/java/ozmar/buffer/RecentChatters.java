package ozmar.buffer;

import ozmar.buffer.interfaces.RecentChattersInterface;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class RecentChatters implements RecentChattersInterface {

    private final Map<String, Long> recentChatters;

    public RecentChatters(Map<String, Long> recentChatters) {
        this.recentChatters = recentChatters;
    }

    @Nonnull
    @Override
    public Map<String, Long> getRecentChatters() {
        return recentChatters;
    }

    @Nullable
    public String getRandomRecentChatter() {
        if (recentChatters.isEmpty()) {
            System.out.println("RECENT CHATTERS IS EMPTY");
            return null;

        } else {
            Object randomName = recentChatters.entrySet().toArray()[new Random().nextInt(recentChatters.entrySet().toArray().length)];
            return randomName.toString().substring(0, randomName.toString().indexOf("="));
        }
    }
}
