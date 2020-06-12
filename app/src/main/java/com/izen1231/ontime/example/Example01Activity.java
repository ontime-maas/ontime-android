package com.izen1231.ontime.example;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.izen1231.ontime.R;
import com.izen1231.ontime.activity.MainActivity;
import com.izen1231.ontime.domain.bus.BusDomain;
import com.izen1231.ontime.domain.path.pathDomain;
import com.izen1231.ontime.service.OntimeService;

public class Example01Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example01);

        OntimeService ontimeService = new OntimeService();

        BusDomain busParam =ontimeService.getArsIdByBusStation("무궁화코오롱.건영아파트","184849");

        System.out.println("테스트 결과 : "+busParam);
//        alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,
//                FIFTEEN_SEC_MILLIS, pendingIntent);


//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        RepeatingAlarmFragment fragment = new RepeatingAlarmFragment();
//        transaction.add(fragment, FRAGTAG);
//        transaction.commit();


    }
}
