package com.example.itubeapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itubeapp.home.HomeActivity;
import com.example.itubeapp.R;
import com.example.itubeapp.data.user.User;
import com.example.itubeapp.signup.SignupActivity;
import com.example.itubeapp.utils.ViewModelUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText usernameInput;
    TextInputEditText passwordInput;
    Button loginButton;
    Button signupButton;

    LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        viewModel = new ViewModelUtils().getViewModel(this, LoginViewModel.class);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser() {
        String usernameText = usernameInput.getText().toString().trim();
        String passwordText = passwordInput.getText().toString().trim();

        if(usernameText.isEmpty()) {
            Toast.makeText(this, "Enter your username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordText.isEmpty()) {
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        ListenableFuture<User> futureUser = viewModel.getUserByUsernamePassword(usernameText, passwordText, this);
        Futures.addCallback(futureUser, new FutureCallback<User>() {
            @Override
            public void onSuccess(User result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result==null) {
                            Toast.makeText(LoginActivity.this, "Username or password incorrect, try again", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("UID", result.getUid());
                            Toast.makeText(LoginActivity.this, "Login succesful", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
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
                        Toast.makeText(LoginActivity.this, "Failed to access database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, MoreExecutors.directExecutor());
    }
}