package commandsTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.commands.Dice;
import ozmar.commands.interfaces.DiceInterface;

public class DiceTest {

    private static DiceInterface dice;

    @BeforeClass
    public static void setUp() {
        dice = new Dice();
    }

    @Test
    public void zeroSidedDie() {
        dice.setSides(0);
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(0, dice.rollPosDie());
            Assert.assertEquals(0, dice.rollNegDie());
            Assert.assertEquals(0, (int) dice.rollNDie(10));
        }
    }

    @Test
    public void posRoll() {
        dice.setSides(10);
        for (int i = 0; i < 1000; i++) {
            Assert.assertTrue(dice.rollPosDie() > 0);
            Assert.assertTrue(dice.rollNDie(10) > 0);
        }
    }

    @Test
    public void negRoll() {
        dice.setSides(-10);
        for (int i = 0; i < 1000; i++) {
            Assert.assertTrue(dice.rollNegDie() < 0);
            Assert.assertTrue(dice.rollNDie(10) < 0);
        }
    }

    @Test
    public void diceCountLimitEnforced() {
        dice.setSides(1);
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(100, (int) dice.rollNDie(1000));
        }
    }
}
