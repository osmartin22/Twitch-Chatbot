package ozmar.database.interfaces;

import com.github.twitch4j.helix.domain.User;
import ozmar.ChatUser;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public interface ChatTableInterface {

    @Nonnull
    String getCreateTableSql();

    void checkIfNameExists(@Nonnull List<String> list);

    void addUsersToTable(@Nonnull List<User> list);

    void insertUserList(@Nonnull List<User> list);

    void updatePoints(@Nonnull Map<Long, ChatUser> map);

    int getMessageCountByUserId(long userId);

    int getMessageCountByUserName(@Nonnull String userName);

    int getPointsByUserId(long userId);

    int getPointsByUserName(@Nonnull String userName);

    void updateValentine(long userId, @Nonnull String newValentine);

    @Nonnull
    String getValentineById(long userId);
}
