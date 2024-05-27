package com.example.personallearningapp.activities.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Login.LoginActivity;
import com.example.personallearningapp.models.UserInterest;

import java.util.ArrayList;
import java.util.List;

public class UserInterestSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    UserInterestSelectionViewModel viewModel;
    AppCompatButton algorithmsButton;
    AppCompatButton dataStructuresButton;
    AppCompatButton devOpsButton;
    AppCompatButton agileMethodologyButton;
    AppCompatButton softwareArchitectureButton;
    AppCompatButton tddButton;
    AppCompatButton microservicesButton;
    AppCompatButton containerizationButton;
    AppCompatButton cloudComputingButton;
    AppCompatButton versionControlButton;
    AppCompatButton securityButton;
    AppCompatButton mobileDevButton;
    AppCompatButton webDevelopmentButton;
    AppCompatButton designPatternsButton;
    AppCompatButton timeComplexityButton;
    AppCompatButton testingButton;
    private int selectedInterestCounter;
    private List<String> interestList;
    private String username;
    AppCompatButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interests);

        viewModel = new UserInterestSelectionViewModel(this);

        algorithmsButton = findViewById(R.id.algorithms_button);
        dataStructuresButton = findViewById(R.id.data_structures_button);
        devOpsButton = findViewById(R.id.dev_ops_button);
        agileMethodologyButton = findViewById(R.id.agile_button);
        softwareArchitectureButton = findViewById(R.id.architecture_button);
        tddButton = findViewById(R.id.tdd_button);
        microservicesButton = findViewById(R.id.microservices_button);
        containerizationButton = findViewById(R.id.containerization_button);
        cloudComputingButton = findViewById(R.id.cloud_computing_button);
        versionControlButton = findViewById(R.id.version_control_button);
        securityButton = findViewById(R.id.security_button);
        mobileDevButton = findViewById(R.id.mobile_dev_button);
        webDevelopmentButton = findViewById(R.id.web_dev_button);
        designPatternsButton = findViewById(R.id.design_patterns_button);
        timeComplexityButton = findViewById(R.id.time_complexity_button);
        testingButton = findViewById(R.id.testing_button);
        submitButton = findViewById(R.id.submit_button);

        interestList = new ArrayList<>();
        username = getIntent().getStringExtra("USERNAME");

        algorithmsButton.setOnClickListener(this);
        dataStructuresButton.setOnClickListener(this);
        devOpsButton.setOnClickListener(this);
        agileMethodologyButton.setOnClickListener(this);
        softwareArchitectureButton.setOnClickListener(this);
        tddButton.setOnClickListener(this);
        microservicesButton.setOnClickListener(this);
        containerizationButton.setOnClickListener(this);
        cloudComputingButton.setOnClickListener(this);
        versionControlButton.setOnClickListener(this);
        securityButton.setOnClickListener(this);
        mobileDevButton.setOnClickListener(this);
        webDevelopmentButton.setOnClickListener(this);
        designPatternsButton.setOnClickListener(this);
        timeComplexityButton.setOnClickListener(this);
        testingButton.setOnClickListener(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerInterests(interestList);
            }
        });
    }

    @Override
    public void onClick(View v) {
        AppCompatButton button = (AppCompatButton) v;
        Drawable currentBackground = button.getBackground();
        Drawable userBackground = AppCompatResources.getDrawable(this, R.drawable.user_input_background);
        String buttonText = button.getText().toString();

        if (currentBackground.getConstantState().equals(userBackground.getConstantState())) {
            if(selectedInterestCounter == 10) {
                Toast.makeText(this, "10 interests already selects", Toast.LENGTH_SHORT).show();
                return;
            }
            button.setBackground(AppCompatResources.getDrawable(this, R.drawable.interest_button_selected));
            interestList.add(buttonText);
            selectedInterestCounter++;
        } else {
            button.setBackground(userBackground);
            interestList.remove(buttonText);
            selectedInterestCounter--;
        }
    }

    public void registerInterests(List<String> interestList) {
        if(selectedInterestCounter < 3) {
            Toast.makeText(this, "Select at least 3 interests", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < interestList.size(); i++) {
            UserInterest interest = new UserInterest(username, interestList.get(i));
            viewModel.insert(interest);
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}