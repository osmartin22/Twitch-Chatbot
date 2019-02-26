package ozmar;

import com.vdurmont.emoji.EmojiParser;
import ozmar.utils.StringHelper;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFilter {

    public static Map<String, String[]> timeoutWordsMap = new HashMap<>();
    public static Map<String, String[]> bannedWordsMap = new HashMap<>();

    private WordFilter() {

    }

    public static void loadConfigs() {
        timeoutWordsMap = getFileData("C:\\TwitchBotFiles\\timeoutWords.txt");
        bannedWordsMap = getFileData("C:\\TwitchBotFiles\\bannedWords.txt");
    }

    @Nonnull
    private static Map<String, String[]> getFileData(@Nonnull String fileLocation) {
        Map<String, String[]> map = new HashMap<>();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            while ((line = reader.readLine()) != null) {
                try {
                    String[] content = line.split(",");
                    if (content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignore_in_combination_with_words = new String[]{};
                    if (content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }
                    map.put(word.replaceAll("\\s+", ""), ignore_in_combination_with_words);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
     * Checks if the word is on the list and then checks if the word should be ignored
     *
     * @param input String to filter
     * @return List of strings that were found
     */
    // TODO: Currently if the input contains a filtered and an ignore word, it doesn't catch the word
    // e.g. banned = ass, allowed = pass   -> for "ass pass" the banned word is not caught
    @Nonnull
    public static List<String> badWordsFound(@Nonnull String input) {
        input = makeInputReady(input);
        List<String> badWords = new ArrayList<>();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < (length + 1 - i); j++) {
                String wordToCheck = input.substring(i, i + j);

                if (bannedWordsMap.containsKey(wordToCheck)) {
                    String[] ignoreCheck = bannedWordsMap.get(wordToCheck);
                    boolean ignore = false;
                    for (String s1 : ignoreCheck) {
                        if (input.contains(s1)) {
                            ignore = true;
                            break;
                        }
                    }

                    if (!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }

        return badWords;
    }

    @Nonnull
    private static String makeInputReady(@Nonnull String input) {
        input = input.replaceAll("1", "i");
        input = input.replaceAll("!", "i");
        input = input.replaceAll("3", "e");
        input = input.replaceAll("4", "a");
        input = input.replaceAll("@", "a");
        input = input.replaceAll("5", "s");
        input = input.replaceAll("7", "t");
        input = input.replaceAll("0", "o");
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");
        return input;
    }

    @Nonnull
    public static String timeoutWordFound(@Nonnull String word) {
        String temp = makeInputReady(word).trim();
        if (timeoutWordsMap.containsKey(temp)) {
            word = StringHelper.insertSpecialChars(word);
        }

        return word;
    }

    /**
     * Returns a list of each emoji found, empty list if none are found
     *
     * @param input string to extract emojis from
     * @return List of found emojis
     */
    @Nonnull
    public static List<String> extractEmojis(@Nonnull String input) {
        return EmojiParser.extractEmojis(input);
    }

    /**
     * Removes emojis from the given string
     *
     * @param input String to remove emojis from
     * @return string without emojis
     */
    @Nonnull
    public static String removeEmojis(@Nonnull String input) {
        return EmojiParser.removeAllEmojis(input);
    }

    /**
     * Modifies the string in an attempt to make it a word
     * <p>
     * NOTE: meant for 1 word at a time, not a full sentence
     * Words passed into this method should have emojis extracted from it before hand
     * else an error will be thrown
     *
     * @param word string to modify
     * @return modified string
     */
    @Nonnull
    public static String transformWord(@Nonnull String word) {
        if (word.matches("(.)\\1*") && word.length() > 3) { // Turn "......" into "..."
            word = word.substring(0, 3);
        } else if (word.length() > 3) {
            word = word.replaceFirst("^[^\\p{IsAlnum}#!_]+", "");
            if (word.length() > 1) {
                char first = word.charAt(0);
                word = word.replaceAll("[^\\p{IsAlnum}'_\\-.]", "");
                if (first == '!' || first == '#') {
                    word = first + word;
                }
                word = word.replaceAll("[^\\p{IsAlnum}_]+$", "");
            }
        }

        return word;
    }
}
