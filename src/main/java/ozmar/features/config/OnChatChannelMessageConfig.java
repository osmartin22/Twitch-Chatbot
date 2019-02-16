package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.buffer.interfaces.RecentChattersInterface;
import ozmar.buffer.interfaces.WordCountBufferInterface;
import ozmar.features.OnChatChannelMessage;

@Configuration
public class OnChatChannelMessageConfig {

    @Bean
    OnChatChannelMessage onChatChannelMessageBean(WordCountBufferInterface wordCountBuffer,
                                                  ChatDataBufferInterface chatDataBuffer,
                                                  RecentChattersInterface recentChatters) {
        return new OnChatChannelMessage(wordCountBuffer, chatDataBuffer, recentChatters);
    }
}
