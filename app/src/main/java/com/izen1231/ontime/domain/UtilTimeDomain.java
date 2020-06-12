package com.izen1231.ontime.domain;

public class UtilTimeDomain extends TimeDomain {
    private static UtilTimeDomain utilTimeDomain;

    TimeDomain arriveTime;
    TimeDomain readyTime;

    public synchronized static UtilTimeDomain getInstance(){
        if(utilTimeDomain == null){
            utilTimeDomain = new UtilTimeDomain();
        }
        return utilTimeDomain;
    }

    public TimeDomain getArriveTime() {
        return arriveTime;
    }

    public synchronized void setArriveTime(TimeDomain arriveTime) {
        this.arriveTime = arriveTime;
    }

    public TimeDomain getReadyTime() {
        return readyTime;
    }

    public synchronized void setReadyTime(TimeDomain readyTime) {
        this.readyTime = readyTime;
    }

    @Override
    public String toString() {
        return "UtilTimeDomain{" +
                "amORpm='" + amORpm + '\'' +
                ", hour='" + hour + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
