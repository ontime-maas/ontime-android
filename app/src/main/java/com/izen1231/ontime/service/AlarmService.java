package com.izen1231.ontime.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import com.izen1231.ontime.receiver.AlarmReceiver;
import com.izen1231.ontime.util.Constants;



public class AlarmService extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String on_Off = intent.getExtras().getString("ON_OFF");
        switch (on_Off) {
            case Constants.ADD_INTENT:
                Uri uri = Settings.System.DEFAULT_ALARM_ALERT_URI;
                mediaPlayer = MediaPlayer.create(this, uri);
                mediaPlayer.start();
                break;
            case Constants.OFF_INTENT:
                int alarmId = intent.getExtras().getInt("AlarmId");
                if (mediaPlayer != null && mediaPlayer.isPlaying() && alarmId == AlarmReceiver.pendingId) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                break;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}