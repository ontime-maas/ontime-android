package com.izen1231.ontime.service;

import com.izen1231.ontime.domain.AddressDomain;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.domain.TotalAlarmTimeDomain;
import com.izen1231.ontime.domain.bus.BusDomain;
import com.izen1231.ontime.domain.live.LiveDomain;
import com.izen1231.ontime.domain.locationDomain;
import com.izen1231.ontime.domain.path.pathDomain;
import com.izen1231.ontime.httpService.RetrofitOntime;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class OntimeService {
    String start_address;
    String end_address;
    pathDomain path_ret;
    pathDomain path_ret2;
    BusDomain bus_ret;
    String arsID;
    LiveDomain liveStationList;
    BusDomain station_ret;
    locationDomain loc_ret;

    public OntimeService() {
    }

    public OntimeService(AddressDomain addressDomain,
                         TimeDomain arriveTime,
                         TimeDomain readyTime) {

        this.start_address = addressDomain.getSaddress();
        this.end_address = addressDomain.getEaddress();
    }

    public locationDomain getLocationByAddress(String address){

        Call<locationDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().getLocationByAddress(address);
        try {
            Response<locationDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("res.body ===> : " +res.body());
            this.loc_ret = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.loc_ret;
    }

    public pathDomain searchPathByLocation(String sx, String sy, String ex, String ey){
        Call<pathDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().searchPathByLocation(sx,sy,ex,ey,"100");
        try {
            Response<pathDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("res.body ===> : " +res.body());
            this.path_ret2 = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.path_ret2;
    }
    public BusDomain searchStation(String stationName ,String stationId){

        Call<BusDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().searchBusStation(stationName,stationId);
        try {
            Response<BusDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("res.body ===> : " +res.body());
            this.station_ret = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.station_ret;
    }

    public pathDomain searchPathbyAddress(){

        System.out.println("출발지 : " +this.start_address);
        System.out.println("도착지 : " +this.end_address);
        Call<pathDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().searchPathByAddress(this.start_address,this.end_address);
        try {
            Response<pathDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("res.body ===> : " +res.body());
            this.path_ret = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.path_ret;
    }

    public BusDomain getArsIdByBusStation(String stationName , String stationId){

        Call<BusDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().getArsIdByBusStation(stationName,stationId);
        try {
            Response<BusDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("res.body ===> : " +res.body());
            this.bus_ret = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.bus_ret;
    }

    public String getArsId(String stationName , String stationId){
        System.out.println("getArsId : res.stationName ===> : " +stationName);
        System.out.println("getArsId : stationId.body ===> : " +stationId);

        Call<BusDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().getArsIdByBusStation(stationName,stationId);
            try {
            Response<BusDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("getArsId : res.body ===> : " +res.body());
            this.arsID = res.body().getArsID();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.arsID;
    }

    public LiveDomain getLiveBusStation(String arsId){

        System.out.println("getLiveBusStation arsId : "+ arsId);

        System.out.println("beeffor : "+ arsId);

        Call<LiveDomain> test = RetrofitOntime.getInstance().getOnTimeRetrofitService().searchLiveBusArrive(arsId);

        try {
            System.out.println("after : "+ arsId);
            Response<LiveDomain> res = test.execute();
            // res.body
            System.out.println("res ===> : " +res);
            System.out.println("getArsId : res.body ===> : " +res.body());
            this.liveStationList = res.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.liveStationList;
    }

}
