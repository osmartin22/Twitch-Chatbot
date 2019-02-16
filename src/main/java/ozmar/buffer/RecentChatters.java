package ozmar.buffer;

import ozmar.buffer.interfaces.RecentChattersInterface;

import javax.annotation.Nonnull;
import java.util.Map;

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
}
