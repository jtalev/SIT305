package com.example.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class TemperatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String[] topSpinnerItems = {"Celsius", "Fahrenheit", "Kelvin"};

        ArrayAdapter<String> topSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, topSpinnerItems);
        topSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerTop = findViewById(R.id.spinnerTop);
        spinnerTop.setAdapter(topSpinnerAdapter);
        Spinner spinnerBottom = findViewById(R.id.spinnerBottom);

        spinnerTop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                updateBottomSpinner(selectedItem, spinnerBottom);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                String[] bottomSpinnerItems = {"Select a source unit"};
//                ArrayAdapter<String> bottomSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bottomSpinnerItems);
//                bottomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinnerBottom.setAdapter(bottomSpinnerAdapter);
            }
        });

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

                if (selectedItemTop.equals("Celsius")) {
                    value = celsiusConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Kelvin")) {
                    value = kelvinConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Fahrenheit")) {
                    value = fahrenheitConverter(value, selectedItemBottom);
                } else {
                    convertedValue.setText(String.valueOf(value));
                }

                convertedValue.setText(String.valueOf(value));
            }
        });
    }

    private void updateBottomSpinner(String selectedItem, Spinner spinnerBottom) {
        ArrayAdapter<String> bottomSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        bottomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (selectedItem.equals("Celsius")) {
            bottomSpinnerAdapter.add("Fahrenheit");
            bottomSpinnerAdapter.add("Kelvin");
        } else if (selectedItem.equals("Fahrenheit")) {
            bottomSpinnerAdapter.add("Celsius");
            bottomSpinnerAdapter.add("Kelvin");
        } else if (selectedItem.equals("Kelvin")) {
            bottomSpinnerAdapter.add("Celsius");
            bottomSpinnerAdapter.add("Fahrenheit");
        }

        spinnerBottom.setAdapter(bottomSpinnerAdapter);
    }

    private double celsiusConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Fahrenheit")) {
            value = (value * 1.8) + 32;
        } else {
            value = value + 273.15;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }

    private double kelvinConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Celsius")) {
            value = value - 273.15;
        } else {
            value = (value - 273.15) * 1.8 + 32;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }

    private double fahrenheitConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Celsius")) {
            value = (value - 32) / 1.8;
        } else {
            value = (value - 32) * ((double) 5 / 9) + 273.15;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
}
