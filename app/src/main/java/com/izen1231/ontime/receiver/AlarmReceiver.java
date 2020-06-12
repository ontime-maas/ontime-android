package com.izen1231.ontime.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.izen1231.ontime.service.AlarmService;
import com.izen1231.ontime.util.Constants;

public class AlarmReceiver extends BroadcastReceiver {

    public static int pendingId;

    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent != null) {

            Intent intentToService = new Intent(context, AlarmService.class);
            try {

                String intentType = intent.getExtras().getString("intentType");
                switch (intentType) {
                    case Constants.ADD_INTENT:

                        pendingId = intent.getExtras().getInt("PendingId");
                        intentToService.putExtra("ON_OFF", Constants.ADD_INTENT);
                        context.startService(intentToService);

                        break;
                    case Constants.OFF_INTENT:

                        int alarmId = intent.getExtras().getInt("AlarmId");
                        // alarm 서비스 시작
                        intentToService.putExtra("ON_OFF", Constants.OFF_INTENT);
                        intentToService.putExtra("AlarmId", alarmId);
                        context.startService(intentToService);

                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}