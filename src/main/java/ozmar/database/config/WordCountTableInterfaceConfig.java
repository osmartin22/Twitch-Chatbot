package ozmar.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.WordCountTable;
import ozmar.database.interfaces.WordCountTableInterface;

@Configuration
public class WordCountTableInterfaceConfig {

    @Bean
    public WordCountTableInterface wordCountTableInterfaceBean() {
        return new WordCountTable();
    }
}
