package com.johair.tabcalculator;

public class rectTrav {
    public static int lengthValue;

    public static int[] rectTravPoints(double x) {
        if (0 <= x && x <= 4) {
            lengthValue = 2;
        } else if (5 <= x && x <= 15) {
            lengthValue = 3;
        } else if (16 <= x && x <= 24) {
            lengthValue = 4;
        } else if (25 <= x && x <= 35) {
            lengthValue = 5;
        } else if (36 <= x && x <= 48) {
            lengthValue = 6;
        } else if (49 <= x && x <= 63) {
            lengthValue = 7;
        } else if (64 <= x && x <= 80) {
            lengthValue = 8;
        } else if (81 <= x && x <= 99) {
            lengthValue = 9;
        } else if (x >= 100) {
            lengthValue = 10;
        }

        int[] rectArray = new int[lengthValue];

        for (int i = 0;i <= lengthValue - 1;i++) {
            rectArray[i] = (int) ((x / lengthValue) * i + (x / lengthValue / 2));
        }

        return rectArray;
    }
}
