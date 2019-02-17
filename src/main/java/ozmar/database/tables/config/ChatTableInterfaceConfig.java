package ozmar.database.tables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.tables.ChatTable;
import ozmar.database.tables.interfaces.ChatTableInterface;

@Configuration
public class ChatTableInterfaceConfig {

    @Bean
    public ChatTableInterface chatTableInterfaceBean() {
        return new ChatTable();
    }
}
