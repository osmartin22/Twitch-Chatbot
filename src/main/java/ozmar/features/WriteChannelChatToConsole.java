package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import ozmar.buffer.ChatDataBuffer;
import ozmar.buffer.WordCountBuffer;

import javax.annotation.Nonnull;

// TODO: Maybe remove punctuation so that "hey" and "hey," are the same word
public class WriteChannelChatToConsole {
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        String message = event.getMessage().trim();

        handleChatData(event);
        handleWordCount(message);

        System.out.printf(
                "Channel [%s] - User[%s] - Id[%d] - Message [%s]%n",
                event.getChannel().getName(),
                event.getUser().getName(),
                event.getUser().getId(),
                event.getMessage()
        );
    }

    private void handleChatData(ChannelMessageEvent event) {
        long userId = event.getUser().getId();
        ChatDataBuffer chatDataBuffer = new ChatDataBuffer();
        chatDataBuffer.updateChatUser(userId, event.getUser().getName());
    }

    private void handleWordCount(@Nonnull String message) {
        for (String s : message.split(" ")) {

            // Ignore urls from being stored and wasting space since it is unlikely the same url is
            // more than a couple of times unless chat spams it
            if (s.startsWith("http")) {
                continue;
            }

            // Remove @ symbol so that @username and username are registered as the same
            if (s.length() > 1 && s.startsWith("@")) {
                s = s.substring(1);
            }

            WordCountBuffer wordCountBuffer = new WordCountBuffer();
            wordCountBuffer.updateWordCount(s);
        }
    }
}
