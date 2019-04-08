package com.nexp.pavel.networkexpertise.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nexp.pavel.networkexpertise.R;



public class LoginActivity extends AppCompatActivity {

    private Button log_btn;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        log_btn = findViewById(R.id.email_sign_in_button);
        log_btn.setOnClickListener(v -> startActivity(MainActivity.newIntent(LoginActivity.this)));

    }
}
