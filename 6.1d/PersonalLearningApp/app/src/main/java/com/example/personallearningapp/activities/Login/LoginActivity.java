package com.example.personallearningapp.activities.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Profile.ProfileActivity;
import com.example.personallearningapp.activities.Signup.SignupActivity;
import com.example.personallearningapp.activities.Task.TaskActivity;
import com.example.personallearningapp.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText usernameInput;
    TextInputEditText passwordInput;
    AppCompatButton loginButton;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new LoginViewModel(this);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    public void launchSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void loginUser() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "username or password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        ListenableFuture<User> userFuture = viewModel.getUserByUsername(username);
        Futures.addCallback(userFuture, new FutureCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if(user == null) {
                    Toast.makeText(LoginActivity.this, "username doesn't exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!viewModel.isUserLoginValid(user, username, password)) {
                    Toast.makeText(LoginActivity.this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed to fetch user", Toast.LENGTH_SHORT).show();
            }
        }, getApplicationContext().getMainExecutor());
    }
}