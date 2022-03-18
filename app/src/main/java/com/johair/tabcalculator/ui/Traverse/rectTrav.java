package com.johair.tabcalculator.ui.Traverse;

import java.text.DecimalFormat;

public class rectTrav {
    // Rectangle Traverse Sizing Conditional
    public static int lengthValue;
    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    public static double[] rectTravPoints(double ductSize) {
        if (0 <= ductSize && ductSize <= 4) {
            lengthValue = 2;
        } else if (5 <= ductSize && ductSize <= 15) {
            lengthValue = 3;
        } else if (16 <= ductSize && ductSize <= 24) {
            lengthValue = 4;
        } else if (25 <= ductSize && ductSize <= 35) {
            lengthValue = 5;
        } else if (36 <= ductSize && ductSize <= 48) {
            lengthValue = 6;
        } else if (49 <= ductSize && ductSize <= 63) {
            lengthValue = 7;
        } else if (64 <= ductSize && ductSize <= 80) {
            lengthValue = 8;
        } else if (81 <= ductSize && ductSize <= 99) {
            lengthValue = 9;
        } else if (ductSize >= 100) {
            lengthValue = 10;
        }

        double[] rectArray = new double[lengthValue];

        for (int i = 0;i <= lengthValue - 1;i++) {
            rectArray[i] = ((ductSize / lengthValue) * i + (ductSize / lengthValue / 2));
        }

        return rectArray;
    }
}
