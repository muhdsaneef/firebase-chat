package com.dailyapps.scribbles.activities;

import android.os.Bundle;

import com.dailyapps.scribbles.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initScreenNavigation(ChatEntryScreen.class);
    }
}
