package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.OnChatChannelMessage;

@Configuration
public class OnChatChannelMessageConfig {

    @Bean
    OnChatChannelMessage onChatChannelMessageBean() {
        return new OnChatChannelMessage();
    }
}
