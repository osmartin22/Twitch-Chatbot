package ozmar.commands.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.TwitchStockCommand;
import ozmar.commands.interfaces.TwitchStockCommandInterface;

@Configuration
public class TwitchStockCommandInterfaceConfig {

    @Bean
    TwitchStockCommandInterface twitchStockCommandInterfaceBean(MessageSource messageSource) {
        return new TwitchStockCommand(messageSource);
    }
}
