package com.example.geeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    
    EditText etEmail;
    Button btLogin;
    String email;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        // Sets the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        etEmail = findViewById(R.id.etEmail);
        btLogin = findViewById(R.id.btLogin);
        
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Login Button");
                email = etEmail.getText().toString();
                goToPassword(email);
            }
        });
    }

    private void goToPassword(String email) {
        Log.d(TAG, "goToPassword: Attempting get username");
    }
}