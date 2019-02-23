package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.TwitchCalls;
import ozmar.commands.interfaces.TwitchCallsInterface;

@Configuration
public class TwitchCallsInterfaceConfig {

    @Bean
    TwitchCallsInterface twitchCallsInterfaceBean() {
        return new TwitchCalls();
    }
}
