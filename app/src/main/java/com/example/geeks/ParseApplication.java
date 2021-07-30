package com.example.geeks;

import android.app.Application;

import com.example.geeks.models.Post;
import com.example.geeks.models.Tournament;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Tournament.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Ct0CLm76IN7SxWq704o1QaRzm0Xgq7Qy1Gtn3BpY")
                .clientKey("Y9ClCC6Uxmn81ELNsHVpwmbekKVSsTph8OktruDP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
