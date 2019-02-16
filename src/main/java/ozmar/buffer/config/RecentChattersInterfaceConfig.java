package ozmar.buffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.RecentChatters;
import ozmar.buffer.interfaces.RecentChattersInterface;

import java.util.Map;

@Configuration
public class RecentChattersInterfaceConfig {

    @Bean
    RecentChattersInterface recentChattersInterfaceBean(Map<String, Long> recentChatters) {
        return new RecentChatters(recentChatters);
    }
}
