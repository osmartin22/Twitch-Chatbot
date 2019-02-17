package ozmar.database.interfaces;

import com.github.twitch4j.helix.domain.User;
import ozmar.user.ChatUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface ChatTableInterface {

    @Nonnull
    String getCreateTableSql();

    long getUserId(@Nonnull String userName);

    void checkIfNameExists(@Nonnull List<String> list);

    void addUsersToTable(@Nonnull List<User> list);

    void insertUserList(@Nonnull List<User> list);

    void updatePoints(@Nonnull Map<Long, ChatUser> map);

    int getMessageCountByUserId(long userId);

    int getMessageCountByUserName(@Nonnull String userName);

    int getPointsByUserId(long userId);

    int getPointsByUserName(@Nonnull String userName);

    void updatePartner(long userId, @Nonnull String newPartner);

    @Nullable
    String getPartnerById(long userId);
}
