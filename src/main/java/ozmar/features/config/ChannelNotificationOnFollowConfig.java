package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.ChannelNotificationOnFollow;

@Configuration
public class ChannelNotificationOnFollowConfig {

    @Bean
    ChannelNotificationOnFollow channelNotificationOnFollowBean() {
        return new ChannelNotificationOnFollow();
    }
}
