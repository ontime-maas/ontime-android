package com.izen1231.ontime.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateService {
    public HashMap<String , Object> calDateDiff(int fh, int fm , int fs , int eh , int em , int es) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        HashMap<String , Object> map = new HashMap<>();
        String s_fh;
        String s_fm;
        String s_fs;
        String s_eh;
        String s_em;
        String s_es;
        Boolean isOverHour  = false;

        if(fh < 11) {
            s_fh = convertFormat(fh);
        } else {
            s_fh = Integer.toString(fh);
        }
        if(fm < 11) {
            s_fm = convertFormat(fm);
        } else {
            s_fm = Integer.toString(fm);
        }
        if(fs < 11) {
            s_fs = convertFormat(fs);
        } else {
            s_fs = Integer.toString(fs);
        }

        if(eh < 11) {
            s_eh = convertFormat(eh);
        } else {
            s_eh = Integer.toString(eh);
        }
        if(em < 11) {
            s_em = convertFormat(em);
        } else {
            s_em = Integer.toString(em);
        }
        if(es < 11) {
            s_es = convertFormat(es);
        } else {
            s_es = Integer.toString(es);
        }

        String firstReult = s_fh+":"+s_fm+":"+s_fs;
        Date first = f.parse(firstReult);

        String secondResult = s_eh+":"+s_em+":"+s_es;
        Date end = f.parse(secondResult);

        System.out.println("firstReult : " + firstReult);
        System.out.println("secondResult : " + secondResult);

        long diff;

        if(first.getTime()  < end.getTime()){
            diff = end.getTime() - first.getTime();
            isOverHour =  true;
        } else {
            diff = first.getTime() - end.getTime();
        }


        System.out.println("diff : " +diff);
        System.out.println("sec : " +diff/1000);
        System.out.println("minutes : " +diff/60000);
        System.out.println("hour : " +diff/3600000);

        long needMinutes = diff/60000;
        long hour = TimeUnit.MINUTES.toHours(needMinutes); //
        long minutes = TimeUnit.MINUTES.toMinutes(needMinutes) - TimeUnit.HOURS.toMinutes(hour);
        if(isOverHour){
            hour  = 24 - hour;
        }
        System.out.println("hour : " +hour);
        System.out.println("minutes : " +minutes);

        map.put("hour",hour);
        map.put("minutes",minutes);

        return map;
    }

    private String getCurrentTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("a:HH:mm:ss");
        return currentTimeFormat.format(date);
    }

    public String getCurrentHour(){
        String currentTime = getCurrentTime();
        String h   = currentTime.split(":")[1];
        return h;
    }
    public String getCurrentMinutes(){
        String currentTime = getCurrentTime();
        String m = currentTime.split(":")[2];
        return m;
    }
    public String getCurrentSec(){
        String currentTime = getCurrentTime();
        String s = currentTime.split(":")[3];
        return s;
    }

    public String getCurrentAmPm(){
        String currentTime = getCurrentTime();
        String ampm =  currentTime.split(":")[0];
        return ampm;
    }

    private String convertFormat(int t){
            return "0"+t;
    }
}
