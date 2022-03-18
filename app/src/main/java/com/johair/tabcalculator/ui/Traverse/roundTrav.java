package com.johair.tabcalculator.ui.Traverse;

import java.text.DecimalFormat;

public class roundTrav {
    // Round Traverse Sizing Conditional
    public static double[] roundArray = {};
    public static double[] sixHoleArray = {0.032, 0.135, 0.321, 0.679, 0.865, 0.968};
    public static double[] tenHoleArray = {0.019,0.077,0.153,0.217,0.361,0.639,0.783,0.847,0.923,0.981};

    public static double[] roundTravPoints(double ductSize) {
        if (0 <= ductSize && ductSize <= 10) {
            roundArray = new double[sixHoleArray.length];

            for (int i = 0;i <= sixHoleArray.length - 1;i++) {
                roundArray[i] = ductSize * sixHoleArray[i];
            }
        } else if (ductSize > 10) {
            roundArray = new double[tenHoleArray.length];

            for (int i = 0;i <= tenHoleArray.length - 1;i++) {
                roundArray[i] = ductSize * tenHoleArray[i];
            }
        }
        return roundArray;
    }
}