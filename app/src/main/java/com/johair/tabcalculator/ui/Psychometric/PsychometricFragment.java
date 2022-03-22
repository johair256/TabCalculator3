package com.johair.tabcalculator.ui.Psychometric;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.johair.tabcalculator.Util;
import com.johair.tabcalculator.databinding.FragmentPsychometricBinding;

import java.text.DecimalFormat;

public class PsychometricFragment extends Fragment {

    private FragmentPsychometricBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPsychometricBinding.inflate(inflater, container, false);

        binding.psychEnter.setOnClickListener(view -> printOutput());

        binding.psychClear.setOnClickListener(view -> resetPsych());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Retrieve Values
    EditText inputAltitude;
    EditText inputDryBulb;
    EditText inputWetBulb;
    TextView outputEnthalpy;
    public double altitude;
    public double dryBulbTemp;
    public double wetBulbTemp;
    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    // Print Enthalpy
    public void printOutput() {
        //Close Keyboard
        if (getActivity() != null) {
            Util.hideKeyboard(getActivity());
        }

        //Toast.makeText(getActivity(),df3.format(correctionFactor()),Toast.LENGTH_LONG).show();
        outputEnthalpy = binding.outputEnthalpy;
        outputEnthalpy.setText(df3.format(calcEnthalpy()));
    }

    public double calcEnthalpy() {
        // Attempts to retrieve value from user input; if there is no value entered, defaults to 0
        // Altitude in ft
        inputAltitude = binding.inputAltitude;
        try {
            altitude = Double.parseDouble(inputAltitude.getText().toString());
        }
        catch (NumberFormatException ex) {
            altitude = 0;
        }

        // Dry Bulb Temp in F
        inputDryBulb = binding.inputDryBulb;
        try {
            dryBulbTemp = Double.parseDouble(inputDryBulb.getText().toString());
        }
        catch (NumberFormatException ex) {
            dryBulbTemp = 0;
        }

        // Wet Bulb Temp in F
        inputWetBulb = binding.inputWetBulb;
        try {
            wetBulbTemp = Double.parseDouble(inputWetBulb.getText().toString());
        }
        catch (NumberFormatException ex) {
            wetBulbTemp = 0;
        }

        // Temps in Rankine
        double dryBulbTempR = dryBulbTemp + 459.67;
        double wetBulbTempR = wetBulbTemp + 459.67;

        // Water Vapor Saturation Pressures
        //double dryBulbPws = pressureSaturated(dryBulbTempR);
        double wetBulbPws = pressureSaturated(wetBulbTempR);

        // Saturated Humidity Ratios
        //double dryBulbWs = humiditySaturated(dryBulbPws);
        double wetBulbWs = humiditySaturated(wetBulbPws);

        // Specific heat at dry Bulb Temp
        double  a1 = -2.0921943e-14,
                a2 = 2.5588383e-11,
                a3 = 1.2900877e-8,
                a4 = 5.8045267e-6,
                a5 = 0.23955919;
        double Cp = a1*Math.pow(dryBulbTemp,4) + a2*Math.pow(dryBulbTemp,3) + a3*Math.pow(dryBulbTemp,2) + a4*dryBulbTemp + a5;

        // Humidity Ratio
        double humidityRatio = ((1093-0.556*wetBulbTemp)*wetBulbWs-Cp*(dryBulbTemp-wetBulbTemp))/(1093+0.444*dryBulbTemp-wetBulbTemp);

        // Enthalpy
        return Cp*dryBulbTemp+humidityRatio*(1061+0.444*dryBulbTemp);
    }

    // Water Vapor Saturation Pressure
    public double pressureSaturated(double tempR) {
        double  c1 = -1.0440397e4,
                c2 = -1.1294650e1,
                c3 = -2.7022355e-2,
                c4 = 1.2890360e-5,
                c5 = -2.4780681e-9,
                c6 = 6.5459673e0;

        return 1.004*Math.exp(c1/tempR + c2 + c3*tempR + c4*Math.pow(tempR,2) + c5*Math.pow(tempR,3) + c6*Math.log(tempR));
    }

    // Saturated Humidity Ratio
    public double humiditySaturated(double pressure) {
        double presAtAltitude = 14.6959 * Math.pow(1-6.8753e-6*altitude,5.2559);

        return (0.62198*pressure)/(presAtAltitude-pressure);
    }

    public void resetPsych() {
        if (inputDryBulb != null) {
            //Close Keyboard
            if (getActivity() != null) {
                Util.hideKeyboard(getActivity());
            }

            // Clears all views
            inputAltitude.getText().clear();
            inputDryBulb.getText().clear();
            inputWetBulb.getText().clear();
            outputEnthalpy.setText("");
        }
    }
}