package com.johair.tabcalculator.ui.Psychometric;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PsychometricViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PsychometricViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Psychometric fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}