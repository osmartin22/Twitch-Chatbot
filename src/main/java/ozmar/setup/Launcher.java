package ozmar.setup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ozmar.WordFilter;
import ozmar.timers.ChatListTimer;
import ozmar.timers.RecentChatterTimer;
import ozmar.timers.WordCountTimer;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BotConfig.class);
        context.registerShutdownHook();

        // TODO: TEMP
        WordFilter.loadConfigs();

        Bot bot = context.getBean(Bot.class);
        bot.initialize();
        bot.registerFeatures();
        bot.start();

        ChatListTimer chatListTimer = context.getBean(ChatListTimer.class);
        WordCountTimer wordCountTimer = context.getBean(WordCountTimer.class);
        RecentChatterTimer recentChatterTimer = context.getBean(RecentChatterTimer.class);
        chatListTimer.startTimer();
        wordCountTimer.startTimer();
        recentChatterTimer.startTimer();
    }
}