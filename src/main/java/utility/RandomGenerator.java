package main.java.utility;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public static int getRandomNumber(int origin, int bound) {
        return random.nextInt(origin, bound);
    }
}
