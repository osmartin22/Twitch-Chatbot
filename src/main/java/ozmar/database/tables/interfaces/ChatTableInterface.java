package ozmar.database.tables.interfaces;

import com.github.twitch4j.helix.domain.User;
import ozmar.user.ChatUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface ChatTableInterface {

    long getUserId(@Nonnull String userName);

    void checkIfNameExists(@Nonnull List<String> list);

    void addUserList(@Nonnull List<User> list);

    void insertUserList(@Nonnull List<User> list);

    void updatePoints(@Nonnull Map<Long, ChatUser> map);

    int getMessageCount(long userId);

    int getMessageCount(@Nonnull String userName);

    int getPoints(long userId);

    int getPoints(@Nonnull String userName);

    void updatePartner(long userId, @Nonnull String newPartner);

    @Nullable
    String getPartner(long userId);
}
