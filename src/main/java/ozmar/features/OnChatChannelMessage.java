package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.events.domain.EventUser;
import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.buffer.interfaces.WordCountBufferInterface;

import javax.annotation.Nonnull;

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

//        System.out.printf(
//                "Channel [%s] - User[%s] - Id[%d] - Message [%s]%n",
//                event.getChannel().getName(),
//                event.getUser().getName(),
//                event.getUser().getId(),
//                event.getMessage()
//        );
    }

    private void handleChatData(EventUser user) {
        long userId = user.getId();
        chatDataBuffer.updateChatUser(userId, user.getName());
    }

    private void handleWordCount(@Nonnull String message) {
        for (String s : message.split(" ")) {

            // Don't store urls "http"
            // Ignore words starting with "[" as it is commonly used in bot responses
            if (s.startsWith("http") || s.startsWith("[")) {
                continue;
            }

            // Remove @ symbol so that @username and username are registered as the same
            if (s.length() > 1) {
                if (s.startsWith("@") || s.startsWith("\"") || s.startsWith(" ")) {
                    s = s.substring(1);
                }

                char last = s.charAt(s.length() - 1);
                if (last == ',' || last == '.' || last == ';' || last == '"') {
                    s = s.substring(0, s.length() - 1);
                }
            }
            if (s.length() > 1 && s.startsWith("@")) {
                s = s.substring(1);
            }

            wordCountBuffer.updateWordCount(s);
        }
    }
}
