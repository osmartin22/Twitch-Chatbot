package ozmar.buffer;

import ozmar.buffer.interfaces.WordCountBufferInterface;

import javax.annotation.Nonnull;
import java.util.Map;

public class WordCountBuffer implements WordCountBufferInterface {
    private boolean currentWordCountMap = true;   // true = wordCountMap1 false = wordCountMap2
    private final Map<String, Integer> wordCountMap1;
    private final Map<String, Integer> wordCountMap2;

    public WordCountBuffer(Map<String, Integer> wordCountMap1, Map<String, Integer> wordCountMap2) {
        this.wordCountMap1 = wordCountMap1;
        this.wordCountMap2 = wordCountMap2;
    }

    /**
     * Gets the current map and flips the flag so the other map is used
     *
     * @return Map
     */
    @Nonnull
    @Override
    public Map<String, Integer> getMap() {
        Map<String, Integer> currentMap = getCurrentMap();
        flipFlag();
        return currentMap;
    }

    /**
     * Clears the map of the one currently not in use
     */
    @Override
    public void clearMap() {
        if (currentWordCountMap) {
            wordCountMap2.clear();
        } else {
            wordCountMap1.clear();
        }
    }

    /**
     * Increments the count for the word. If it does not exist, it is inserted with a count of 1
     *
     * @param word String to increment or put in the map
     */
    @Override
    public void updateWordCount(@Nonnull String word) {
        Map<String, Integer> currentMap = getCurrentMap();
        int count = currentMap.getOrDefault(word, 0) + 1;
        currentMap.put(word, count);
    }

    /**
     * Gets the current map
     *
     * @return Map
     */
    @Nonnull
    private Map<String, Integer> getCurrentMap() {
        return (currentWordCountMap) ? wordCountMap1 : wordCountMap2;
    }

    /**
     * Flip the flag
     */
    private void flipFlag() {
        currentWordCountMap = !currentWordCountMap;
    }
}
