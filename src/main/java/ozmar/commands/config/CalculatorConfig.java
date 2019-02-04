package ozmar.commands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.commands.Calculator;
import ozmar.enums.Operator;

import java.util.Stack;

@Configuration
public class CalculatorConfig {

    @Bean
    public Calculator calculatorBean() {
        Stack<Double> numStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<>();
        return new Calculator(numStack, operatorStack);
    }

}
