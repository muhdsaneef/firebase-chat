package com.dailyapps.scribbles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.dailyapps.scribbles.app.AppConstants;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initScreenNavigation(final Class activityClass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showScreen(activityClass);
            }
        }, AppConstants.SPLASH_DELAY);
    }
    protected void showScreen(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right).toBundle();
        startActivity(intent, bundle);
    }

    protected void showScreen(Class activityClass, String chatRoomName) {
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right).toBundle();
        intent.putExtra(AppConstants.CHAT_ROOM_NAME, chatRoomName);
        startActivity(intent, bundle);
    }
}
