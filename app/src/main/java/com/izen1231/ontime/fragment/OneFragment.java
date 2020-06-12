package com.izen1231.ontime.fragment;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gun0912.tedpermission.util.ObjectUtils;
import com.izen1231.ontime.R;
import com.izen1231.ontime.database.AlarmDatabaseOpenHelper;
import com.izen1231.ontime.domain.live.LiveDomain;
import com.izen1231.ontime.domain.live.list;
import com.izen1231.ontime.model.Alarm;
import com.izen1231.ontime.service.OntimeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class OneFragment extends Fragment implements TextToSpeech.OnInitListener {
    AlarmDatabaseOpenHelper helper;
    SQLiteDatabase database;
    FragmentPagerAdapter adapterViewPager;
    TextView set_time;
    TextView txt_sub_msg;
    TextView main_time;
    Button btn_tts;
    private TextToSpeech tts;
    list ret;
    TextView main_day;
    TextView main_weather;
//    TextView main_time;
    TextView main_weather2;
    EditText main_start;
    EditText main_end;
    ImageView down;
    ImageView main_photo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tts = new TextToSpeech(getContext(),this);
//        tts.setLanguage(Locale.KOREA);
        return inflater.inflate(R.layout.activity_one_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.ret = new list();

//        ViewPager main_viewpager2 = (ViewPager) view.findViewById(R.id.main_viewpager2);
//        adapterViewPager = new MyPagerAdapter(getFragmentManager());
//        main_viewpager2.setAdapter(adapterViewPager);

        set_time = (TextView)view.findViewById(R.id.set_time);
        main_start = (EditText)view.findViewById(R.id.main_start);
        main_end = (EditText)view.findViewById(R.id.main_end);
        down = (ImageView)view.findViewById(R.id.down);
        main_day = (TextView)view.findViewById(R.id.main_day);
        main_weather = (TextView)view.findViewById(R.id.main_weather);
        main_time = (TextView)view.findViewById(R.id.main_time);
        main_weather2 = (TextView)view.findViewById(R.id.main_weather2);
        main_photo = (ImageView)view.findViewById(R.id.main_photo);
        txt_sub_msg = (TextView)view.findViewById(R.id.txt_sub_msg);
        main_time = (TextView)view.findViewById(R.id.main_time);
//        btn_tts = (Button)view.findViewById(R.id.btn_tts);

        helper = new AlarmDatabaseOpenHelper(getActivity(), null , null, 2);
        database = helper.getWritableDatabase();

        // 가장 최근에 등록된 알람 가져오기
        List<Alarm> alarmList = helper.queryAlarmTable();

        // 알람이 있으면
        if(alarmList.size() > 0){
            Alarm lastAlarm = alarmList.get(alarmList.size()-1);
            System.out.println("가장 최근의 알람 !  : "+lastAlarm);
            System.out.println("사이즈 : "+alarmList.size());

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("HH-mm");

            String getTime = sdf.format(date);
            String currentH = getTime.split("-")[0];
            String currentM = getTime.split("-")[1];
            int currentTotalM = (Integer.parseInt(currentH ) * 60) + Integer.parseInt(currentM);
            // 최근 알람 시간이 현재시간보다 초과가 되이씅면 알람이 울렸다고 가정 .
            System.out.println("현재시간 : "  + Integer.parseInt(currentH)+ "시  " + currentM + "분");
            int lastAlarmTotalM = (lastAlarm.getHour_x() * 60) + lastAlarm.getMinute_x();

            LeftTimeModel leftTimeModel = getStartLeftTime(lastAlarm);

            main_start.setText(lastAlarm.getStart_address());
            main_end.setText(lastAlarm.getEnd_address());
            if(leftTimeModel.getLeftHour() < 1){
                main_time.setText(leftTimeModel.getLeftMinutes()+"분 남았어요");
            }else{
                main_time.setText(leftTimeModel.getLeftHour()+"시"+leftTimeModel.getLeftMinutes()+"분 남았어요");
            }

//            tts.speak("영서귀요요요요요미미미미미미ㅣ밈",TextToSpeech.QUEUE_FLUSH,null,null);

            Timer timer = new Timer();

            TimerTask TT = new TimerTask() {
                private list ret;

                @Override
                public void run() {

                   int uptime = (lastAlarm.getHour_x() * 60) + lastAlarm.getMinute_x();
                   LeftTimeModel leftTimeModel = getStartLeftTime(lastAlarm);
                   int sTime = (leftTimeModel.getLeftHour() * 60) + leftTimeModel.getLeftMinutes();

                   int currentTime = ( Integer.parseInt(getCurrentTime().split(":")[0]) * 60) + Integer.parseInt(getCurrentTime().split(":")[1]);

//                   getActivity().runOnUiThread(new Runnable() {
//                       @Override
//                       public void run() {
////                           if(uptime + sTime >= currentTime){
////                               main_time.setText("출발하세요!");
////                           } else {
////                               if(leftTimeModel.getLeftHour() < 1){
////                                   main_time.setText(leftTimeModel.getLeftMinutes()+"분 남았어요");
////                               } else {
////                                   main_time.setText(leftTimeModel.getLeftHour()+"시간 "+leftTimeModel.getLeftMinutes()+"분 남았어요");
////                               }
////                           }
//                       }
//                   });
//                    if()
                    // 반복실행할 구문
                    //MainActivity.myTTS.speak("앙",TextToSpeech.QUEUE_FLUSH,null,null);
                    // 1.현재 실시간 버스를 계속 가져온다.
//                    ret = fetchLiveStationList(lastAlarm);
                    System.out.println("타이머 !! " +ret);
                    // 3. 라스트 알람의 range 를 구한다. (분)
                    int range_h = lastAlarm.getRange_h();
                    int range_m = lastAlarm.getRange_m();
                    // 정류장에서 목적지까지 거리계산이 안되는 경우

//                    if(range_h == 0 && range_m == 0){
//                        String msg = ret.getArrmsg1()+" 버스가 도착합니다.";
//                        System.out.println("tts 메세지 : " + msg);
//                        tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,null);
//                    } else {
//                        System.out.println("range_h" + range_h);
//                        System.out.println("range_m" + range_m);
//
//                        int max_rangeMinutes = (range_h*60)+range_m;
//                        int min_rangeMinutes = max_rangeMinutes - 10;
//
//                        System.out.println("max_rangeMinutes" + max_rangeMinutes);
//                        System.out.println("min_rangeMinutes" + min_rangeMinutes);
//
//                        int currentH = Integer.parseInt(getCurrentTime().split(":")[0]);
//                        int currentM = Integer.parseInt(getCurrentTime().split(":")[1]);
//                        int totalCurrent = (currentH*60)+currentM;
//
//                        System.out.println("getcurrent H" + getCurrentTime().split(":")[0]);
//                        System.out.println("getcurrent M" + getCurrentTime().split(":")[1]);
//                        System.out.println("totalCurrent" + totalCurrent);
//
//                        // 4. 정류장까지 가는 도보시간 + 현재시간 + 버스 오는시간 이 range 안이면 알람
//                        String reg = "(?=분)";
//                        if(!ret.getArrmsg1().split(reg)[0].equals("곧 도착")){
//                            int alarmTime = lastAlarm.getWarkTime() + totalCurrent + Integer.parseInt(ret.getArrmsg1().split(reg)[0]);
//                            if(alarmTime>=min_rangeMinutes && alarmTime<=max_rangeMinutes){
//                                Toast.makeText(getContext(),"알람!",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
                }
            };

            timer.schedule(TT, 0, 5000); //Timer 실행
//            timer.cancel();//타이머 종료

            // 알람이 진행중일 경우
            if(lastAlarm.getOnOff() == 1){
            }
            // 그냥 평상시 앱을 켰을경우
            if(lastAlarm.getOnOff() == 0){
            }
        } else {

        }
    }



    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

    public LeftTimeModel getStartLeftTime(Alarm lastAlarm){
        int goal = (lastAlarm.getGoal_h()*60)+lastAlarm.getGoal_m();
        int start = (lastAlarm.getStart_h()*60)+lastAlarm.getStart_m();
        int diff = goal-start;

        long start_hour = TimeUnit.MINUTES.toHours(diff); //
        long start_minutes = TimeUnit.MINUTES.toMinutes(diff) - TimeUnit.HOURS.toMinutes(start_hour);

//        if(start_hour > 12){
//            start_hour-= 12;
//        }
        LeftTimeModel leftTimeModel = new LeftTimeModel();
        leftTimeModel.setLeftHour((int) start_hour);
        leftTimeModel.setLeftMinutes((int) start_minutes);
        return leftTimeModel;
    }

    public list fetchLiveStationList(Alarm lastAlarm){

        OntimeService ontimeService = new OntimeService();
        System.out.println("#fetchLiveStationList 정류장 이름" + lastAlarm.getTotalPathStationName());
        System.out.println("#fetchLiveStationList 정류장 아이디" + Integer.toString(lastAlarm.getTotalPathStationId()));

        String arsId = ontimeService.getArsId(lastAlarm.getTotalPathStationName(), Integer.toString(lastAlarm.getTotalPathStationId()));

        // arsid 를 이용해 내가 첫번째로 이용해야할 정류장 정보 가져오기
        System.out.println("여긴됨 !" + arsId);
        LiveDomain liveStationList = ontimeService.getLiveBusStation(arsId);
        System.out.println("여긴안됨 !" + liveStationList);

        if( !(ObjectUtils.isEmpty(liveStationList.getList())) ){
            for(int i=0 ; i<liveStationList.getList().size(); i++){
                if(liveStationList.getList().get(i).getRtNm().equals(lastAlarm.getBusNo())){
                    this.ret = liveStationList.getList().get(i);
                    break;
                }
            }
        }


        return this.ret;
    }

    private String getCurrentTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("HH:mm");
        return currentTimeFormat.format(date);
    }

    @Override
    public void onInit(int i) {
        tts.setLanguage(Locale.KOREA);
    }

    class LeftTimeModel{
        int leftHour;
        int leftMinutes;

        public int getLeftHour() {
            return leftHour;
        }

        public void setLeftHour(int leftHour) {
            this.leftHour = leftHour;
        }

        public int getLeftMinutes() {
            return leftMinutes;
        }

        public void setLeftMinutes(int leftMinutes) {
            this.leftMinutes = leftMinutes;
        }
    }
}
