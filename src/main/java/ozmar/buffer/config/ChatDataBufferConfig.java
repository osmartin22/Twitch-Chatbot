package ozmar.buffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.ChatDataBuffer;

@Configuration
public class ChatDataBufferConfig {

    @Bean
    ChatDataBuffer chatDataBufferBean() {
        return new ChatDataBuffer();
    }
}
