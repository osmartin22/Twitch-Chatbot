package ozmar.buffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.WordCountBuffer;

@Configuration
public class WordCountBufferConfig {

    @Bean
    WordCountBuffer wordCountBufferBean() {
        return new WordCountBuffer();
    }
}
