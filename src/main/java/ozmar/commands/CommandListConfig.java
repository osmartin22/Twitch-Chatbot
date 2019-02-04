package ozmar.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandListConfig {

    @Bean
    public CommandList commandListBean(@Autowired Calculator calculator) {
        return new CommandList(calculator);
    }

}
