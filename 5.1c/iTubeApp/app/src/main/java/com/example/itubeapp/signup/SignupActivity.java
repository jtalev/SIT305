package com.example.itubeapp.signup;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.itubeapp.R;
import com.example.itubeapp.data.user.User;
import com.example.itubeapp.utils.ViewModelUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

public class SignupActivity extends AppCompatActivity {
    ImageButton backButton;
    TextInputEditText fullName;
    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText confirmPassword;
    Button createAccountButton;
    private SignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        viewModel = new ViewModelUtils().getViewModel(this, SignupViewModel.class);

        backButton = findViewById(R.id.back_button);
        fullName = findViewById(R.id.name_input);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        confirmPassword = findViewById(R.id.confirm_password_input);
        createAccountButton = findViewById(R.id.create_account_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    public void saveUser() {
        String fullNameText = fullName.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirmPassword.getText().toString();

        if(fullNameText.isEmpty()) {
            makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(usernameText.isEmpty()) {
            makeText(this, "Enter your username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordText.isEmpty()) {
            makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(confirmPasswordText.isEmpty()) {
            makeText(this, "Enter password confirmation", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!passwordText.equals(confirmPasswordText)) {
            makeText(this, "Make sure passwords match", Toast.LENGTH_SHORT).show();
            return;
        }

        ListenableFuture<User> futureUser = viewModel.getUserByUsername(usernameText, this);
        Futures.addCallback(futureUser, new FutureCallback<User>() {
            @Override
            public void onSuccess(User result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null) {
                            makeText(SignupActivity.this, "Username already exists, choose a different one", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(fullNameText, usernameText, passwordText);
                            viewModel.insertUser(user, SignupActivity.this);
                            makeText(SignupActivity.this, "User saved", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                });
            }
            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t.printStackTrace();
                        makeText(SignupActivity.this, "Failed to access database", Toast.LENGTH_SHORT);
                    }
                });
            }
        }, MoreExecutors.directExecutor());
    }
}