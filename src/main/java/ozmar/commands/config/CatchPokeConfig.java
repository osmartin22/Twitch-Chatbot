package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.CatchPoke;

@Configuration
public class CatchPokeConfig {

    @Bean
    CatchPoke catchPokeBean() {
        return new CatchPoke();
    }
}
