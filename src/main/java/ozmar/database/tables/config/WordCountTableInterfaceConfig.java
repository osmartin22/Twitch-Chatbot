package ozmar.database.tables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.tables.WordCountTable;
import ozmar.database.tables.interfaces.WordCountTableInterface;

@Configuration
public class WordCountTableInterfaceConfig {

    @Bean
    public WordCountTableInterface wordCountTableInterfaceBean() {
        return new WordCountTable();
    }
}
