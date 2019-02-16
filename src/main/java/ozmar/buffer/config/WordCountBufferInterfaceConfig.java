package ozmar.buffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.buffer.WordCountBuffer;
import ozmar.buffer.interfaces.WordCountBufferInterface;

import java.util.Map;

@Configuration
public class WordCountBufferInterfaceConfig {

    @Bean
    WordCountBufferInterface wordCountBufferInterfaceBean(Map<String, Integer> wordCountMap1,
                                                          Map<String, Integer> wordCountMap2) {
        return new WordCountBuffer(wordCountMap1, wordCountMap2);
    }
}
