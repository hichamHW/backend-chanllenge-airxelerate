package com.airxelerate.util;

import java.util.Random;

public class GeneratorUtil {
    private static final String[] CARRIER_CODES = {"AA", "DL", "UA", "WN", "AS"};

    public static String getRandomCarrierCode() {
        Random random = new Random();
        int index = random.nextInt(CARRIER_CODES.length);
        return CARRIER_CODES[index];
    }
    public static long generateRandom4DigitNumber() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
}
