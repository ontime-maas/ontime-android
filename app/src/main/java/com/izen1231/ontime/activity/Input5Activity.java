package com.izen1231.ontime.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import com.izen1231.ontime.R;
import com.izen1231.ontime.domain.AddressDomain;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.domain.UtilTimeDomain;
import com.izen1231.ontime.domain.path.SubPathList;
import com.izen1231.ontime.domain.path.pathDomain;
import com.izen1231.ontime.service.OntimeService;

import java.util.HashMap;
import java.util.List;

public class Input5Activity extends AppCompatActivity {

    public final static int BUS_TRAFFIC_TYPE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input5);

        Intent intent = getIntent();

        String start_address = intent.getExtras().getString("startAddress");
        String end_address = intent.getExtras().getString("endAddress");

        System.out.println("인텐트 출발장소 " +start_address);
        System.out.println("인텐트 도착장소 " +end_address);

        String arrive_ampm = intent.getExtras().getString("arrive_ampm");
        int arrive_h = intent.getExtras().getInt("arrive_h");
        int arrive_m = intent.getExtras().getInt("arrive_m");

        System.out.println("인텐트 도착시간 (오전오후?) " +arrive_ampm);
        System.out.println("인텐트 도착시간 (시) " +arrive_h);
        System.out.println("인텐트 도착시간 (분)" +arrive_m);

        int ready_h = intent.getExtras().getInt("ready_h");
        int ready_m = intent.getExtras().getInt("ready_m");

        System.out.println("인텐트 준비시간 (시)" +ready_h);
        System.out.println("인텐트 준비시간 (분)" +ready_m);

        AddressDomain addressDomain = AddressDomain.getInstance();
        addressDomain.setSaddress(start_address);
        addressDomain.setEaddress(end_address);

        TimeDomain arriveTime = new TimeDomain();
        arriveTime.setAmORpm(arrive_ampm);
        arriveTime.setHour(arrive_h);
        arriveTime.setMin(arrive_m);

        TimeDomain readyTime = new TimeDomain();

        readyTime.setHour(ready_h);
        readyTime.setMin(ready_m);

        OntimeService ontimeService = new OntimeService(addressDomain,
                arriveTime , readyTime);

        pathDomain pathParam =ontimeService.searchPathbyAddress();

        System.out.println("pathdomain ---- -> : " + pathParam);

        int totalTimeData = pathParam.getResult().getPath().get(0).getInfo().getTotalTime(); // 이동 소요 시간
        int totalTimeHours = totalTimeData/60;
        int totalTimeMinutes = totalTimeData%60;
        int warkTime = pathParam.getResult().getPath().get(0).getSubPath().get(0).getSectionTime();
        // 첫번재 탈 버스정류장 이름과 버스정류장 아이디 찾기

        BusMetaData busMetaData = getFirstStationId(pathParam.getResult().getPath().get(0).getSubPath());

        String totalPathStationName = pathParam.getResult().getPath().get(0).getInfo().getFirstStartStation();
        int totalPathStationId = busMetaData.getStartId();
        String totalbusNo = busMetaData.getBusNo();
        int totalBusId = busMetaData.getBusId();


//        getStartID() == BUS_TRAFFIC_TYPE

        System.out.printf("%d:%02d\n",totalTimeHours,totalTimeMinutes);

        Intent nextIntent =new Intent(Input5Activity.this, Input6Activity.class);

        TimeDomain totalTime = new TimeDomain();
        totalTime.setHour(totalTimeHours);
        totalTime.setMin(totalTimeMinutes);

        nextIntent.putExtra("totalTime",  totalTime);
        nextIntent.putExtra("readyTime", readyTime );
        nextIntent.putExtra("start_address", start_address );
        nextIntent.putExtra("end_address", end_address );
        nextIntent.putExtra("totalPathStationName",totalPathStationName);
        nextIntent.putExtra("totalPathStationId",totalPathStationId);
        nextIntent.putExtra("totalbusNo",totalbusNo);
        nextIntent.putExtra("totalBusId",totalBusId);
        nextIntent.putExtra("warkTime",warkTime);

        System.out.println("시작 --------------");

        System.out.println("totalTime"+  totalTime);
        System.out.println("readyTime"+ readyTime );
        System.out.println("start_address"+ start_address );
        System.out.println("end_address"+ end_address );
        System.out.println("totalPathStationName"+totalPathStationName);
        System.out.println("totalPathStationId"+totalPathStationId);
        System.out.println("totalbusNo"+totalbusNo);
        System.out.println("totalBusId"+totalBusId);
        System.out.println("warkTime"+warkTime);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(nextIntent);
            }
        } , 2000);
    }

    private BusMetaData getFirstStationId(List<SubPathList> subPathList){

        BusMetaData busMetaData = new BusMetaData();

        for(int i = 0; i< subPathList.size(); i++) {
            if (subPathList.get(i).getTrafficType() == BUS_TRAFFIC_TYPE) {
                busMetaData.setStartId(subPathList.get(i).getStartID());
                busMetaData.setBusNo(subPathList.get(i).getLane().get(0).getBusNo());
                busMetaData.setBusId(subPathList.get(i).getLane().get(0).getBusID());
                break;
            }
        }
        return busMetaData;
    }

    class BusMetaData{
        int startId;
        String busNo;
        int busId ;

        public int getStartId() {
            return startId;
        }

        public void setStartId(int startId) {
            this.startId = startId;
        }

        public String getBusNo() {
            return busNo;
        }

        public void setBusNo(String busNo) {
            this.busNo = busNo;
        }

        public int getBusId() {
            return busId;
        }

        public void setBusId(int busId) {
            this.busId = busId;
        }
    }
}
