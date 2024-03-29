package com.example.portafgliomedico.ui.memos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MemosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}