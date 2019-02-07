package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Dice;

@Configuration
public class DiceConfig {

    @Bean
    public Dice diceBean() {
        return new Dice();
    }
}
