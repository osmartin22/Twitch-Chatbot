package ozmar.database.dao;

import ozmar.database.dao.interfaces.WordCountDaoInterface;
import ozmar.database.tables.interfaces.WordCountTableInterface;

import javax.annotation.Nonnull;
import java.util.Map;

public class WordCountDao implements WordCountDaoInterface {

    private WordCountTableInterface wordCountTable;

    public WordCountDao(WordCountTableInterface wordCountTable) {
        this.wordCountTable = wordCountTable;
    }

    @Override
    public Map<String, Integer> queryWordCount() {
        return wordCountTable.queryWordCount();
    }

    @Override
    public Map<String, Integer> queryTop10Words() {
        return wordCountTable.queryTop10Words();
    }

    @Override
    public int querySpecificWordCount(@Nonnull String word) {
        return wordCountTable.querySpecificWordCount(word);
    }

    @Override
    public void updateOrInsertWordCount(@Nonnull Map<String, Integer> map) {
        if (!map.isEmpty()) {
            wordCountTable.updateOrInsertWordCount(map);
        }
    }

    @Override
    public void clearTable() {
        wordCountTable.clearTable();
    }
}
