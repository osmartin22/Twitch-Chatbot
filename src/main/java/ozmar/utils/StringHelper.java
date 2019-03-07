package ozmar.utils;

import reactor.util.annotation.NonNull;

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
     * Inserts special characters so that words can bypass blacklist
     * NOTE: Might still not work depending on the filter of the channel, should not be abused
     *
     * @param word word to modify
     * @return String
     */
    @Nonnull
    public static String insertSpecialChars(@Nonnull String word) {
        String special = "\u00AD\u2063";
        return word.substring(0, 1) + special + word.substring(1);
    }


    public static boolean startsWithVowel(@NonNull String input) {
        if (input.length() > 0) {
            char firstChar = input.charAt(0);
            return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
        } else {
            return false;
        }
    }
}
