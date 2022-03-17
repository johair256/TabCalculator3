package com.johair.tabcalculator.ui.Traverse;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.johair.tabcalculator.Util;
import com.johair.tabcalculator.databinding.FragmentTraverseBinding;
import com.johair.tabcalculator.rectTrav;

import java.text.DecimalFormat;

public class TraverseFragment extends Fragment {

    private FragmentTraverseBinding binding;
    String[] traverseMethodOptions = {"Rectangle","Round","Logarithmic"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTraverseBinding.inflate(inflater, container, false);

        binding.calcButton.setOnClickListener(view -> Calculate());

        binding.clearButton.setOnClickListener(view -> Reset());

        Spinner traverseMethod = binding.methodSpinner;
        ArrayAdapter<String> ad = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, traverseMethodOptions);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        traverseMethod.setAdapter(ad);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    double measurement;
    int[] displayArray;

    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    // Retrieve user input data
    EditText inputMeasurement;
    LinearLayout printLayout;
    Spinner methodSpinner;

    public void Calculate() {
        //Close Keyboard
        if (getActivity() != null) {
            Util.hideKeyboard(getActivity());
        }

        // Attempts to retrieve value from user input; if there is no value entered, defaults to 0
        inputMeasurement = binding.inputMeasurement;
        printLayout = binding.printLayout;
        methodSpinner = binding.methodSpinner;

        try {
            measurement = Double.parseDouble(inputMeasurement.getText().toString());
        }
        catch (NumberFormatException ex) {
            measurement = 0;
        }

        switch (methodSpinner.getSelectedItemPosition()) {
            case 0:
                displayArray = rectTrav.rectTravPoints(measurement);
                break;
            case 1:
                displayArray = rectTrav.rectTravPoints(measurement + 10);
                break;
            case 2:
                displayArray = rectTrav.rectTravPoints(measurement + 20);
                break;
        }

        // Clear Linear Layout
        printLayout.removeAllViews();

        // Display traverse points
        if (measurement > 0) {   // Prevents function from outputting zeros
            for (int i = 0;i <= displayArray.length - 1;i++) {
                TextView measurementView = new TextView(getActivity());
                measurementView.setText(df3.format(displayArray[i]));
                measurementView.setLayoutParams(new LinearLayout.LayoutParams(100, 60));
                measurementView.setGravity(Gravity.CENTER);
                printLayout.addView(measurementView);
                printLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
            }
        }
    }

    public void Reset() {
        if (inputMeasurement != null) {
            //Close Keyboard
            if (getActivity() != null) {
                Util.hideKeyboard(getActivity());
            }

            // Clears all views
            inputMeasurement.getText().clear();
            printLayout.removeAllViews();
        }
    }
}