package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.ChannelNotificationOnGiftSubscription;

@Configuration
public class ChannelNotificationOnGiftSubscriptionConfig {

    @Bean
    ChannelNotificationOnGiftSubscription channelNotificationOnGiftSubscriptionBean() {
        return new ChannelNotificationOnGiftSubscription();
    }
}
