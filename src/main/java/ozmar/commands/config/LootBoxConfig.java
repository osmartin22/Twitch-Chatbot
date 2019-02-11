package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.LootBox;

@Configuration
public class LootBoxConfig {

    @Bean
    LootBox lootBoxBean() {
        return new LootBox();
    }
}
