package com.johair.tabcalculator.ui.Traverse;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.johair.tabcalculator.R;
import com.johair.tabcalculator.Util;
import com.johair.tabcalculator.databinding.FragmentTraverseBinding;

import java.text.DecimalFormat;
import java.util.Objects;

public class TraverseFragment extends Fragment {

    private FragmentTraverseBinding binding;
    String[] traverseMethodOptions = {"Rectangle","Round","Logarithmic"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTraverseBinding.inflate(inflater, container, false);

        binding.calcButton.setOnClickListener(view -> calcTraversePoints());

        binding.clearButton.setOnClickListener(view -> resetTraverse());

        AutoCompleteTextView traverseMethod = binding.traverseMethodMenu;
        ArrayAdapter<String> traverseMethodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, traverseMethodOptions);
        //traverseMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        traverseMethod.setAdapter(traverseMethodAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    double measurement;
    double[] displayArray;

    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    // Retrieve user input data
    TextInputEditText inputMeasurement;
    TableLayout printLayout;
    AutoCompleteTextView traverseMenu;

    @SuppressLint({"SetTextI18n"})
    public void calcTraversePoints() {
        //Close Keyboard
        if (getActivity() != null) {
            Util.hideKeyboard(getActivity());
        }

        // Attempts to retrieve value from user input; if there is no value entered, defaults to 0
        inputMeasurement = binding.inputMeasurement;
        printLayout = binding.printLayout;
        traverseMenu = binding.traverseMethodMenu;

        try {
            measurement = Double.parseDouble(inputMeasurement.getText().toString());
        }
        catch (NumberFormatException ex) {
            measurement = 0;
        }

        // Unselect view
        inputMeasurement.setSelected(false);

        Toast.makeText(getActivity(),df3.format(traverseMenu.getListSelection()+1),Toast.LENGTH_LONG).show();

        switch (traverseMenu.getListSelection()+1) {
            case 0:
                displayArray = rectTrav.rectTravPoints(measurement);
                break;
            case 1:
                displayArray = roundTrav.roundTravPoints(measurement);
                break;
            case 2:
                displayArray = logTrav.logTravPoints(measurement);
                break;
        }

        // Clear Linear Layout
        printLayout.removeAllViews();

        // Display traverse points
        if (measurement > 0) {   // Prevents function from outputting zeros
            for (int i = 0;i <= displayArray.length - 1;i++) {
                // Create Round Corner Output Box
                GradientDrawable roundCornersShape = new GradientDrawable();
                roundCornersShape.setCornerRadius(20);
                roundCornersShape.setColor(Color.GRAY);

                // Create new row
                TableRow newRow = new TableRow(getActivity());

                // Marking output
                TextView pitotMarkView = new TextView(getActivity());
                pitotMarkView.setText("Pitot Marking "+df3.format(i+1)+":");
                pitotMarkView.setTextSize(20);
                pitotMarkView.setGravity(Gravity.CENTER);
                pitotMarkView.setTextColor(Color.BLACK);
                pitotMarkView.setBackground(roundCornersShape);
                TableRow.LayoutParams markingsLayoutParams = new TableRow.LayoutParams(500, 100);
                markingsLayoutParams.setMargins(5,5,5,5);
                newRow.addView(pitotMarkView, 0, markingsLayoutParams);
                newRow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);

                // Data output
                TextView measurementView = new TextView(getActivity());
                measurementView.setText(df3.format(displayArray[i]));
                measurementView.setTextSize(20);
                measurementView.setGravity(Gravity.CENTER);
                measurementView.setTextColor(Color.BLACK);
                measurementView.setBackground(roundCornersShape);
                TableRow.LayoutParams dataLayoutParams = new TableRow.LayoutParams(250, 100);
                dataLayoutParams.setMargins(5,5,5,5);
                newRow.addView(measurementView, 1, dataLayoutParams);
                newRow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);

                // Unit output
                TextView unitView = new TextView(getActivity());
                unitView.setText("in");
                unitView.setTextSize(20);
                unitView.setGravity(Gravity.CENTER);
                unitView.setTextColor(Color.BLACK);
                unitView.setBackground(roundCornersShape);
                TableRow.LayoutParams unitLayoutParams = new TableRow.LayoutParams(100, 100);
                unitLayoutParams.setMargins(5,5,5,5);
                newRow.addView(unitView, 2, unitLayoutParams);
                newRow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);

                // Add row to table
                printLayout.addView(newRow);
            }
        }
    }

    public void resetTraverse() {
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