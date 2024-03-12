package com.example.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

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

        Button convertBtn = findViewById(R.id.convertBtn);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItemTop = spinnerTop.getSelectedItem().toString();
                String selectedItemBottom = spinnerBottom.getSelectedItem().toString();
                TextInputEditText valueInput = findViewById(R.id.valueInput);
                TextView convertedValue = findViewById(R.id.convertedValue);
                String inputValue = valueInput.getText().toString();
                double value = Double.parseDouble(inputValue);

                if (selectedItemTop.equals("Grams")) {
                    value = gramConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Kilograms")) {
                    value = kilogramConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Ounce")) {
                    value = ounceConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Pound")) {
                    value = poundConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Ton")) {
                    value = tonConverter(value, selectedItemBottom);
                } else {
                    convertedValue.setText(String.valueOf(value));
                }

                convertedValue.setText(String.valueOf(value));
            }
        });
    }

    private double gramConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Ounce")) {
            value = value / 28.35;
        } else if (bottomSelection.equals("Pound")) {
            value = value / 453.6;
        } else {
            value = value / 907200;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double kilogramConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Ounce")) {
            value = value * 35.274;
        } else if (bottomSelection.equals("Pound")) {
            value = value * 2.205;
        } else {
            value = value / 907.2;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double ounceConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Grams")) {
            value = value * 28.35;
        } else {
            value = value / 35.274;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double poundConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Grams")) {
            value = value * 453.6;
        } else {
            value = value / 2.205;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double tonConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Grams")) {
            value = value * 907200;
        } else {
            value = value * 907.2;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
}
