package com.izen1231.ontime.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.izen1231.ontime.R;
import com.izen1231.ontime.database.AlarmDatabaseOpenHelper;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.fragment.FourFragment;
import com.izen1231.ontime.fragment.OneAlarmingFragment;
import com.izen1231.ontime.fragment.OneFragment;
import com.izen1231.ontime.fragment.ThreeFragment;
import com.izen1231.ontime.fragment.TwoFragment;
import com.izen1231.ontime.model.Alarm;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener {
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private OneFragment onefragment = new OneFragment();
    private OneAlarmingFragment oneAlarmingFragment = new OneAlarmingFragment();
    private TwoFragment twofragment = new TwoFragment();
    private ThreeFragment threefragment = new ThreeFragment();
    private FourFragment fourfragment = new FourFragment();

    public static TextToSpeech myTTS;

    AlarmDatabaseOpenHelper helper;
    SQLiteDatabase database;
    Cursor cursor;
    String sql;

    //    Button btn_apiTest;
// 여기는 applyAlarm 브랜치 입니다.!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);

        myTTS = new TextToSpeech(this,this);

//        // API 호출시 StrictMode 무시
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        helper = new AlarmDatabaseOpenHelper(MainActivity.this, null , null, 2);
        database = helper.getWritableDatabase();
//        helper.deleteTable(database);


        List<Alarm> alarmList = helper.queryAlarmTable();

        System.out.println("사이즈 : "+alarmList.size());
        if(alarmList.size() > 0){
            for(int i =0 ; i<alarmList.size() ; i++){
                System.out.println( i +"번쨰 값 : "+alarmList.get(i));
            }
        }

        Intent g_intent = getIntent();
        // 클릭버
        if((!g_intent.hasExtra("isShowAlarm"))){
            Intent intent=new Intent(MainActivity.this,ViewPagerActivity.class);
            startActivity(intent);
        } else {
            BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
            // 첫 화면 지정
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //transaction.replace(R.id.frame_layout, oneAlarmingFragment).commitAllowingStateLoss();
            transaction.replace(R.id.frame_layout, onefragment).commitAllowingStateLoss();

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    switch (item.getItemId()) {
                        case R.id.navigation_alarm: {
                            //transaction.replace(R.id.frame_layout, oneAlarmingFragment).commitAllowingStateLoss();
                            transaction.replace(R.id.frame_layout, onefragment).commitAllowingStateLoss();
                            break;
                        }
                        case R.id.navigation_schedule: {
                            transaction.replace(R.id.frame_layout, twofragment).commitAllowingStateLoss();
                            break;
                        }
                        case R.id.navigation_move: {
                            transaction.replace(R.id.frame_layout, threefragment).commitAllowingStateLoss();
                            break;
                        }
                        case R.id.navigation_mypage: {
                            transaction.replace(R.id.frame_layout, fourfragment).commitAllowingStateLoss();
                            break;
                        }
                    }

                    return true;
                }
            });
        }

        System.out.println("메인 해즈 : " + g_intent.hasExtra("isShowAlarm"));

//        String isShowAlarm =g_intent.getExtras().getString("isShowAlarm");
        // 알람이 없으면
//        if(alarmList.size() < 1){
                // 알람이 등록 안되어있을때 스플래쉬 알람 추가 띄우기
        // 만약 알람 끄기로 넘어온거라면
//            isShowAlarm

        database.close();
        helper.close();
//        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTTS.shutdown();
    }

    @Override
    public void onInit(int i) {
        String myText1 = "안녕하세요.";
        String myText2 = "안녕하세요";

        myTTS.setLanguage(Locale.KOREA);
    }
}


//        btn_apiTest = (Button) findViewById(R.id.btn_apiTest);
//
//        btn_apiTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String start = "경수대로 665번길 75";
//                String end = "퇴계로 286";
//                Call<pathDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().searchPathByAddress(start,end);
//                try {
//                    Response<pathDomain> res = test.execute();
//                    // res.body
//                    System.out.println("res ===> : " +res);
//                    System.out.println("res.body ===> : " +res.body());
//                    System.out.println("res.body.getStartRadius ===> : " +res.body().getResult().getStartRadius());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });



