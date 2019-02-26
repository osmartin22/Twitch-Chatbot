package ozmar.buffer.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface RecentChattersInterface {

    @Nonnull
    Map<String, Long> getRecentChatters();

    @Nullable
    String getRandomRecentChatter();

}
