package com.example.personallearningapp.activities.Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.models.User;
import com.example.personallearningapp.utils.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.LinkedHashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private SignupViewModel viewModel;
    private TextInputEditText firstNameInput;
    private TextInputEditText lastNameInput;
    private TextInputEditText usernameInput;
    private TextInputEditText emailInput;
    private TextInputEditText confirmEmailInput;
    private TextInputEditText passwordInput;
    private TextInputEditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        viewModel = new SignupViewModel(this);

        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        confirmEmailInput = findViewById(R.id.confirm_email_input);
        passwordInput = findViewById(R.id.password_input);
        confirmPasswordInput = findViewById(R.id.confirm_password_input);
    }

    public void registerUser(View view) {
        Map<String, String> inputs = new LinkedHashMap<>();
        inputs.put("first name", firstNameInput.getText().toString().trim());
        inputs.put("last name", lastNameInput.getText().toString().trim());
        inputs.put("username", usernameInput.getText().toString().trim());
        inputs.put("email", emailInput.getText().toString().trim());
        inputs.put("confirm email", confirmEmailInput.getText().toString().trim());
        inputs.put("password", passwordInput.getText().toString().trim());
        inputs.put("confirm password", confirmPasswordInput.getText().toString().trim());

        if (isFormValid(inputs)) {
            User user = new User(
                    inputs.get("first name"),
                    inputs.get("last name"),
                    inputs.get("username"),
                    inputs.get("email"),
                    inputs.get("password"));

            checkUserByUsername(user);
        }
    }

    private boolean isFormValid(Map<String, String> inputs) {
        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            String input = entry.getValue();
            String fieldName = entry.getKey();

            if (input.isEmpty()) {
                Toast.makeText(this, fieldName + " is empty", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (!Util.isValidEmail(inputs.get("email"))) {
            Toast.makeText(this,"email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!inputs.get("email").equals(inputs.get("confirm email"))) {
            Toast.makeText(this, "emails must match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Util.isValidPassword(inputs.get("password"))) {
            Toast.makeText(this,"password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!inputs.get("password").equals(inputs.get("confirm password"))) {
            Toast.makeText(this, "passwords must match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void checkUserByUsername(User user) {
        ListenableFuture<User> userByUsernameFuture = viewModel.getUserByUsername(user.getUsername());
        Futures.addCallback(userByUsernameFuture, new FutureCallback<User>() {
            @Override
            public void onSuccess(User existingUserByUsername) {
                runOnUiThread(() -> {
                    if (existingUserByUsername != null) {
                        Toast.makeText(SignupActivity.this, "User already exists with this username", Toast.LENGTH_SHORT).show();
                    } else {
                        checkUserByEmail(user);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SignupActivity.this, "Error checking username existence", Toast.LENGTH_SHORT).show();
            }
        }, MoreExecutors.directExecutor());
    }

    private void checkUserByEmail(User user) {
        ListenableFuture<User> userByEmailFuture = viewModel.getUserByEmail(user.getEmail());
        Futures.addCallback(userByEmailFuture, new FutureCallback<User>() {
            @Override
            public void onSuccess(User existingUserByEmail) {
                runOnUiThread(() -> {
                    if (existingUserByEmail != null) {
                        Toast.makeText(SignupActivity.this, "User already exists with that email", Toast.LENGTH_SHORT).show();
                    } else {
                        viewModel.insert(user);
                        Intent intent = new Intent(SignupActivity.this, UserInterestSelectionActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(SignupActivity.this, "Error checking email existence", Toast.LENGTH_SHORT).show();
                });
            }
        }, MoreExecutors.directExecutor());
    }
}