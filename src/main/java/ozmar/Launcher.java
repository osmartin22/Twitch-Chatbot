package ozmar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ozmar.timers.ChatListTimer;
import ozmar.timers.WordCountTimer;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BotConfig.class);

        Bot bot = context.getBean(Bot.class);
        bot.initialize();
        bot.registerFeatures();
        bot.start();

        ChatListTimer chatListTimer = context.getBean(ChatListTimer.class);
        WordCountTimer wordCountTimer = context.getBean(WordCountTimer.class);
        chatListTimer.startTimer();
        wordCountTimer.startTimer();
    }
}
