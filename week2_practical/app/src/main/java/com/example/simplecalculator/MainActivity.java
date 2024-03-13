package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText topInput = findViewById(R.id.topValInput);
        TextInputEditText bottomInput = findViewById(R.id.bottomValInput);
        TextView value = findViewById(R.id.value);
        Button plusBtn = findViewById(R.id.plusBtn);
        Button minusBtn = findViewById(R.id.minusBtn);
        Button multiplyBtn = findViewById(R.id.multiplyBtn);
        Button divideBtn = findViewById(R.id.divideBtn);


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double topInputDouble = Double.parseDouble(topInput.getText().toString());
                double bottomInputDouble = Double.parseDouble(bottomInput.getText().toString());
                value.setText(String.valueOf(topInputDouble+bottomInputDouble));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double topInputDouble = Double.parseDouble(topInput.getText().toString());
                double bottomInputDouble = Double.parseDouble(bottomInput.getText().toString());
                value.setText(String.valueOf(topInputDouble-bottomInputDouble));
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double topInputDouble = Double.parseDouble(topInput.getText().toString());
                double bottomInputDouble = Double.parseDouble(bottomInput.getText().toString());
                value.setText(String.valueOf(topInputDouble*bottomInputDouble));
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double topInputDouble = Double.parseDouble(topInput.getText().toString());
                double bottomInputDouble = Double.parseDouble(bottomInput.getText().toString());
                value.setText(String.valueOf(topInputDouble/bottomInputDouble));
            }
        });
    }
}