package ozmar.commands;

import ozmar.utils.RandomHelper;

public class Dice {

    private int sides;

    public Dice() {

    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int rollPosDie() {
        if (sides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(1, sides);
    }

    public int rollNegDie() {
        if (sides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(sides, -1);
    }

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
