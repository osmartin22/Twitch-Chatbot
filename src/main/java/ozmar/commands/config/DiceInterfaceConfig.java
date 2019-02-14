package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Dice;
import ozmar.commands.interfaces.DiceInterface;

@Configuration
public class DiceInterfaceConfig {

    @Bean
    public DiceInterface diceInterfaceBean() {
        return new Dice();
    }
}
