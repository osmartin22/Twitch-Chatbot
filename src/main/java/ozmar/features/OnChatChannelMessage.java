package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.events.domain.EventUser;
import com.vdurmont.emoji.EmojiParser;
import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.buffer.interfaces.WordCountBufferInterface;

import javax.annotation.Nonnull;
import java.util.List;

public class OnChatChannelMessage {

    private final WordCountBufferInterface wordCountBuffer;
    private final ChatDataBufferInterface chatDataBuffer;
    private final RecentChattersInterface recentChatters;

    public OnChatChannelMessage(WordCountBufferInterface wordCountBuffer, ChatDataBufferInterface chatDataBuffer,
                                RecentChattersInterface recentChatters) {
        this.wordCountBuffer = wordCountBuffer;
        this.chatDataBuffer = chatDataBuffer;
        this.recentChatters = recentChatters;
    }

    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        handleChatData(event.getUser());
        recentChatters.getRecentChatters().put(event.getUser().getName(), System.currentTimeMillis());

        long userId = event.getUser().getId();
        if (userId != 19264788 && userId != 250198045 && userId != 402734706) { // Ignore known bot responses
            String message = event.getMessage().trim();
            handleWordCount(message);
        }

        System.out.println(event.getMessage());

//        System.out.printf(
//                "Channel [%s] - User[%s] - Id[%d] - Message [%s]%n",
//                event.getChannel().getName(),
//                event.getUser().getName(),
//                event.getUser().getId(),
//                event.getMessage()
//        );
    }

    private void handleChatData(@Nonnull EventUser user) {
        long userId = user.getId();
        chatDataBuffer.updateChatUser(userId, user.getName());
    }

    private void handleWordCount(@Nonnull String message) {
        // Extract and remove all emojis
        List<String> emojiList = EmojiParser.extractEmojis(message);
        message = EmojiParser.removeAllEmojis(message);
        for (String emoji : emojiList) {
            wordCountBuffer.updateWordCount(emoji);
        }

        for (String word : message.split("\\s+")) {

            // Don't store urls "http"
            // Ignore words starting with "[" as it is commonly used in bot responses
            if (word.startsWith("http") || word.startsWith("[")) {
                continue;
            }

            if (word.matches("(.)\\1*")) {
                // Turn "......" into "..."
                if (word.length() > 3) {
                    word = word.substring(0, 3);
                }
            } else {
                // > 3 to allow ASCII faces
                if (word.length() > 3) {
                    word = word.replaceFirst("^[^\\p{IsDigit}\\p{IsAlphabetic}#!_]+", "");
                    if (word.length() > 1) {
                        char first = word.charAt(0);
                        word = word.replaceAll("[^\\p{IsDigit}\\p{IsAlphabetic}'_\\-.]", "");
                        if (first == '!' || first == '#') {
                            word = first + word;
                        }
                        word = word.replaceAll("[^\\p{IsDigit}\\p{IsAlphabetic}_]+$", "");
                    }
                }
            }

            wordCountBuffer.updateWordCount(word);
        }
    }
}
