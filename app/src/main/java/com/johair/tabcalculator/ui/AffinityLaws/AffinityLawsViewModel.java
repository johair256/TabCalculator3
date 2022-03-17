package com.johair.tabcalculator.ui.AffinityLaws;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AffinityLawsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AffinityLawsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Affinity Laws fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}