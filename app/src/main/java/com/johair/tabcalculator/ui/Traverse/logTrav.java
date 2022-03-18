package com.johair.tabcalculator.ui.Traverse;

import java.text.DecimalFormat;

public class logTrav {
    // Logarithmic Traverse Sizing Conditional
    public static String[] logArray = {};
    public static double[] threeHoleArray = {0.064,0.5,0.936};
    public static double[] fiveHoleArray = {0.074,0.288,0.5,0.712,0.926};
    public static double[] sixHoleArray = {0.061,0.235,0.437,0.563,0.765,0.939};
    public static double[] sevenHoleArray = {0.053,0.203,0.366,0.5,0.634,0.797,0.947};
    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    public static String[] logTravPoints(double ductSize) {
        if (0 <= ductSize && ductSize <= 15) {
            for (int i = 0;i <= threeHoleArray.length - 1;i++) {
                logArray[i] = df3.format(ductSize * threeHoleArray[i]);
            }
        } else if (15 < ductSize && ductSize < 30) {
            for (int i = 0;i <= fiveHoleArray.length - 1;i++) {
                logArray[i] = df3.format(ductSize * fiveHoleArray[i]);
            }
        } else if (30 <= ductSize && ductSize <= 63) {
            for (int i = 0;i <= sixHoleArray.length - 1;i++) {
                logArray[i] = df3.format(ductSize * sixHoleArray[i]);
            }
        } else if (63 < ductSize) {
            for (int i = 0;i <= sevenHoleArray.length - 1;i++) {
                logArray[i] = df3.format(ductSize * sevenHoleArray[i]);
            }
        }

        return logArray;
    }
}
