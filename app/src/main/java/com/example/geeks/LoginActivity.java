package com.example.geeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

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
                verifyEmail(email);
            }
        });
    }

    private void verifyEmail(String email) {
        Log.d(TAG, "verifyEmail");
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", email);
        query.setLimit(20);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null) {
                    if (objects.size() == 0) {
                        Log.d(TAG, "No user found");
                    } else {
                        Log.d(TAG, "User found");
                        goToPassword(email);
                    }
                }
            }
        });
    }
    private void goToPassword(String email) {
        Intent i = new Intent(this, PasswordActivity.class);
        startActivity(i);
        finish();
        
    }
}