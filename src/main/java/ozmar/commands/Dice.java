package ozmar.commands;

import ozmar.commands.interfaces.DiceInterface;
import ozmar.utils.RandomHelper;

public class Dice implements DiceInterface {

    private int sides;

    public Dice() {

    }

    @Override
    public void setSides(int sides) {
        this.sides = sides;
    }

    @Override
    public int rollPosDie() {
        if (sides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(1, sides);
    }

    @Override
    public int rollNegDie() {
        if (sides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(sides, -1);
    }

    @Override
    public Integer rollNDie(int dieCount) {
        int roll = 0;

        if (dieCount > 100) {
            dieCount = 100;
        }

        if (sides < 0) {
            for (int i = 0; i < dieCount; i++) {
                try {
                    roll = Math.addExact(roll, rollNegDie());
                } catch (ArithmeticException e) {
                    System.out.println("Number too small: " + e.getMessage());
                    return null;
                }
            }

        } else if (sides > 0) {
            for (int i = 0; i < dieCount; i++) {
                try {
                    roll = Math.addExact(roll, rollPosDie());
                } catch (ArithmeticException e) {
                    System.out.println("Number too big: " + e.getMessage());
                    return null;
                }
            }

        } else {
            return 0;
        }

        return roll;
    }
}
