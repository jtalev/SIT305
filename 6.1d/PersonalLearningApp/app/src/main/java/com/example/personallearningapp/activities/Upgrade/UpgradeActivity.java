package com.example.personallearningapp.activities.Upgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Profile.ProfileActivity;
import com.example.personallearningapp.models.User;

public class UpgradeActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER", User.class);
        }
    }

    public void purchaseStarter(View view) {
    }

    public void purchaseIntermediate(View view) {
    }

    public void purchaseAdvanced(View view) {
    }

    public void back(View view) {
        Intent intent = new Intent(UpgradeActivity.this, ProfileActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish();
    }
}