package commandsTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.commands.DiceRoller;
import ozmar.commands.interfaces.DiceRollerInterface;

public class DiceRollerTest {

    private static DiceRollerInterface dice;

    @BeforeClass
    public static void setUp() {
        dice = new DiceRoller();
    }

    @Test
    public void zeroSidedDie() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(0, 10);
            Assert.assertNotNull(result);
            Assert.assertEquals(0, (int) result);
        }
    }

    @Test
    public void zeroDieRoll() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(100, 0);
            Assert.assertNotNull(result);
            Assert.assertEquals(0, (int) result);
        }
    }

    @Test
    public void allZeroRoll() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(0, 0);
            Assert.assertNotNull(result);
            Assert.assertEquals(0, (int) result);
        }
    }

    @Test
    public void posRoll() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(100, 10);
            Assert.assertNotNull(result);
            Assert.assertTrue(result > 0);
        }
    }

    @Test
    public void negRoll() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(-20, 10);
            Assert.assertNotNull(result);
            Assert.assertTrue(result < 0);
        }
    }

    @Test
    public void diceCountLimitEnforced() {
        for (int i = 0; i < 1000; i++) {
            Integer result = dice.roll(1, 1000);
            Assert.assertNotNull(result);
            Assert.assertEquals(100, (int) result);
        }
    }

    @Test
    public void validStringInputToRoll() {
        Integer result;
        for (int i = 0; i < 1000; i++) {
            result = dice.roll("1", "1000");
            Assert.assertNotNull(result);
            Assert.assertEquals(100, (int) result);

            result = dice.roll("1", "10");
            Assert.assertNotNull(result);
            Assert.assertEquals(10, (int) result);
        }
    }

    // Should default to dieSides = 20, dieCount = 1
    @Test
    public void invalidStringInputToRoll() {
        Integer result;
        for (int i = 0; i < 1000; i++) {
            result = dice.roll("1", "");
            Assert.assertNotNull(result);
            Assert.assertEquals(1, (int) result);

            result = dice.roll("", "1");
            Assert.assertNotNull(result);
            Assert.assertTrue(result <= 20);
            Assert.assertTrue(result > 0);

            result = dice.roll("", "");
            Assert.assertNotNull(result);
            Assert.assertTrue(result <= 20);
            Assert.assertTrue(result > 0);
        }
    }

}
