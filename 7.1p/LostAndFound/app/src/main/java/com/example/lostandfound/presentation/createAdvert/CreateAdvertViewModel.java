package com.example.lostandfound.presentation.createAdvert;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lostandfound.presentation.createAdvert.model.AdvertFormState;

public class CreateAdvertViewModel extends ViewModel {

    private final MutableLiveData<AdvertFormState> state = new MutableLiveData<>();

    public CreateAdvertViewModel() {
        state.setValue(new AdvertFormState());
    }

    public MutableLiveData<AdvertFormState> getState() {
        return this.state;
    }
}
