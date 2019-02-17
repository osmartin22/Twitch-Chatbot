package ozmar.database.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.database.dao.WordCountDao;
import ozmar.database.tables.interfaces.WordCountTableInterface;

@Configuration
public class WordCountDaoConfig {

    @Bean
    public WordCountDao wordCountDaoBean(WordCountTableInterface wordCountTable) {
        return new WordCountDao(wordCountTable);
    }
}
