package com.johair.tabcalculator.ui.Psychometric;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.johair.tabcalculator.databinding.FragmentPsychometricBinding;

public class PsychometricFragment extends Fragment {

    private FragmentPsychometricBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PsychometricViewModel psychometricViewModel =
                new ViewModelProvider(this).get(PsychometricViewModel.class);

        binding = FragmentPsychometricBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPsychometric;
        psychometricViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}