package ozmar.database.interfaces;

import com.github.twitch4j.helix.domain.User;
import ozmar.ChatUser;
import ozmar.Command;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public interface DatabaseHandlerInterface {

    List<Command> getCommands();

    void insertCommand(@Nonnull Command command);

    void updateCommandUsage(@Nonnull Command command);

    Map<String, Integer> getWordCount();

    Map<String, Integer> getTop10Words();

    int getSpecificWordCount(@Nonnull String word);

    void updateOrInsertWordCount(@Nonnull Map<String, Integer> map);

    void clearWordCount();

    void addUserList(@Nonnull List<User> userList);

    void checkIfNamesExist(@Nonnull List<String> list);

    void updatePoints(@Nonnull Map<Long, ChatUser> map);

    int getMessageCount(long userId);

    int getMessageCount(@Nonnull String userName);

    int getPoints(long userId);

    int getPoints(@Nonnull String userName);

    String getValentine(long userId);

    void updateValentine(long userId, @Nonnull String newValentine);
}
