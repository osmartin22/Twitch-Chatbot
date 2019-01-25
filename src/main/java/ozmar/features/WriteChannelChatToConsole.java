package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import ozmar.Bot;

public class WriteChannelChatToConsole {
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        String message = event.getMessage().trim();

        for (String s : message.split(" ")) {

            // Ignore urls
            if (s.startsWith("http")) {
                break;
            }

            int count = Bot.wordCount.getOrDefault(s, 0) + 1;
            Bot.wordCount.put(s, count);
        }

        System.out.printf(
                "Channel [%s] - User[%s] - Message [%s]%n",
                event.getChannel().getName(),
                event.getUser().getName(),
                event.getMessage()
        );
    }
}
