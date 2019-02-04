package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.CommandList;
import ozmar.features.OnCommandReceived;

@Configuration
public class OnCommandReceivedConfig {

    @Bean
    public OnCommandReceived onCommandReceivedBean(CommandList commandList) {
        return new OnCommandReceived(commandList);
    }
}
