package com.example.practica3_antoniovr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends LogActivity {

    private static final String DEBUG_TAG = "LogsAndroid-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        Button btNextActivity = findViewById(R.id.btNextActivity);
        btNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });
    }
    //Expresion lamdba
    /*Button btNextActivity = findViewById(R.id.btNextActivity);
        btNextActivity.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NextActivity.class)));
*/


    public void LauncherNextActivity(View view) {
        startActivity(new Intent(this, NextActivity.class));
    }
}