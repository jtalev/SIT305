package com.example.unitconverterapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class WeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Switch unitSystemSwitch = findViewById(R.id.unitToggle);

        String[] metricSpinnerItems = {"Grams", "Kilograms"};
        String[] imperialSpinnerItems = {"Ounce", "Pound", "Ton"};

        ArrayAdapter<String> metricAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, metricSpinnerItems);
        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> imperialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imperialSpinnerItems);
        imperialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerTop = findViewById(R.id.spinnerTop);
        Spinner spinnerBottom = findViewById(R.id.spinnerBottom);
        spinnerTop.setAdapter(metricAdapter);
        spinnerBottom.setAdapter(imperialAdapter);

        unitSystemSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                spinnerTop.setAdapter(imperialAdapter);
                spinnerBottom.setAdapter(metricAdapter);
            } else {
                spinnerTop.setAdapter(metricAdapter);
                spinnerBottom.setAdapter(imperialAdapter);
            }
        }));
    }
}
