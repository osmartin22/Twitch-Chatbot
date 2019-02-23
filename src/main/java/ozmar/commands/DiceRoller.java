package ozmar.commands;

import ozmar.commands.interfaces.DiceRollerInterface;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiceRoller implements DiceRollerInterface {

    public DiceRoller() {

    }

    @Nullable
    @Override
    public Integer roll(int dieSides, int dieCount) {
        if (dieSides == 0 || dieCount == 0) {
            return 0;
        }

        if (dieCount > 100) {
            dieCount = 100;
        }

        Integer roll = 0;
        if (dieSides < 0) {
            for (int i = 0; i < dieCount; i++) {
                try {
                    roll = Math.addExact(roll, rollNegDie(dieSides));
                } catch (ArithmeticException e) {
                    System.out.println("Number too small: " + e.getMessage());
                    roll = null;
                    break;
                }
            }

        } else {
            for (int i = 0; i < dieCount; i++) {
                try {
                    roll = Math.addExact(roll, rollPosDie(dieSides));
                } catch (ArithmeticException e) {
                    System.out.println("Number too big: " + e.getMessage());
                    roll = null;
                    break;
                }
            }
        }

        return roll;
    }

    @Nullable
    @Override
    public Integer roll(@Nonnull String dieSides, @Nonnull String dieCount) {
        int sides = 20;
        int count = 1;
        try {
            sides = Integer.parseInt(dieSides);
            if (!dieCount.isEmpty()) {
                count = Integer.parseInt(dieCount);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input was incorrect, defaulting to preset value: " + e.getMessage());
        }

        return roll(sides, count);
    }

    @Override
    public int rollPosDie(int dieSides) {
        if (dieSides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(1, dieSides);
    }

    @Override
    public int rollNegDie(int dieSides) {
        if (dieSides == 0) {
            return 0;
        }
        return RandomHelper.getRandNumInRange(dieSides, -1);
    }
}
