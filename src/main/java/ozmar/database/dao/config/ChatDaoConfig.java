package ozmar.database.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.dao.ChatDao;
import ozmar.database.tables.interfaces.ChatTableInterface;

@Configuration
public class ChatDaoConfig {

    @Bean
    public ChatDao chatDaoBean(ChatTableInterface chatTable) {
        return new ChatDao(chatTable);
    }
}
