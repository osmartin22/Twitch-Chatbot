package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.ChannelNotificationOnSubscription;

@Configuration
public class ChannelNotificationOnSubscriptionConfig {

    @Bean
    ChannelNotificationOnSubscription channelNotificationOnSubscriptionBean() {
        return new ChannelNotificationOnSubscription();
    }
}
