package ozmar.buffer.interfaces;

import javax.annotation.Nonnull;
import java.util.Map;

public interface RecentChattersInterface {

    @Nonnull
    Map<String, Long> getRecentChatters();
}
