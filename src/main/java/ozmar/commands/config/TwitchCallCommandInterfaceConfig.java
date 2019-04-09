package ozmar.commands.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.TwitchCallCommand;
import ozmar.commands.interfaces.TwitchCallCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;

@Configuration
public class TwitchCallCommandInterfaceConfig {

    @Bean
    TwitchCallCommandInterface twitchCallCommandInterfaceBean(MessageSource messageSource,
                                                              DatabaseHandlerInterface databaseHandlerInterface
    ) {
        return new TwitchCallCommand(messageSource, databaseHandlerInterface);
    }
}
