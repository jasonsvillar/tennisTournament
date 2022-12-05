package com.jasonvillar.tennisTournament.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
    public static double getRandomNumber(double min, double max) {
        double number = (Math.random() * (max - min)) + min;
        BigDecimal bdToRound = new BigDecimal(number).setScale(2, RoundingMode.HALF_DOWN);
        return bdToRound.doubleValue();
    }
}
