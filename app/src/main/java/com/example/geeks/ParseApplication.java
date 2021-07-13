package com.example.geeks;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Ct0CLm76IN7SxWq704o1QaRzm0Xgq7Qy1Gtn3BpY")
                .clientKey("Y9ClCC6Uxmn81ELNsHVpwmbekKVSsTph8OktruDP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
