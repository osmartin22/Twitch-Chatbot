package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.ChatTable;
import ozmar.database.interfaces.ChatTableInterface;

@Configuration
public class ChatTableInterfaceConfig {

    @Bean
    public ChatTableInterface chatTableInterfaceBean() {
        return new ChatTable();
    }
}
