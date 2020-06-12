package com.izen1231.ontime.httpService;

import com.izen1231.ontime.domain.bus.BusDomain;
import com.izen1231.ontime.domain.live.LiveDomain;
import com.izen1231.ontime.domain.locationDomain;
import com.izen1231.ontime.domain.path.pathDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitOntimeService {

    @GET("address/location")
    Call<locationDomain> getLocationByAddress(
            @Query("address") String address);

    @GET("path/search/location")
    Call<pathDomain> searchPathByLocation(
            @Query("sx") String sx,
            @Query("sy") String sy,
            @Query("ex") String ex,
            @Query("ey") String ey,
            @Query("maxPath") String maxPath);

    @GET("path/search/address")
    Call<pathDomain> searchPathByAddress(
            @Query("startAddress") String startAddress,
            @Query("endAddress") String endAddress);

    @GET("bus/station")
    Call<BusDomain> searchBusStation(
            @Query("stationName") String stationName,
            @Query("stationId") String stationId);

    @GET("bus/station/arsid")
    Call<BusDomain> getArsIdByBusStation(
            @Query("stationName") String stationName,
            @Query("stationId") String stationId);

    // 실시간도착정보
    @GET("bus/station/arrive")
    Call<LiveDomain> searchLiveBusArrive(
            @Query("arsId") String arsId);

}
