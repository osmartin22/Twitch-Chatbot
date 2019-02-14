package commandsTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.commands.Calculator;
import ozmar.commands.interfaces.CalculatorInterface;

public class CalculatorTest {

    private static CalculatorInterface calculator;

    @BeforeClass
    public static void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void onNumber() {

    }

    @Test
    public void addition() {
        calculator.setOperation("4+3");
        Assert.assertEquals(4 + 3, calculator.parse(), 0);

        calculator.setOperation("2 + 100.05 + 5 + 8.7");
        Assert.assertEquals(2 + 100.05 + 5 + 8.7, calculator.parse(), 0);

        calculator.setOperation("+4 + + + + 3");
        Assert.assertEquals(4 + 3, calculator.parse(), 0);
    }

    @Test
    public void subtraction() {
        calculator.setOperation("4-3");
        Assert.assertEquals(4 - 3, calculator.parse(), 0);

        calculator.setOperation("100.10 - 50.6 - 8.003");
        Assert.assertEquals(100.10 - 50.6 - 8.003, calculator.parse(), 0);

        calculator.setOperation("4 - 3 - - - 2");
        Assert.assertEquals(4 - 3 - 2, calculator.parse(), 0);
    }

    @Test
    public void multiplication() {
        calculator.setOperation("3*7");
        Assert.assertEquals(3 * 7, calculator.parse(), 0);

        calculator.setOperation("11.2*3.5*100");
        Assert.assertEquals(11.2 * 3.5 * 100, calculator.parse(), 0);
    }

    @Test
    public void division() {
        calculator.setOperation("3/3");
        Assert.assertEquals(1, calculator.parse(), 0);

        calculator.setOperation("100/3/45/200");
        Assert.assertEquals(100.0 / 3.0 / 45.0 / 200.0, calculator.parse(), 0);
    }

    @Test
    public void negativeNumbers() {
        calculator.setOperation("-3 + -6 - -2");
        Assert.assertEquals(-3 + (-6) - (-2), calculator.parse(), 0);

        calculator.setOperation("-3 * -2 / -10 * -4");
        Assert.assertEquals((-3.) * (-2.) / (-10.) * (-4.0), calculator.parse(), 0);
    }

    @Test
    public void sin() {
        calculator.setOperation("sin(90)");
        Assert.assertEquals(Math.sin(Math.toRadians(90)), calculator.parse(), 0);

        calculator.setOperation("sin(45)");
        Assert.assertEquals(Math.sin(Math.toRadians(45)), calculator.parse(), 0);

        calculator.setOperation("sin(180)");
        Assert.assertEquals(Math.sin(Math.toRadians(180)), calculator.parse(), 0);
    }

    @Test
    public void cos() {
        calculator.setOperation("cos(90)");
        Assert.assertEquals(Math.cos(Math.toRadians(90)), calculator.parse(), 0);

        calculator.setOperation("cos(45)");
        Assert.assertEquals(Math.cos(Math.toRadians(45)), calculator.parse(), 0);

        calculator.setOperation("cos(180)");
        Assert.assertEquals(Math.cos(Math.toRadians(180)), calculator.parse(), 0);
    }

    @Test
    public void tan() {
        calculator.setOperation("tan(90)");
        Assert.assertEquals(Math.tan(Math.toRadians(90)), calculator.parse(), 0);

        calculator.setOperation("tan(45)");
        Assert.assertEquals(Math.tan(Math.toRadians(45)), calculator.parse(), 0);

        calculator.setOperation("tan(180)");
        Assert.assertEquals(Math.tan(Math.toRadians(180)), calculator.parse(), 0);
    }

    @Test
    public void parenthesis() {
        calculator.setOperation("(3 + 2) *(9) / (2+(3-1))");
        Assert.assertEquals((3.0 + 2.0) * (9.0) / (2.0 + (3.0 - 1.0)), calculator.parse(), 0);

        calculator.setOperation("((((9)) + 2)) * 3");
        Assert.assertEquals(((((9)) + 2)) * 3, calculator.parse(), 0);
    }

    @Test
    public void exponent() {
        calculator.setOperation("2^3");
        Assert.assertEquals(Math.pow(2, 3), calculator.parse(), 0);

        calculator.setOperation("4 ^ .003");
        Assert.assertEquals(Math.pow(4, .003), calculator.parse(), 0);
    }

    @Test
    public void squareRoot() {
        calculator.setOperation("sqrt4");
        Assert.assertEquals(Math.sqrt(4), calculator.parse(), 0);

        calculator.setOperation("sqrt4*2");
        Assert.assertEquals(Math.sqrt(4) * 2, calculator.parse(), 0);

        calculator.setOperation("sqrt(4*2)");
        Assert.assertEquals(Math.sqrt(4 * 2), calculator.parse(), 0);
    }

    @Test
    public void divideByZero() {
        calculator.setOperation("4 * 2 + 3 * 100 / 0 + 55");
        Assert.assertEquals(1.0 / 0, calculator.parse(), 0);
    }

    @Test
    public void overFlow() {

    }
}
