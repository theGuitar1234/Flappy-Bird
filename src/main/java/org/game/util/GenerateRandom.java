package org.game.util;

import java.util.Random;

public class GenerateRandom {

    private static final Random random = new Random(); 

    public static int generateRandom(int min, int max) {
        return random.nextInt(min, max);
    }
}
