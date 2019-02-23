package ozmar.utils;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.stream.Collectors;

public class StringHelper {

    private StringHelper() {

    }

    /**
     * gets the first word of a string
     *
     * @param word string to parse
     * @return String
     */
    @Nonnull
    public static String getFirstWord(@Nonnull String word) {
        if (word.contains(" ")) {
            word = word.substring(0, word.indexOf(" "));
        }

        return word;
    }

    /**
     * Converts a map into a string
     *
     * @param map map to convert to string
     * @return String
     */
    @Nonnull
    public static String turnMapToString(@Nonnull Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .map(e -> e.getKey() + " " + e.getValue())
                .collect(Collectors.joining(", "));
    }

    /**
     * Inserts special characters so that banned words can be posted
     * NOTE: Channels can still have these chars banned as well
     * NOTE: NOT meant to be used to circumvent filters
     *
     * @param word word to modify
     * @return String
     */
    @Nonnull
    public static String insertSpecialChars(@Nonnull String word) {
        String special = "\u00AD\u2063";
        return word.substring(0, 1) + special + word.substring(1);
    }
}
