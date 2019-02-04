package ozmar.timers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.DatabaseHandler;
import ozmar.timers.ChatListTimer;

@Configuration
public class ChatListTimerConfig {

    @Bean
    ChatListTimer chatListTimerBean(DatabaseHandler db) {
        return new ChatListTimer(db);
    }
}
