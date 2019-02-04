package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.ChatTable;

@Configuration
public class ChatTableConfig {

    @Bean
    public ChatTable chatTableBean() {
        return new ChatTable();
    }
}
