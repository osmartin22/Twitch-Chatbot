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
        return RandomHelper.getRandNumInRange(1, sides);
    }

    public int rollNegDie() {
        return RandomHelper.getRandNumInRange(sides, -1);
    }

    public int rollNDie(int dieCount) {
        int roll = 0;

        if (sides < 0) {
            for (int i = 0; i < dieCount; i++) {
                roll += rollNegDie();
            }

        } else if (sides > 0) {
            for (int i = 0; i < dieCount; i++) {
                roll += rollPosDie();
            }

        } else {
            return 0;
        }

        return roll;
    }
}
