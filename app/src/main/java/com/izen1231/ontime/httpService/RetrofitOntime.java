package com.izen1231.ontime.httpService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitOntime {
    private static RetrofitOntime ourInstance = new RetrofitOntime();

    public static RetrofitOntime getInstance() {
        return ourInstance;
    }

    private  RetrofitOntime(){}

    Retrofit onTimeRetrofit = new Retrofit.Builder()
            .baseUrl("http://106.10.37.11:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitOntimeService onTimeRetrofitService = onTimeRetrofit.create(RetrofitOntimeService.class);

    public RetrofitOntimeService getOnTimeRetrofitService() {
        return onTimeRetrofitService;
    }

}
