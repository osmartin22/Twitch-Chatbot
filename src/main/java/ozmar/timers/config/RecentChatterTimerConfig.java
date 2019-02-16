package ozmar.timers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.timers.RecentChatterTimer;

@Configuration
public class RecentChatterTimerConfig {

    @Bean
    RecentChatterTimer recentChatterTimerBean(RecentChattersInterface recentChatters) {
        return new RecentChatterTimer(recentChatters);
    }
}
