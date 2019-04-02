package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.OnPrivateMessageReceived;

@Configuration
public class OnPrivateMessageReceivedConfig {

    @Bean
    public OnPrivateMessageReceived onPrivateMessageReceivedBean() {
        return new OnPrivateMessageReceived();
    }

}
