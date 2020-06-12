package com.izen1231.ontime.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.izen1231.ontime.R;
import com.izen1231.ontime.database.DataBaseManager;
import com.izen1231.ontime.model.Alarm;
import com.izen1231.ontime.receiver.AlarmReceiver;
import com.izen1231.ontime.util.Constants;
import com.izen1231.ontime.view.AlarmAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmMainActivity extends AppCompatActivity implements AlarmAdapter.CallBack {

    @BindView(R.id.openAdd)
    Button button;
    @BindView(R.id.alarmView)
    RecyclerView recyclerView;
    private DataBaseManager dataBaseManager;
    private AlarmAdapter alarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmmain);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        importData();
        recyclerView.setAdapter(alarmAdapter);
    }

    @OnClick(R.id.openAdd)
    public void onOpenAddAlarm(View view) {
        Intent intent = new Intent(getApplicationContext(), AddAlarmActivity.class);
        intent.putExtra("screenType", "add");
        startActivityForResult(intent, Constants.REQUEST_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Alarm alarm;
        if (requestCode == Constants.REQUEST_ADD && resultCode == RESULT_OK) {
            alarm = (Alarm) data.getSerializableExtra("Alarm");
            boolean containAlarm = checkAlarm(alarm);

            if (!containAlarm) {
                alarmAdapter.add(alarm);
                alarmAdapter.notifyDataSetChanged();
                dataBaseManager.insert(alarm);
                setAlarm(alarm, 0);
            }


        } else if (requestCode == Constants.REQUEST_EDIT && resultCode == RESULT_OK) {
            alarm = (Alarm) data.getSerializableExtra("Alarm");
            boolean containAlarm = checkAlarm(alarm);

            if (!containAlarm) {
                int position = data.getExtras().getInt("position");
                alarmAdapter.updateAlarm(alarm, position);
                alarmAdapter.notifyDataSetChanged();
                dataBaseManager.update(alarm);
                if (alarm.getOnOff() == 1) {
                    setAlarm(alarm, PendingIntent.FLAG_UPDATE_CURRENT);
                }
            }

        }
    }

    @Override
    public void onMenuAction(Alarm alarm, MenuItem item, int position) {

        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this, AddAlarmActivity.class);
                intent.putExtra("screenType", "edit");
                intent.putExtra("AlarmEdit", alarm);
                intent.putExtra("position", position);
                startActivityForResult(intent, Constants.REQUEST_EDIT);
                break;
            case R.id.delete:
                alarmAdapter.removeAlarm(position);
                alarmAdapter.notifyDataSetChanged();
                int alarmId = (int) alarm.getId();
                dataBaseManager.delete(alarmId);
                deleteCancel(alarm);
                break;
        }

    }

    @Override
    public void startAlarm(Alarm alarm) {
        alarm.setOnOff(1);
        dataBaseManager.update(alarm);
        setAlarm(alarm, 0);
    }

    @Override
    public void cancelAlarm(Alarm timeItem) {
        timeItem.setOnOff(0);
        dataBaseManager.update(timeItem);
        deleteCancel(timeItem);
        sendIntent(timeItem, Constants.OFF_INTENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean checkAlarm(Alarm alarm) {
        boolean contain = false;
        for (Alarm alar : alarmAdapter.getmAlarms()) {
            if (alar.getHour_x() == alarm.getHour_x() && alar.getMinute_x() == alarm.getMinute_x())
                contain = true;
        }
        if (contain) {
            Toast.makeText(this, "You have already set this Alarm", Toast.LENGTH_SHORT).show();
        }
        return contain;
    }


    private void importData() {
        if (alarmAdapter == null) {
            dataBaseManager = new DataBaseManager(this);
            ArrayList<Alarm> arrayList = dataBaseManager.getAlarmList();
            alarmAdapter = new AlarmAdapter(arrayList, this);
        }
    }


    private void sendIntent(Alarm alarm, String intentType) {
        Intent intent1 = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        intent1.putExtra("intentType", intentType);
        intent1.putExtra("AlarmId", (int) alarm.getId());
        sendBroadcast(intent1);
    }


    private void setAlarm(Alarm alarm, int flags) {
        Calendar myCalendar = Calendar.getInstance();
        Calendar calendar = (Calendar) myCalendar.clone();
        calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour_x());
        calendar.set(Calendar.MINUTE, alarm.getMinute_x());
        calendar.set(Calendar.SECOND, 0);
        if (calendar.compareTo(myCalendar) <= 0) {
            calendar.add(Calendar.DATE, 1);
        }
        int alarmId = (int) alarm.getId();
        Intent intent = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        intent.putExtra("intentType", Constants.ADD_INTENT);
        intent.putExtra("PendingId", alarmId);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, alarmId,
                intent, flags);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    private void deleteCancel(Alarm alarm) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int alarmId = (int) alarm.getId();
        Intent intent = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, alarmId, intent, 0);
        alarmManager.cancel(alarmIntent);
    }
}
