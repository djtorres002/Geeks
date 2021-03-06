package com.example.geeks.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.geeks.MainActivity;
import com.example.geeks.models.Tournament;

import com.example.geeks.R;
import com.parse.Parse;
import com.parse.ParseUser;

public class NewTournamentActivity extends AppCompatActivity {

    Button btCreate;
    Button btClose;
    EditText etGameName;
    EditText etNumberOfPlayers;
    DatePicker dpDate;
    EditText etTime;
    EditText etTimeZone;
    EditText etPlatform;
    EditText etExtraD;

    public static final String TAG = "NewTournament";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tournament);
        btCreate = findViewById(R.id.btCreate);
        etGameName = findViewById(R.id.etGameName);
        etNumberOfPlayers = findViewById(R.id.etNumberOfPlayers);
        dpDate = findViewById(R.id.dpDate);
        etTime = findViewById(R.id.etTime);
        etTimeZone = findViewById(R.id.etTimeZone);
        etPlatform = findViewById(R.id.etPlatform);
        etExtraD = findViewById(R.id.etExtraD);
        btClose = findViewById(R.id.btClose);
        
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainActivity();
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etGameName.getText().toString().isEmpty() ||
                        etNumberOfPlayers.getText().toString().isEmpty() ||
                        etTime.getText().toString().isEmpty() ||
                        etTimeZone.getText().toString().isEmpty() ||
                        etPlatform.getText().toString().isEmpty() ||
                        etExtraD.getText().toString().isEmpty()){
                    return;
                } else {
                    String gameName = etGameName.getText().toString();
                    String numberOfPlayers = etNumberOfPlayers.getText().toString();
                    String date = dpDate.getTransitionName();
                    String time = etTime.getText().toString();
                    String timeZone= etTimeZone.getText().toString();
                    ParseUser user = ParseUser.getCurrentUser();
                    String platform = etPlatform.getText().toString();
                    String extraDetails = etExtraD.getText().toString();
                    saveTournament(gameName, numberOfPlayers, date, time, timeZone, user, platform, extraDetails);
                }
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void saveTournament(String gameName, String numberOfPlayers, String date, String time, String timeZone, ParseUser user, String platform, String extraDetails) {
        Tournament tournament  = new Tournament();
        tournament.setGameName(gameName);
        //Integer value = stringToInt(numberOfPlayers);
        //tournament.setNumberofPlayers(value);
        //tournament.setGameDate();
        //tournament.setEndLine();
        tournament.setPlatform(platform);
        tournament.setExtraDetials(extraDetails);
    }

    //private int stringToInt(String numberOfPlayers) {
        //if (!"".equals(numberOfPlayers){
        //    Integer value = Integer.parseInt(numberOfPlayers);
        //    return value;
        //}
    //}
}