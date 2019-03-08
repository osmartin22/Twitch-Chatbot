package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.interfaces.HandleCommandInterface;
import ozmar.features.OnCommandReceived;

import java.util.ArrayDeque;

@Configuration
public class OnCommandReceivedConfig {

    @Bean
    public OnCommandReceived onCommandReceivedBean(HandleCommandInterface handleCommand) {
        return new OnCommandReceived(handleCommand, new ArrayDeque<>(10));
    }
}
