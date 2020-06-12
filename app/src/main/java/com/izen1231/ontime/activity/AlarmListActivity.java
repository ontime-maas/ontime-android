package com.izen1231.ontime.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.izen1231.ontime.R;
import com.izen1231.ontime.adapter.AlarmListAdapter;
import com.izen1231.ontime.database.AlarmDatabaseOpenHelper;
import com.izen1231.ontime.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListActivity extends AppCompatActivity {
    AlarmDatabaseOpenHelper helper;
    SQLiteDatabase database;
    private AlarmListAdapter alarmListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);


        helper = new AlarmDatabaseOpenHelper(AlarmListActivity.this, null , null, 2);
        database = helper.getWritableDatabase();
//        helper.deleteTable(database);



        init();
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        List<String> mData = new ArrayList<String>();

        List<Alarm> alarmList = helper.queryAlarmTable();

        System.out.println("리스트사이즈 : "+alarmList.size());
        if(alarmList.size() > 0){
            for(int i =0 ; i<alarmList.size() ; i++){
                System.out.println( i +"번쨰 값 : "+alarmList.get(i));
            }
        }

        alarmListAdapter = new AlarmListAdapter(AlarmListActivity.this,alarmList);
        recyclerView.setAdapter(alarmListAdapter);
    }
}
