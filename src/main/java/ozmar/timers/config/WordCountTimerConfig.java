package ozmar.timers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.ChatDataBuffer;
import ozmar.buffer.WordCountBuffer;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.timers.WordCountTimer;

@Configuration
public class WordCountTimerConfig {

    @Bean
    public WordCountTimer wordCountTimerBean(DatabaseHandlerInterface db,
                                             WordCountBuffer wordCountBuffer, ChatDataBuffer chatDataBuffer) {
        return new WordCountTimer(db, wordCountBuffer, chatDataBuffer);
    }
}
