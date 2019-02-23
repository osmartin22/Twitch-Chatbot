package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.DiceRoller;
import ozmar.commands.interfaces.DiceRollerInterface;

@Configuration
public class DiceRollerInterfaceConfig {

    @Bean
    public DiceRollerInterface diceRollerInterfaceBean() {
        return new DiceRoller();
    }
}
