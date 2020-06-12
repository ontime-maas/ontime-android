package com.izen1231.ontime.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimeDomain implements Serializable {

    private static TimeDomain timeDomain;

    String amORpm;
    int hour;
    int min;

    public synchronized static TimeDomain getInstance(){
        if(timeDomain == null){
            timeDomain = new TimeDomain();
        }
        return timeDomain;
    }

    public String getAmORpm() {
        return amORpm;
    }

    public synchronized void setAmORpm(String amORpm) {
        this.amORpm = amORpm;
    }

    public int getHour() {
        return hour;
    }

    public synchronized void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public synchronized void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "TimeDomain{" +
                "amORpm='" + amORpm + '\'' +
                ", hour='" + hour + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
