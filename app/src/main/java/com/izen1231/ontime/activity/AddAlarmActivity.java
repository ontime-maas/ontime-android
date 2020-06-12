package com.izen1231.ontime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.izen1231.ontime.R;
import com.izen1231.ontime.model.Alarm;
import com.izen1231.ontime.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlarmActivity extends AppCompatActivity {
    @BindView(R.id.toolBarAdd)
    Toolbar toolBarAdd;
    @BindView(R.id.addAlarm)
    Button addAlarm;
    @BindView(R.id.time_Picker)
    TimePicker timePicker;
    @BindView(R.id.activityName)
    TextView activityName;
    @BindView(R.id.name_Alarm)
    EditText name_Alarm;
    private boolean addScreen;
    private Alarm alarmEdit;
    private Intent intentInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolBarAdd.setNavigationIcon(R.drawable.ic_back);
        setScreen();
        backPressed();

    }

    private void backPressed() {
        toolBarAdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Constants.RESULT_CANCEL);
                onBackPressed();
            }
        });
    }
    private void setScreen() {
        intentInfor = getIntent();
        String screenType = intentInfor.getStringExtra("screenType");
        if (screenType.contains("add")) {
//            activityName.setText(R.string.add);
//            addAlarm.setText(R.string.add);
            addScreen = true;

        } else if (screenType.contains("edit")) {
            try {
                alarmEdit = (Alarm) intentInfor.getExtras().getSerializable("AlarmEdit");
            } catch (Exception e) {
                Log.e("setScreen exception", e.getMessage() + " cause: " + e.getCause());
            }

            if (alarmEdit != null) {
                timePicker.setHour(alarmEdit.getHour_x());
                timePicker.setMinute(alarmEdit.getMinute_x());
                name_Alarm.setText(alarmEdit.getAlarm_Name());
//                activityName.setText(R.string.edit);
//                addAlarm.setText(R.string.edit);
            }
            addScreen = false;
        }
    }

    @OnClick(R.id.addAlarm)
    public void onClick(View v) {
        Intent intent = new Intent(this, AlarmMainActivity.class);
        Alarm alarm = initAlarm();

        if (addScreen) {
            alarm.setId((int) System.currentTimeMillis());
            intent.putExtra("Alarm", alarm);
            setResult(RESULT_OK, intent);
            finish();

        } else {
            int position = intentInfor.getExtras().getInt("position");

            String name = alarm.getAlarm_Name();
            int hour = alarm.getHour_x();
            int minute = alarm.getMinute_x();

            alarmEdit.setAlarm_Name(name);
            alarmEdit.setHour_x(hour);
            alarmEdit.setMinute_x(minute);


            // sending it back by Bundle, this Bundle should be used if
            // we need to transfer a big data, this transfer all detail at a time
            Bundle bundle = new Bundle();
            bundle.putSerializable("Alarm", alarmEdit);
            bundle.putInt("position", position);

            intent.putExtras(bundle);
            // set result for this activity
            setResult(RESULT_OK, intent);
            // finish method is requires if this Activity was started by startActivityForResult
            finish();
        }


    }


    private Alarm initAlarm() {
        int toggleOn = 1;
        Alarm alarm;
        String name1 = null;
        int hour_x = 0;
        int minute_x = 0;

        try {
            hour_x = timePicker.getCurrentHour();
            minute_x = timePicker.getCurrentMinute();
            String name = name_Alarm.getText().toString();

            if (name.length() == 0) {
                name1 = name_Alarm.getHint().toString();
            } else {
                name1 = name_Alarm.getText().toString();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        alarm = new Alarm(hour_x, minute_x, name1, toggleOn);
        return alarm;
    }
}