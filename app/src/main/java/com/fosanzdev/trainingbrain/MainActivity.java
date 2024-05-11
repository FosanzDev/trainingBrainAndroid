package com.fosanzdev.trainingbrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("Session", MODE_PRIVATE);
        String token = preferences.getString("access_token", null);

        if (token == null) login();
        if (token != null) ensureTokenStillActive(token);
    }

    private void login() {
        Intent intent = new Intent(this, LoginActivity.class);
    }

    private void ensureTokenStillActive(String token) {
    }
}