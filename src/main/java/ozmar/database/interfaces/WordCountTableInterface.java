package ozmar.database.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface WordCountTableInterface {

    @Nullable
    Map<String, Integer> queryWordCount();

    @Nonnull
    Map<String, Integer> getTop10Words();

    int getSpecificWordCount(@Nonnull String word);

    void updateOrInsert(@Nonnull Map<String, Integer> map);

    void clearTable();
}
