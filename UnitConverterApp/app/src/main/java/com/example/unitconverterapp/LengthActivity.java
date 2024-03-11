package com.example.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class LengthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Switch unitSystemSwitch = findViewById(R.id.unitToggle);

        String[] metricSpinnerItems = {"Centimeters", "Meters", "Kilometers"};
        String[] imperialSpinnerItems = {"Inches", "Feet", "Yards", "Miles"};

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

                if (selectedItemTop.equals("Centimeters")) {
                    value = centimeterConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Meters")) {
                    value = meterConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Kilometers")) {
                    value = kilometerConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Inches")) {
                    value = inchConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Feet")) {
                    value = feetConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Yards")) {
                    value = yardConverter(value, selectedItemBottom);
                } else if (selectedItemTop.equals("Miles")) {
                    value = mileConverter(value, selectedItemBottom);
                } else {
                    convertedValue.setText(String.valueOf(value));
                }

                convertedValue.setText(String.valueOf(value));
            }
        });
    }

    private double centimeterConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Inches")) {
            value = value / 2.54;
        } else if (bottomSelection.equals("Feet")) {
            value = value / 30.48;
        } else if (bottomSelection.equals("Yards")) {
            value = value / 91.44;
        } else {
            value = value / 160900;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double meterConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Inches")) {
            value = value * 39.37;
        } else if (bottomSelection.equals("Feet")) {
            value = value * 3.281;
        } else if (bottomSelection.equals("Yards")) {
            value = value * 1.094;
        } else {
            value = value / 1609;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double kilometerConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Inches")) {
            value = value * 39370;
        } else if (bottomSelection.equals("Feet")) {
            value = value * 3281;
        } else if (bottomSelection.equals("Yards")) {
            value = value * 1094;
        } else {
            value = value / 1.609;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double inchConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Centimeters")) {
            value = value * 2.54;
        } else if (bottomSelection.equals("Meters")) {
            value = value / 39.37;
        } else {
            value = value / 39370;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double feetConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Centimeters")) {
            value = value * 30.48;
        } else if (bottomSelection.equals("Meters")) {
            value = value / 3.281;
        } else {
            value = value / 3281;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double yardConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Centimeters")) {
            value = value * 91.44;
        } else if (bottomSelection.equals("Meters")) {
            value = value / 1.094;
        } else {
            value = value / 1094;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
    private double mileConverter(double value, String bottomSelection) {
        if (bottomSelection.equals("Centimeters")) {
            value = value * 160900;
        } else if (bottomSelection.equals("Meters")) {
            value = value * 1609;
        } else {
            value = value * 1.609;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));

        return value;
    }
}
