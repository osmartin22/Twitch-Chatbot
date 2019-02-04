package ozmar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.OnCommandReceived;

@Configuration
public class BotConfig {

    @Bean
    public Bot botBean(@Autowired OnCommandReceived onCommandReceived) {
        return new Bot(onCommandReceived);
    }

}
