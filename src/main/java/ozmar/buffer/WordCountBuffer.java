package ozmar.buffer;

import java.util.HashMap;
import java.util.Map;

public class WordCountBuffer {
    private static boolean currentWordCountMap = true;   // true = wordCountMap1 false = wordCountMap2
    private static Map<String, Integer> wordCountMap1 = new HashMap<>();
    private static Map<String, Integer> wordCountMap2 = new HashMap<>();

    public WordCountBuffer() {

    }

    /**
     * Gets the current map and flips the flag so the other map is used
     *
     * @return Map
     */
    public Map<String, Integer> getMap() {
        Map<String, Integer> currentMap = getCurrentMap();
        flipFlag();
        return currentMap;
    }

    /**
     * Clears the map of the one currently not in use
     */
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
    public void updateWordCount(String word) {
        Map<String, Integer> currentMap = getCurrentMap();
        int count = currentMap.getOrDefault(word, 0) + 1;
        currentMap.put(word, count);
    }

    /**
     * Gets the current map
     *
     * @return Map
     */
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
