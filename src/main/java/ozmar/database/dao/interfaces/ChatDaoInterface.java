package ozmar.database.dao.interfaces;

import com.github.twitch4j.helix.domain.User;
import ozmar.user.ChatUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface ChatDaoInterface {

    long getUserId(@Nonnull String userName);

    void addUserList(@Nonnull List<User> userList);

    void checkIfNamesExist(@Nonnull List<String> list);

    void updatePoints(@Nonnull Map<Long, ChatUser> map);

    int getMessageCount(long userId);

    int getMessageCount(@Nonnull String userName);

    int getPoints(long userId);

    int getPoints(@Nonnull String userName);

    @Nullable
    String getPartnerById(long userId);

    void updatePartner(long userId, @Nonnull String newPartner);
}
