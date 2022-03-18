package com.johair.tabcalculator.ui.Traverse;

import java.text.DecimalFormat;

public class roundTrav {
    // Round Traverse Sizing Conditional
    public static String[] roundArray = {};
    public static double[] sixHoleArray = {0.032, 0.135, 0.321, 0.679, 0.865, 0.968};
    public static double[] tenHoleArray = {0.019,0.077,0.153,0.217,0.361,0.639,0.783,0.847,0.923,0.981};
    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    public static String[] roundTravPoints(double ductSize) {
        if (0 <= ductSize && ductSize <= 10) {
            String[] roundArray = new String[sixHoleArray.length];

            for (int i = 0;i <= sixHoleArray.length - 1;i++) {
                roundArray[i] = df3.format(ductSize * sixHoleArray[i]);
            }
        } else if (ductSize > 10) {
            String[] roundArray = new String[tenHoleArray.length];

            for (int i = 0;i <= tenHoleArray.length - 1;i++) {
                roundArray[i] = df3.format(ductSize * tenHoleArray[i]);
            }
        }

        return roundArray;
    }
}