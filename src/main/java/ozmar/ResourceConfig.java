package ozmar;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ResourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("file:/TwitchBotFiles/WEB-INF/cmd", "file:/TwitchBotFiles/WEB-INF/poke");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setConcurrentRefresh(true);
        // Can either make a command to manually reload property files or use
        // setCacheSeconds to attempt a refresh every X seconds
        messageSource.setCacheSeconds(10);
        return messageSource;
    }
}
