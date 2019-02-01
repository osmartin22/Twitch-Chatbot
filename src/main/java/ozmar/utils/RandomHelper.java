package ozmar.utils;

import java.util.Random;

public class RandomHelper {

    private static Random random = new Random();

    private RandomHelper() {

    }

    /**
     * Returns a random number from 0 to given number (inclusive)
     * Allows for negative random numbers
     *
     * @param num range of random number (inclusive)
     * @return int
     */
    public static int getRandomNumber(int num) {
        int roll;

        if (num < 0) {
            num *= -1;
            roll = random.nextInt(num - 1) * -1;

        } else {
            roll = random.nextInt(num + 1);
        }

        return roll;
    }

    /**
     * Returns a random number from 1 to given number (inclusive)
     *
     * @param num range of random number (inclusive)
     * @return int
     */
    public static int getRandomNumberNotZero(int num) {
        return random.nextInt(num + 1) + 1;
    }
}
