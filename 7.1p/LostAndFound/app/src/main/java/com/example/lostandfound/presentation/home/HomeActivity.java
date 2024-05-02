package com.example.lostandfound.presentation.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lostandfound.presentation.createAdvert.CreateAdvertActivity;
import com.example.lostandfound.presentation.showAdverts.ShowAdvertsActivity;
import com.example.lostandfound.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton createAdvertButton;
    AppCompatButton showAdvertsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createAdvertButton = findViewById(R.id.create_advert_button);
        createAdvertButton.setOnClickListener(this);

        showAdvertsButton = findViewById(R.id.show_adverts_button);
        showAdvertsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppCompatButton button = (AppCompatButton) v;

        if (button == createAdvertButton) {
            createAndRunIntent(this, CreateAdvertActivity.class);
        } else if (button == showAdvertsButton) {
            createAndRunIntent(this, ShowAdvertsActivity.class);
        }
    }

    /**
     * Creates and runs an intent to start an activity specified by the provided class
     *
     * @param context the context from which the intent should be started
     * @param cls the class of the activity to be started
     */
    public void createAndRunIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }
}