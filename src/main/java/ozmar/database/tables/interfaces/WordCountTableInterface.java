package ozmar.database.tables.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface WordCountTableInterface {

    @Nullable
    Map<String, Integer> queryWordCount();

    @Nonnull
    Map<String, Integer> queryTop10Words();

    int querySpecificWordCount(@Nonnull String word);

    void updateOrInsertWordCount(@Nonnull Map<String, Integer> map);

    void clearTable();
}
