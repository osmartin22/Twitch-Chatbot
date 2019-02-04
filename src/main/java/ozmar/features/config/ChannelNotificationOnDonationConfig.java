package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.ChannelNotificationOnDonation;

@Configuration
public class ChannelNotificationOnDonationConfig {

    @Bean
    public ChannelNotificationOnDonation channelNotificationOnDonationBean() {
        return new ChannelNotificationOnDonation();
    }
}
