package com.example.u3t1initialappantoniovieira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    sound = MediaPlayer.create(getApplicationContext(), R.raw.sonido);
                    sound.start();
                } catch (InterruptedException e) {
                }
                finally {
                    startActivity(new Intent("android.intent.action.STARTINGPOINT"));
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        sound.release();
        finish();
    }
}