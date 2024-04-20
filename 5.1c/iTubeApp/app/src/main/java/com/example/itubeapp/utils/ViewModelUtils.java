package com.example.itubeapp.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelUtils {
    public <T extends ViewModel> T getViewModel(AppCompatActivity activity, Class<T> viewModelClass) {
        return new ViewModelProvider(activity).get(viewModelClass);
    }
}
