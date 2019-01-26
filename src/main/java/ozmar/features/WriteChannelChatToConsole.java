package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import ozmar.Bot;

//TODO: Maybe remove punctuation so that "hey" and "hey," are the same word
// Maybe remove @ so that "username" and "@username" are the same
public class WriteChannelChatToConsole {
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        String message = event.getMessage().trim();

        for (String s : message.split(" ")) {

            // Ignore urls
            if (s.startsWith("http")) {
                break;
            }

            if (Bot.currentWordCountMap) {
                int count = Bot.wordCountMap1.getOrDefault(s, 0) + 1;
                Bot.wordCountMap1.put(s, count);
            } else {
                int count = Bot.wordCountMap2.getOrDefault(s, 0) + 1;
                Bot.wordCountMap2.put(s, count);
            }

        }

        System.out.printf(
                "Channel [%s] - User[%s] - Message [%s]%n",
                event.getChannel().getName(),
                event.getUser().getName(),
                event.getMessage()
        );
    }
}
