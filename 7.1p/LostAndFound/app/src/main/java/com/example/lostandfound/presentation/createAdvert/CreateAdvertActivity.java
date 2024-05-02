package com.example.lostandfound.presentation.createAdvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.databinding.ActivityCreateAdvertBinding;

/**
 * Activity for users to create new adverts to notify others of lost and found items.
 */
public class CreateAdvertActivity extends AppCompatActivity {
    private CreateAdvertViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);
    }
}