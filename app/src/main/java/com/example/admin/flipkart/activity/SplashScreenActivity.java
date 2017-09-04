package com.example.admin.flipkart.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.app.AppActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppActivity {

    long delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_splash_screen);

            Timer Runsplash = new Timer();
            TimerTask showSplash = new TimerTask() {
                @Override
                public void run() {
                    finish();

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            };

            Runsplash.schedule(showSplash, delay);

        }
}
