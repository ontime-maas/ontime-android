package com.izen1231.ontime.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.izen1231.ontime.R;
import com.izen1231.ontime.database.AlarmDatabaseOpenHelper;
import com.izen1231.ontime.domain.live.LiveDomain;
import com.izen1231.ontime.example.Example01Activity;
import com.izen1231.ontime.model.Alarm;
import com.izen1231.ontime.service.OntimeService;
import com.ornach.nobobutton.NoboButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShowAlarm extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String FRAGTAG = "RepeatingAlarmFragment";
    public static final int REQUEST_CODE = 0;
    AlarmDatabaseOpenHelper helper;
    SQLiteDatabase database;

    TextView txt_showalarm_alarmtime;
    TextView txt_showalarm_arrtime;

    String firstBusMessage;
    String secondBusMessage;

    TextView now_last_time;
    TextView now_last_minute;
    TextView now_last_busstop;
    TextView now_time;
    NoboButton alarm_kill;
    MediaPlayer mediaPlayer;

    private static PowerManager.WakeLock sCpuWakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm);

        now_last_time = (TextView)findViewById(R.id.now_last_time);
        now_last_minute = (TextView)findViewById(R.id.now_last_minute);
        now_last_busstop = (TextView)findViewById(R.id.now_last_busstop);
        now_time = (TextView)findViewById(R.id.now_time);
        alarm_kill = (NoboButton)findViewById(R.id.alarm_kill);

        init();

        if (sCpuWakeLock != null) {
            return;
        }
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        sCpuWakeLock = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "hi");

        sCpuWakeLock.acquire();

        if (sCpuWakeLock != null) {
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }

        helper = new AlarmDatabaseOpenHelper(ShowAlarm.this, null , null, 2);
        database = helper.getWritableDatabase();

        Alarm lastAlarm = getLastAlarm();
        helper.updateOnoffAlarm(1,lastAlarm);
        lastAlarm = getLastAlarm();
        OntimeService ontimeService = new OntimeService();
        String arsId = ontimeService.getArsId(lastAlarm.getTotalPathStationName(), Integer.toString(lastAlarm.getTotalPathStationId()));

        // arsid 를 이용해 내가 첫번째로 이용해야할 정류장 정보 가져오기
        System.out.println("여긴됨 !" + arsId);
//        LiveDomain liveStationList = ontimeService.getLiveBusStation(arsId);
//        System.out.println("여긴안됨 ! : " + liveStationList.getList().isEmpty());

//        if(liveStationList.getList().isEmpty()){
//            Toast.makeText(ShowAlarm.this,"서울 지역 버스만 실시간 알람이가능합니다.",Toast.LENGTH_LONG).show();
//        } else {
//            for(int i=0 ; i<liveStationList.getList().size(); i++){
//                if(liveStationList.getList().get(i).getRtNm().equals(lastAlarm.getBusNo())){
//                    this.firstBusMessage = liveStationList.getList().get(i).getArrmsg1();
//                    this.secondBusMessage = liveStationList.getList().get(i).getArrmsg2();
//                    break;
//                }
//            }
//        }

        System.out.println("내가 첫번째로 타야할 버스는 의 상태는1 : "  + this.firstBusMessage);
        System.out.println("내가 첫번째로 타야할 버스는 의 상태는2 : "  + this.secondBusMessage);

//        System.out.println("내가 타야할 정류장 ! : " + liveStationList);
        setUI(lastAlarm);
        playMusic();

        alarm_kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("isShowAlarm",  "true");
                startActivity(intent);
                finish();
            }
        });

    }

    private void setUI(Alarm lastAlarm){

        int goal = (lastAlarm.getGoal_h()*60)+lastAlarm.getGoal_m();
        int start = (lastAlarm.getStart_h()*60)+lastAlarm.getStart_m();
        int diff = goal-start;

        long start_hour = TimeUnit.MINUTES.toHours(diff); //
        long start_minutes = TimeUnit.MINUTES.toMinutes(diff) - TimeUnit.HOURS.toMinutes(start_hour);

        if(start_hour > 12){
            start_hour-= 12;
        }

        System.out.println("출발시간 시  : " +start_hour);
        System.out.println("출발시간 분  : " +start_minutes);
        if(start_hour > 0){
            now_last_time.setText(start_hour+"시 "+start_minutes+"분전" );
            now_last_minute.setText("출발까지 "+start_hour+"시 "+start_minutes+"분 남았습니다." );
        } else {
            now_last_time.setText(start_minutes+"분전" );
            now_last_minute.setText("출발까지 "+start_minutes+" 분 남았습니다." );
        }

//        now_last_busstop.setText(lastAlarm.getBusNo()+"번 버스"+firstBusMessage);

        now_time.setText(this.getCurrentTime());


    }

    private void init(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
    }

    private void playMusic(){
        Uri uri = Settings.System.DEFAULT_ALARM_ALERT_URI;
        mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.start();
    }

    private Alarm getLastAlarm(){
        List<Alarm> alarmList = helper.queryAlarmTable();
        Alarm lastAlarm = alarmList.get(alarmList.size()-1);
        System.out.println("사이즈 : "+alarmList.size());
        System.out.println("가장 최근의 알람 !  : "+lastAlarm);

        return lastAlarm;
    }

    private String getCurrentTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("HH:mm");
        return currentTimeFormat.format(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.reset();
    }


    // 사용 금지
    public void alarmExample() {
        Intent intent = new Intent(ShowAlarm.this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        PendingIntent pendingIntent = PendingIntent.getActivity(ShowAlarm.this, REQUEST_CODE,
                intent, 0);

        int alarmType = AlarmManager.ELAPSED_REALTIME;
        final int FIFTEEN_SEC_MILLIS = 15000;

        AlarmManager alarmManager = (AlarmManager)
                ShowAlarm.this.getSystemService(ShowAlarm.this.ALARM_SERVICE);

        alarmManager.set(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,pendingIntent);
    }
}
