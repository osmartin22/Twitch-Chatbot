package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.WordCountTable;

@Configuration
public class WordCountTableConfig {

    @Bean
    public WordCountTable wordCountTableBean() {
        return new WordCountTable();
    }
}
