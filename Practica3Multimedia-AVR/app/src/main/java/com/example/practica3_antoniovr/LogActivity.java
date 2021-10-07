package com.example.practica3_antoniovr;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class LogActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "LogsAndroid-1";
    private final String ACTIVITY_CLASS = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void launchNextActivity(View view) {
        startActivity(new Intent(this, NextActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onPause");
        notify(" onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onResume");
        notify(" onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onStart");
        notify(" onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onStop");
        notify(" onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onRestart");
        notify(" onRestart");
    }

    @Override
    protected void  onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onSaveInstanceState");
        notify(" onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onDestroy");
        if (isFinishing()){
            Log.i(DEBUG_TAG, ACTIVITY_CLASS+" finishedByUser");
        } else Log.i(DEBUG_TAG, ACTIVITY_CLASS+" finishedBySystem");
        notify(" onDestroy");
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(DEBUG_TAG, ACTIVITY_CLASS+" onRestoreInstaceState");
        notify(" onRestoreInstanceState");
    }

    private void notify(String eventName){
        String activityName = this.getClass().getSimpleName();

        String CHANNEL_ID = "My_LifeCycle";

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "My Lifecycle", NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription("lifecycle events");
        notificationChannel.setShowBadge(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager!= null){
            notificationManager.createNotificationChannel(notificationChannel);
        }
        }
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID).
                setContentTitle(activityName + " " + eventName).
                setContentText(getPackageName()).
                setAutoCancel(true).
                setSmallIcon(R.mipmap.ic_launcher);
        notificationManagerCompat.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }
}
