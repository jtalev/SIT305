package com.example.personallearningapp.activities.Upgrade;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Profile.ProfileActivity;
import com.example.personallearningapp.models.User;
import com.example.personallearningapp.persistence.AppDatabase;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpgradeActivity extends AppCompatActivity {
    User user;
    PaymentSheet paymentSheet;
    String paymentClientSecret;
    PaymentSheet.CustomerConfiguration customerConfig;
    AppCompatButton starterButton;
    AppCompatButton intermediateButton;
    AppCompatButton advancedButton;
    int purchaseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER", User.class);
        }

        starterButton = findViewById(R.id.starter_button);
        intermediateButton = findViewById(R.id.intermediate_button);
        advancedButton = findViewById(R.id.advanced_button);

        if (user.getAccountType() == 1) {
            starterButton.setText("Purchased");
            starterButton.setClickable(false);
            starterButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
        } else if (user.getAccountType() == 2) {
            starterButton.setText("Purchased");
            starterButton.setClickable(false);
            starterButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
            intermediateButton.setText("Purchased");
            intermediateButton.setClickable(false);
            intermediateButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
        } else if (user.getAccountType() == 3) {
            starterButton.setText("Purchased");
            starterButton.setClickable(false);
            starterButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
            intermediateButton.setText("Purchased");
            intermediateButton.setClickable(false);
            intermediateButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
            advancedButton.setText("Purchased");
            advancedButton.setClickable(false);
            advancedButton.setBackground(getDrawable(R.drawable.submit_background_non_clickable));
        }

        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);
    }

    public void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {
            Log.d(TAG, "Canceled");
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Log.e(TAG, "Got error: ", ((PaymentSheetResult.Failed) paymentSheetResult).getError());
        } else if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            // Display for example, an order confirmation screen
            Log.d(TAG, "Completed");
            user.setAccountType(purchaseType);
            Intent intent = new Intent(UpgradeActivity.this, ProfileActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
            finish();
        }
    }

    public void purchaseStarter(View view) {
        Fuel.INSTANCE.post("http://10.0.2.2:5000/payment-sheet/starter", null).responseString(new Handler<String>() {
            @Override
            public void success(String s) {
                try {
                    final JSONObject result = new JSONObject(s);
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            result.getString("customer"),
                            result.getString("ephemeralKey")
                    );
                    paymentClientSecret = result.getString("paymentIntent");
                    PaymentConfiguration.init(getApplicationContext(), result.getString("publishableKey"));
                    presentPaymentSheet();
                    purchaseType = 1;
                } catch (JSONException e) { /* handle error */ }
            }

            @Override
            public void failure(@NonNull FuelError fuelError) {
                runOnUiThread(() -> Toast.makeText(UpgradeActivity.this, "failure", Toast.LENGTH_SHORT).show());
            }
        });
    }

    public void purchaseIntermediate(View view) {
        Fuel.INSTANCE.post("http://10.0.2.2:5000/payment-sheet/intermediate", null).responseString(new Handler<String>() {
            @Override
            public void success(String s) {
                try {
                    final JSONObject result = new JSONObject(s);
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            result.getString("customer"),
                            result.getString("ephemeralKey")
                    );
                    paymentClientSecret = result.getString("paymentIntent");
                    PaymentConfiguration.init(getApplicationContext(), result.getString("publishableKey"));
                    presentPaymentSheet();
                    purchaseType = 2;
                } catch (JSONException e) { /* handle error */ }

            }

            @Override
            public void failure(@NonNull FuelError fuelError) {
                runOnUiThread(() -> Toast.makeText(UpgradeActivity.this, "failure", Toast.LENGTH_SHORT).show());
            }
        });
    }

    public void purchaseAdvanced(View view) {
        Fuel.INSTANCE.post("http://10.0.2.2:5000/payment-sheet/advanced", null).responseString(new Handler<String>() {
            @Override
            public void success(String s) {
                try {
                    final JSONObject result = new JSONObject(s);
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            result.getString("customer"),
                            result.getString("ephemeralKey")
                    );
                    paymentClientSecret = result.getString("paymentIntent");
                    PaymentConfiguration.init(getApplicationContext(), result.getString("publishableKey"));
                    presentPaymentSheet();
                    purchaseType = 3;
                } catch (JSONException e) { /* handle error */ }

            }

            @Override
            public void failure(@NonNull FuelError fuelError) {
                runOnUiThread(() -> Toast.makeText(UpgradeActivity.this, "failure", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void presentPaymentSheet() {
        final PaymentSheet.Configuration configuration = new PaymentSheet.Configuration.Builder("Personal Learning App")
                .customer(customerConfig)
                .allowsDelayedPaymentMethods(true)
                .build();
        paymentSheet.presentWithPaymentIntent(
                paymentClientSecret,
                configuration
        );
    }

    public void back(View view) {
        Intent intent = new Intent(UpgradeActivity.this, ProfileActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish();
    }
}