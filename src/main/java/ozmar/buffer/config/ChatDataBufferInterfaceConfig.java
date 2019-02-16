package ozmar.buffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.ChatDataBuffer;
import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.user.ChatUser;

import java.util.Map;

@Configuration
public class ChatDataBufferInterfaceConfig {

    @Bean
    ChatDataBufferInterface chatDataBufferInterfaceBean(Map<Long, ChatUser> chatData1,
                                                        Map<Long, ChatUser> chatData2) {
        return new ChatDataBuffer(chatData1, chatData2);
    }
}
