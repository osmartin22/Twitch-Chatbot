package ozmar.commands.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.LootBox;
import ozmar.commands.interfaces.LootBoxInterface;

@Configuration
public class LootBoxInterfaceConfig {

    @Bean
    LootBoxInterface lootBoxInterfaceBean(MessageSource source) {
        return new LootBox(source);
    }
}
