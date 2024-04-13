package com.example.overseer.main.login.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.overseer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText emailTextInput;
    TextInputEditText passwordTextInput;
    TextInputEditText confirmPasswordTextInput;
    AppCompatButton signupButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailTextInput = findViewById(R.id.emailSignup);
        passwordTextInput = findViewById(R.id.passwordSignup);
        confirmPasswordTextInput = findViewById(R.id.confirmPasswordSignup);
        signupButton = findViewById(R.id.registerButton);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailTextInput.getText());
                String password = String.valueOf(passwordTextInput.getText());
                String confirmPassword = String.valueOf(confirmPasswordTextInput.getText());

                if (email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Confirm passwords match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    showSuccessDialog();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Authentication successful.")
                .setCancelable(false)
                .setPositiveButton("Back to login", (dialog, which) -> {
                    finish();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}