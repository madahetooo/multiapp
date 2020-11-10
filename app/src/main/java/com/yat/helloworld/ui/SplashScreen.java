package com.yat.helloworld.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.helloworld.utils.LoginActivity;
import com.yat.helloworld.R;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences savedLoginDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish(); // finish this activity


            }
        }).start();
    }
}