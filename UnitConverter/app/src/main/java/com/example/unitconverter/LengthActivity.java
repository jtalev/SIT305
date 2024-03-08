package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

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
        LinearLayout metricLayout = findViewById(R.id.metricLayout);
        LinearLayout imperialLayout = findViewById(R.id.imperialLayout);

        unitSystemSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                metricLayout.setVisibility(View.GONE);
                imperialLayout.setVisibility(View.VISIBLE);
            } else {
                metricLayout.setVisibility(View.VISIBLE);
                imperialLayout.setVisibility(View.GONE);
            }
        }));
    }
}
