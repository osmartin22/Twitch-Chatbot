package ozmar.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.CommandList;

@Configuration
public class OnCommandReceivedConfig {

    @Bean
    public OnCommandReceived onCommandReceivedBean(@Autowired CommandList commandList) {
        return new OnCommandReceived(commandList);
    }
}
