package com.example.marianflanagan.paws.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marianflanagan.paws.R;

/**
 * Created by marianflanagan
 * Student ID - x16119444
 */

public class SplashScreen extends AppCompatActivity {
    private static final int DELAY_SECONDS = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i= new Intent(SplashScreen.this, ActivityLogin.class);
                startActivity(i);
                finish();
            }
        }, DELAY_SECONDS);
    }
}
