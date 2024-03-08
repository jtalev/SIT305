package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        continueButton = findViewById(R.id.continueBtn);

        continueButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
               Intent intent;

               if (selectedRadioButtonId == -1) {
                   Toast.makeText(MainActivity.this, "Please select a unit", Toast.LENGTH_SHORT).show();
                   intent = null;
               } else if (selectedRadioButtonId == R.id.lengthBtn) {
                    intent = new Intent(MainActivity.this, LengthActivity.class);
               } else if (selectedRadioButtonId == R.id.temperatureBtn) {
                   intent = new Intent(MainActivity.this, TemperatureActivity.class);
               } else if (selectedRadioButtonId == R.id.weightBtn) {
                   intent = new Intent(MainActivity.this, WeightActivity.class);
               } else {
                   intent = null;
               }

               if (intent != null) {
                    startActivity(intent);
               }
           }
        });
    }
}