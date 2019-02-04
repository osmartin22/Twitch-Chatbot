package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.ChannelNotificationOnCheer;

@Configuration
public class ChannelNotificationOnCheerConfig {

    @Bean
    ChannelNotificationOnCheer channelNotificationOnCheerBean() {
        return new ChannelNotificationOnCheer();
    }
}
