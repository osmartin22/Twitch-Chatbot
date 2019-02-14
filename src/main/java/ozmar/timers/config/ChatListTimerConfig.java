package ozmar.timers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.timers.ChatListTimer;

@Configuration
public class ChatListTimerConfig {

    @Bean
    ChatListTimer chatListTimerBean(DatabaseHandlerInterface db) {
        return new ChatListTimer(db);
    }
}
