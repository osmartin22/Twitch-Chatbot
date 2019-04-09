package ozmar.commands.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.CatchPoke;
import ozmar.commands.interfaces.CatchPokeInterface;

@Configuration
public class CatchPokeInterfacesConfig {

    @Bean
    CatchPokeInterface catchPokeInterfaceBean(MessageSource messageSource) {
        return new CatchPoke(messageSource);
    }
}
