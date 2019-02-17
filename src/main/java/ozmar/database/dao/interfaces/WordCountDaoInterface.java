package ozmar.database.dao.interfaces;

import javax.annotation.Nonnull;
import java.util.Map;

public interface WordCountDaoInterface {

    Map<String, Integer> queryWordCount();

    Map<String, Integer> queryTop10Words();

    int querySpecificWordCount(@Nonnull String word);

    void updateOrInsertWordCount(@Nonnull Map<String, Integer> map);

    void clearTable();
}
