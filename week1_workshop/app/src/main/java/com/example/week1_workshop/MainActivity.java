package com.example.week1_workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextBtn = findViewById(R.id.nextBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        textView = findViewById(R.id.textView);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCount();
                updateTextView();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCount();
                updateTextView();
            }
        });
    }

    private void incrementCount(){
        count++;
        System.out.println("Count: " + count);
    }

    private void updateTextView() {
        textView.setText(String.format("Count: %d", count));
    }

    private void clearCount() {
        count = 0;
        System.out.println("Count: " + count);
    }
}