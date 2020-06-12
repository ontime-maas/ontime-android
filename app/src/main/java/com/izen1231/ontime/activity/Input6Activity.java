package com.izen1231.ontime.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.izen1231.ontime.R;
import com.izen1231.ontime.database.AlarmDatabaseOpenHelper;
import com.izen1231.ontime.domain.AddressDomain;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.domain.UtilTimeDomain;
import com.izen1231.ontime.domain.bus.BusDomain;
import com.izen1231.ontime.domain.locationDomain;
import com.izen1231.ontime.domain.path.pathDomain;
import com.izen1231.ontime.model.Alarm;
import com.izen1231.ontime.service.DateService;
import com.izen1231.ontime.service.OntimeService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Input6Activity extends AppCompatActivity {

    TextView txt_totalTime;
    TextView txt_readyTime;
    TextView wakeup_time;
    TextView txt_goalTime;
    TextView start_time;
    Button alarm_finish;
    TextView txt_com_saddress;
    TextView txt_com_eaddress;

    AlarmDatabaseOpenHelper helper;
    SQLiteDatabase database;

    public static final int REQUEST_CODE = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input6);


        try {
            txt_totalTime = (TextView)findViewById(R.id.txt_totalTime);
//            txt_readyTime = (TextView)findViewById(R.id.txt_readyTime);
            wakeup_time = (TextView)findViewById(R.id.wakeup_time);
//            txt_goalTime = (TextView)findViewById(R.id.txt_goalTime);
            start_time = (TextView)findViewById(R.id.start_time);
//            txt_goalTime = (TextView)findViewById(R.id.txt_goalTime);
            alarm_finish = (Button)findViewById(R.id.alarm_finish);
            txt_com_saddress = (TextView)findViewById(R.id.txt_com_saddress);
            txt_com_eaddress = (TextView)findViewById(R.id.txt_com_eaddress);

            Intent intent = getIntent();

            TimeDomain totalTime = (TimeDomain) intent.getExtras().getSerializable("totalTime");
            TimeDomain readyTime = (TimeDomain) intent.getExtras().getSerializable("readyTime");
            String start_address = intent.getExtras().getString("start_address");
            String end_address = intent.getExtras().getString("end_address");
            String totalPathStationName = intent.getExtras().getString("totalPathStationName");
            int totalPathStationId = intent.getExtras().getInt("totalPathStationId");
            String totalbusNo = intent.getExtras().getString("totalbusNo");
            int totalBusId = intent.getExtras().getInt("totalBusId");
            int warkTime = intent.getExtras().getInt("warkTime");

            txt_com_saddress.setText(start_address);
            txt_com_eaddress.setText(end_address);


            System.out.println("activity 6 이동시간 시 : " + totalTime.getHour());
            System.out.println("activity 6 이동시간 분 : " + totalTime.getMin());
            System.out.println("activity 6 준비시간 분 : " + readyTime.getMin());

            int totalTimeTransMinute = (totalTime.getHour()*60) + (totalTime.getMin()+10);

            long to_hour = TimeUnit.MINUTES.toHours(totalTimeTransMinute); //
            long to_minutes = TimeUnit.MINUTES.toMinutes(totalTimeTransMinute) - TimeUnit.HOURS.toMinutes(to_hour);

            totalTime.setHour((int) to_hour);
            totalTime.setMin((int) to_minutes);

            txt_totalTime.setText(totalTime.getHour()+"시간 "+totalTime.getMin()+"분");

            int i_readyTime_h = readyTime.getHour();
//            if(i_readyTime_h == 0){
//                txt_readyTime.setText(readyTime.getMin()+"분");
//            } else {
//                txt_readyTime.setText(readyTime.getHour()+"시간 "+readyTime.getMin()+"분");
//            }

            UtilTimeDomain u = UtilTimeDomain.getInstance();
            TimeDomain goalTime = u.getArriveTime();

            int i_goalTimeHour = goalTime.getHour();
            String goalTimeApmPm = getAmPm(i_goalTimeHour);
            if(i_goalTimeHour > 12){
                i_goalTimeHour -= 12;
            }
//            txt_goalTime.setText(goalTimeApmPm+" "+i_goalTimeHour+"시 "+goalTime.getMin()+"분");


            int startTime = calculateStartTime(totalTime,readyTime,goalTime);
            int startTIme_hour = startTime / 60;
            int startTIme_minutes = startTime % 60;
            String startTimeAmpm = getAmPm(startTIme_hour);
            if(startTIme_hour > 12){
                startTIme_hour-= 12;
            }
            System.out.println("기상시간 시  : " +startTIme_hour);
            System.out.println("기상시간 분  : " +startTIme_minutes);
            wakeup_time.setText(startTimeAmpm+" "+startTIme_hour+"시 "+startTIme_minutes+"분");



            /* 출발시간 */
            int goalTimeTransMin = (goalTime.getHour() * 60) + goalTime.getMin();
            int totalTimeTransMin = (totalTime.getHour() * 60) + totalTime.getMin();
            int goal_total_diff = (goalTimeTransMin - totalTimeTransMin);

            long start_hour = TimeUnit.MINUTES.toHours(goal_total_diff); //
            long start_minutes = TimeUnit.MINUTES.toMinutes(goal_total_diff) - TimeUnit.HOURS.toMinutes(start_hour);

            String startTimeAmpm2 = getAmPm((int) start_hour);
            if(start_hour > 12){
                start_hour-= 12;
            }
            System.out.println("출발시간 시  : " +start_hour);
            System.out.println("출발시간 분  : " +start_minutes);
            start_time.setText(startTimeAmpm2+" "+start_hour+"시 "+start_minutes+"분");

            helper = new AlarmDatabaseOpenHelper(Input6Activity.this, null , null, 2);
            database = helper.getWritableDatabase();
            DateService dateService = new DateService();
            HashMap<String , Object> result = new HashMap<>();

            int paramsetId = (int) System.currentTimeMillis();
            long now = System.currentTimeMillis();

            result = dateService.calDateDiff(startTIme_hour,startTIme_minutes,00,
                    Integer.parseInt(dateService.getCurrentHour()),Integer.parseInt(dateService.getCurrentMinutes()),Integer.parseInt(dateService.getCurrentSec()));

            /* 이 최종 시간 뒤에 알람이 울리게 된다. */
            /* 이 시간으로 알람이 언제 울리는지 생성. */
            System.out.println("최종 시간 : " + result.get("hour"));
            System.out.println("최종 시간 : " + result.get("minutes"));


//
//            int paramsetHour = Integer.parseInt((String) result.get("hour"));
//            int paramsetMinutes = Integer.parseInt((String) result.get("minutes"));
            int paramsetHour = startTIme_hour;
            int paramsetMinutes = startTIme_minutes;
            String paramsetName = "최초알람";
            int paramsetOnOff = 0;

            Alarm alarm = new Alarm(paramsetId,paramsetHour,paramsetMinutes,paramsetName,paramsetOnOff);

            alarm.setGoal_h(i_goalTimeHour);
            alarm.setGoal_m(goalTime.getMin());

            alarm.setMove_h(totalTime.getHour());
            alarm.setMove_m(totalTime.getMin());
            alarm.setReady_h(readyTime.getHour());
            alarm.setReady_m(readyTime.getMin());
            alarm.setStart_h((int) start_hour);
            alarm.setStart_m((int) start_minutes);
            alarm.setUp_h(startTIme_hour);
            alarm.setUp_m(startTIme_minutes);
            alarm.setStart_address(start_address);
            alarm.setEnd_address(end_address);
            alarm.setTotalPathStationId(totalPathStationId);
            alarm.setTotalPathStationName(totalPathStationName);
            alarm.setBusId(totalBusId);
            alarm.setBusNo(totalbusNo);
            alarm.setWarkTime(warkTime);


            // 약속시간 구하기
            int goalTotalTime =  (alarm.getGoal_h() * 60) + alarm.getGoal_m();


            AddressDomain addressDomain = new AddressDomain();
            addressDomain.setSaddress(alarm.getStart_address());
            addressDomain.setEaddress(alarm.getEnd_address());
            OntimeService searchPathService = new OntimeService(addressDomain,null,null);

            // 정류장 좌표 얻기
            System.out.println("#197 정류장 이름 : " + alarm.getTotalPathStationName());
            System.out.println("#198 정류장 번호 : " + Integer.toString(alarm.getTotalPathStationId()));
            BusDomain busDomain = searchPathService.searchStation(alarm.getTotalPathStationName(), Integer.toString(alarm.getTotalPathStationId()));
            double stationX =  busDomain.getX();
            double stationY = busDomain.getY();

            // 도착지 좌표 얻기
            locationDomain lod =  searchPathService.getLocationByAddress(alarm.getEnd_address());

            String endX = lod.getX();
            String endY = lod.getY();

            System.out.println("정류장 시작 주소 : " + Double.toString(stationX));
            System.out.println("정류장 시작 주소 : " + Double.toString(stationY));
            System.out.println("도착지 시작 주소 : " + endX);
            System.out.println("도착지 시작 주소 : " + endY);
            // 정류장과 도착지까지의 길찾기
            pathDomain station_to_arrive = searchPathService.searchPathByLocation( Double.toString(stationX) , Double.toString(stationY) , endX,endY);
            if(station_to_arrive.getResult() != null){
                // 정류장과 도착지까지의 걸리는 시간
                int st_to_ar_min = station_to_arrive.getResult().getPath().get(0).getInfo().getTotalTime();
                System.out.println("st_to_ar_min : " + st_to_ar_min);


                // 약속시간을 분으로 포맷
                int gTotalMin = (alarm.getGoal_h()*60) + alarm.getGoal_m();
                System.out.println("gTotalMin : " + gTotalMin);

                // 약속시간 - 정류장에서 도착지까지의 시간
                int rangeTime = gTotalMin - st_to_ar_min;
                System.out.println("rangeTime : " + rangeTime);

                // 나온 range 를 시하고 분으로 나눔
                long st_to_ar_min_h = TimeUnit.MINUTES.toHours(rangeTime); //
                System.out.println("st_to_ar_min_h : " + st_to_ar_min_h);
                long st_to_ar_min_m = TimeUnit.MINUTES.toMinutes(rangeTime) - TimeUnit.HOURS.toMinutes(st_to_ar_min_h);
                System.out.println("st_to_ar_min_m : " + st_to_ar_min_m);

                alarm.setRange_h((int) st_to_ar_min_h);
                alarm.setRange_m((int) st_to_ar_min_m);
            }



//            wakeup_time.setText(startTime / 60+"시 "+startTime % 60+"분");

            long l_ret_h = (long) result.get("hour");
            long l_ret_m = (long) result.get("minutes");

            long ret_sec = ((l_ret_h * 60)*60)  + (l_ret_m * 60);
            System.out.println("그냥 초 : "+ret_sec );
            System.out.println("그냥 밀리초 : "+ (ret_sec * 1000) );
            long ret_msec = ret_sec * 1000;

            alarm_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /* SQLite 데이터베이스에 알람 추가*/
                    helper.insert(alarm);

                    /* 실질적인 알람 셋팅 */
                    Intent intent = new Intent(Input6Activity.this, ShowAlarm.class);
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    PendingIntent pendingIntent = PendingIntent.getActivity(Input6Activity.this, REQUEST_CODE,
                            intent, 0);

                    int alarmType = AlarmManager.ELAPSED_REALTIME;
                    System.out.println(ret_msec+"밀리초 뒤에 알람이 울릴예정임!");
//                    final int FIFTEEN_SEC_MILLIS = (int)ret_msec;
                    final int FIFTEEN_SEC_MILLIS = 5000;
                    AlarmManager alarmManager = (AlarmManager)
                            Input6Activity.this.getSystemService(Input6Activity.this.ALARM_SERVICE);

                    alarmManager.set(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,pendingIntent);
                    Toast.makeText(Input6Activity.this , l_ret_h+"시 " +l_ret_m+"분 뒤에 알람이 울립니다...",Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(Input6Activity.this, MainActivity.class);
                    startActivity(intent2);
                    finish();
                }
            });




        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private int calculateStartTime(TimeDomain totaltime , TimeDomain readyTime , TimeDomain goalTime ) throws ParseException {
        System.out.println("calculateStartTime 목표도착시간 (시): "+goalTime.getHour());
        System.out.println("calculateStartTime 목표도착시간 (분): "+goalTime.getMin());
        int goalTimeMin =( goalTime.getHour() * 60) +  goalTime.getMin();
        System.out.println("calculateStartTime 목표도착시간 (총 분): "+goalTimeMin);


        System.out.println("calculateStartTime 소요시간 (시): "+totaltime.getHour());
        System.out.println("calculateStartTime 소요시간 (분): "+totaltime.getMin());
        int totalTimeMin = ( totaltime.getHour() * 60) +  totaltime.getMin();
        System.out.println("calculateStartTime 소요시간 (총 분): "+totalTimeMin);

        int total = totalTimeMin + readyTime.getMin();  // 준비시간은 분 만 더함

        return (goalTimeMin - total) ;


    }

    private String getAmPm(int hourOfDay){
        String AM_PM ;
        if(hourOfDay < 12) {
            AM_PM = "오전";
        } else {
            AM_PM = "오후";
        }
        return AM_PM;
    }

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            AppFinish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "one more", Toast.LENGTH_SHORT).show();
        }
    }

    public void AppFinish(){
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
