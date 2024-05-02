package com.example.lostandfound.presentation.createAdvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.databinding.ActivityCreateAdvertBinding;
import com.example.lostandfound.presentation.createAdvert.model.AdvertFormState;

/**
 * Activity for users to create new adverts to notify others of lost and found items.
 */
public class CreateAdvertActivity extends AppCompatActivity {
    private ActivityCreateAdvertBinding binding;
    private CreateAdvertViewModel createAdvertViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        createAdvertViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CreateAdvertViewModel.class);
        binding = ActivityCreateAdvertBinding.inflate(getLayoutInflater());
        binding.setViewModel(createAdvertViewModel);
        setContentView(binding.getRoot());
        setContentView(binding.getRoot());

        createAdvertViewModel.getState().observe(this, AdvertFormState -> {
            String name = AdvertFormState.getName();
             if (name.equals("Hello")) {
                 AdvertFormState.setDescription("binding working");
             }
        });
    }
}