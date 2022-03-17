package com.johair.tabcalculator.ui.Traverse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TraverseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TraverseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Traverse fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}