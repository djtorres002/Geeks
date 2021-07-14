package com.example.geeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PasswordActivity extends AppCompatActivity {

    public static final String TAG = "PasswordActivity";

    ImageView ivUserPic;
    TextView tvName;
    EditText etPassword;
    Button btLogin;
    TextView tvReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        ivUserPic = findViewById(R.id.ivUserPic);
        tvName = findViewById(R.id.tvName);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvReturn = findViewById(R.id.tvReturn);

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Return");
                goToLogin();
            }
        });

    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}