package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import ozmar.Bot;
import ozmar.ChatUser;
import ozmar.utils.RandomHelper;

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
        if (Bot.currentChatDataMap) {
            if (Bot.chatData1.containsKey(userId)) {
                ChatUser user = Bot.chatData1.get(userId);
                user.incrementMessageCount(1);
                user.incrementPoints(RandomHelper.getRandomNumber(1));
            } else {
                ChatUser user = new ChatUser(userId, event.getUser().getName(),
                        1, RandomHelper.getRandomNumber(1));
                Bot.chatData1.put(userId, user);
            }

        } else {
            if (Bot.chatData2.containsKey(userId)) {
                ChatUser user = Bot.chatData2.get(userId);
                user.incrementMessageCount(1);
                user.incrementPoints(RandomHelper.getRandomNumber(1));
            } else {
                ChatUser user = new ChatUser(userId, event.getUser().getName(),
                        1, RandomHelper.getRandomNumber(1));
                Bot.chatData2.put(userId, user);
            }
        }
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

            if (Bot.currentWordCountMap) {
                int count = Bot.wordCountMap1.getOrDefault(s, 0) + 1;
                Bot.wordCountMap1.put(s, count);
            } else {
                int count = Bot.wordCountMap2.getOrDefault(s, 0) + 1;
                Bot.wordCountMap2.put(s, count);
            }
        }
    }
}
