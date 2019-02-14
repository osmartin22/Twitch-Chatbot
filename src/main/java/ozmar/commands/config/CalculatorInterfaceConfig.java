package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Calculator;
import ozmar.commands.interfaces.CalculatorInterface;

@Configuration
public class CalculatorInterfaceConfig {
    @Bean
    public CalculatorInterface calculatorInterfaceBean() {
        return new Calculator();
    }
}
