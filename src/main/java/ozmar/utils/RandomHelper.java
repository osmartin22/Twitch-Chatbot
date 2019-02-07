package ozmar.utils;

import java.util.Random;

public class RandomHelper {

    private static Random random = new Random();

    private RandomHelper() {

    }

    /**
     * Returns a random number from min to max
     *
     * @param min minimum roll
     * @param max maximum roll
     * @return int
     */
    public static int getRandNumInRange(int min, int max) {
        int num = max - min;
        try {
            num = Math.addExact(num, 1);
        } catch (ArithmeticException e) {
            System.out.println("Using (max-min) since adding will overflow: " + e.getMessage());
        }

        return random.nextInt(num) + min;
    }
}
