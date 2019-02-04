package ozmar.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Bean
    public Calculator calculatorBean() {
        return new Calculator();
    }

}
