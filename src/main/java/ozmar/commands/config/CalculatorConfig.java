package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Calculator;

@Configuration
public class CalculatorConfig {

    @Bean
    public Calculator calculatorBean() {
        return new Calculator();
    }

}
