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
        String[][] inputs = {{firstNameInput.getText().toString().trim(), "first name"},
                             {lastNameInput.getText().toString().trim(), "last name"},
                             {usernameInput.getText().toString().trim(), "username"},
                             {emailInput.getText().toString().trim(), "email"},
                             {confirmEmailInput.getText().toString().trim(), "confirm email"},
                             {passwordInput.getText().toString().trim(), "password"},
                             {confirmPasswordInput.getText().toString().trim(), "confirm password"}};

        for (String[] inputPair : inputs) {
            String input = inputPair[0];
            String fieldName = inputPair[1];

            if (input.isEmpty()) {
                Toast.makeText(this, fieldName + " is empty", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (!Util.isValidEmail(inputs[3][0])) {
            Toast.makeText(this, inputs[3][1] + " is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputs[3][0].equals(inputs[4][0])) {
            Toast.makeText(this, "emails must match", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Util.isValidPassword(inputs[5][0])) {
            Toast.makeText(this, inputs[5][1] + " must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputs[5][0].equals(inputs[6][0])) {
            Toast.makeText(this, "passwords must match", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(inputs[0][0], inputs[1][0], inputs[2][0], inputs[3][0], inputs[5][0]);
        ListenableFuture<User> userByUsernameFuture = viewModel.getUserByUsername(user.getUsername());
        ListenableFuture<User> userByEmailFuture = viewModel.getUserByEmail(user.getEmail());

        Futures.addCallback(userByUsernameFuture, new FutureCallback<User>() {
            @Override
            public void onSuccess(User existingUserByUsername) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (existingUserByUsername!=null) {
                            Toast.makeText(SignupActivity.this, "User already exists with this username", Toast.LENGTH_SHORT).show();
                        } else {
                            // Continue with email check
                            Futures.addCallback(userByEmailFuture, new FutureCallback<User>() {
                                @Override
                                public void onSuccess(User existingUserByEmail) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (existingUserByEmail!=null) {
                                                Toast.makeText(SignupActivity.this, "User already exists with that email", Toast.LENGTH_SHORT).show();
                                            } else {
                                                viewModel.insert(user);
                                                Intent intent = new Intent(SignupActivity.this, UserInterestSelectionActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SignupActivity.this, "Error checking email existence", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }, MoreExecutors.directExecutor());
                        }
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SignupActivity.this, "Error checking username existence", Toast.LENGTH_SHORT).show();
            }
        }, MoreExecutors.directExecutor());
    }
}