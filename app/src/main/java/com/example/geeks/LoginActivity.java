package com.example.geeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.Endpoint;
import com.api.igdb.utils.Endpoints;
import com.api.igdb.utils.TwitchToken;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

import proto.Game;
import proto.GameResult;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    
    EditText etEmail;
    Button btLogin;
    String email;
    TextView tvError;
    TextView tvRegister;
    Endpoint endpoint;
    String apicalypseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Java Example
        //TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
        //TwitchToken token = tAuth.requestTwitchToken("30l4fgc0bdj7lg5k7ah68rnifkbsfx", "on03mhzbv8sm5wrbbitaiyuzlrkgyw");
        // The instance stores the token in the object untill a new one is requested
        //token = tAuth.getTwitchToken();

        //IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        //wrapper.setCredentials("30l4fgc0bdj7lg5k7ah68rnifkbsfx", token.getAccess_token());

        //APICalypse apicalypse = new APICalypse().fields("*").sort("release_dates.date", Sort.DESCENDING);
        //try{
        //    List<Game> games = ProtoRequestKt.games(wrapper, apicalypse);
        //} catch(RequestException e) {
        //    // Do something or error
        //}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        etEmail = findViewById(R.id.etEmail);
        btLogin = findViewById(R.id.btLogin);
        tvError = findViewById(R.id.tvError);
        tvRegister = findViewById(R.id.tvRegister);
        
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Login Button");
                email = etEmail.getText().toString();
                verifyEmail(email);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Regiter");
                goToSignUp();
            }
        });
    }

    private void goToSignUp() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
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
                        tvError.setText("No user found");
                    } else {
                        Log.d(TAG, "User found");
                        goToPassword(objects);
                    }
                }
            }
        });
    }
    private void goToPassword(List<ParseUser> objects) {
        Intent i = new Intent(this, PasswordActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("userInfo", Parcels.wrap(objects));
        startActivity(i);
        finish();
        
    }
}