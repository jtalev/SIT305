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
               if (selectedRadioButtonId != -1) {
                   // to do: check what the value of selectedRadioButtonId is
                   Intent intent = new Intent(MainActivity.this, ConvertUnitsActivity.class);
                   intent.putExtra("selectedRadioButtonId", selectedRadioButtonId);
                   startActivity(intent);
               } else {
                   Toast.makeText(MainActivity.this, "Please select a unit", Toast.LENGTH_SHORT).show();
               }
           }
        });
    }
}