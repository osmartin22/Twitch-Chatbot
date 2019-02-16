package ozmar.timers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.buffer.interfaces.WordCountBufferInterface;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.timers.WordCountTimer;

@Configuration
public class WordCountTimerConfig {

    @Bean
    public WordCountTimer wordCountTimerBean(DatabaseHandlerInterface db,
                                             WordCountBufferInterface wordCountBuffer,
                                             ChatDataBufferInterface chatDataBuffer) {
        return new WordCountTimer(db, wordCountBuffer, chatDataBuffer);
    }
}
