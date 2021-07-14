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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseObject;

import org.parceler.Parcels;

import java.util.List;

public class PasswordActivity extends AppCompatActivity {

    public static final String TAG = "PasswordActivity";

    ImageView ivUserPic;
    TextView tvName;
    EditText etPassword;
    Button btLogin;
    TextView tvReturn;
    TextView tvError;
    String password;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        List<ParseUser> objects = Parcels.unwrap(getIntent().getParcelableExtra("userInfo"));

        ivUserPic = findViewById(R.id.ivUserPic);
        tvName = findViewById(R.id.tvName);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvReturn = findViewById(R.id.tvReturn);
        tvError = findViewById(R.id.tvError);

        Glide.with(this)
                .load(objects.get(0).getParseFile("userPic").getUrl())
                .override(410, 250)
                .centerCrop()
                .transform(new CircleCrop())
                .into(ivUserPic);

        tvName.setText(objects.get(0).getUsername());
        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Return");
                goToLogin();
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Login");
                password = etPassword.getText().toString();
                username = objects.get(0).getUsername();
                loginUser(username, password);

            }
        });

    }

    private void loginUser(String username, String password) {
        Log.d(TAG, "loginUser: Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.d(TAG, "Issue with login", e);
                    tvError.setText("Wrong password");
                    return;
                } else {
                    goMainActivity();
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}