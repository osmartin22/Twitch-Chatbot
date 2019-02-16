package ozmar.buffer.interfaces;

import ozmar.user.ChatUser;

import javax.annotation.Nonnull;
import java.util.Map;

public interface ChatDataBufferInterface {

    @Nonnull
    Map<Long, ChatUser> getMap();

    void clearMap();

    void updateChatUser(long userId, @Nonnull String userName);
}
