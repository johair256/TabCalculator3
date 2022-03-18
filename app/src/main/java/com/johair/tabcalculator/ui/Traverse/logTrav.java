package com.johair.tabcalculator.ui.Traverse;

import java.text.DecimalFormat;

public class logTrav {
    // Logarithmic Traverse Sizing Conditional
    public static double[] logArray = {};
    public static double[] threeHoleArray = {0.064,0.5,0.936};
    public static double[] fiveHoleArray = {0.074,0.288,0.5,0.712,0.926};
    public static double[] sixHoleArray = {0.061,0.235,0.437,0.563,0.765,0.939};
    public static double[] sevenHoleArray = {0.053,0.203,0.366,0.5,0.634,0.797,0.947};

    public static double[] logTravPoints(double ductSize) {
        if (0 <= ductSize && ductSize <= 15) {
            logArray = new double[threeHoleArray.length];

            for (int i = 0;i <= threeHoleArray.length - 1;i++) {
                logArray[i] = ductSize * threeHoleArray[i];
            }
        } else if (15 < ductSize && ductSize < 30) {
            logArray = new double[fiveHoleArray.length];

            for (int i = 0;i <= fiveHoleArray.length - 1;i++) {
                logArray[i] = ductSize * fiveHoleArray[i];
            }
        } else if (30 <= ductSize && ductSize <= 63) {
            logArray = new double[sixHoleArray.length];

            for (int i = 0;i <= sixHoleArray.length - 1;i++) {
                logArray[i] = ductSize * sixHoleArray[i];
            }
        } else if (63 < ductSize) {
            logArray = new double[sevenHoleArray.length];

            for (int i = 0;i <= sevenHoleArray.length - 1;i++) {
                logArray[i] = ductSize * sevenHoleArray[i];
            }
        }
        return logArray;
    }
}