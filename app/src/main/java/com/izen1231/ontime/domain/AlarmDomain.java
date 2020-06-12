package com.izen1231.ontime.domain;

public class AlarmDomain {

    private static AlarmDomain alarmDomain;


    String spendTime;
    String readyTime;
    TimeDomain startTime;
    TimeDomain upTime;
    TimeDomain endTime;


    public synchronized static AlarmDomain getInstance(){
        if(alarmDomain == null){
            alarmDomain = new AlarmDomain();
        }
        return alarmDomain;
    }


    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public String getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(String readyTime) {
        this.readyTime = readyTime;
    }

    public TimeDomain getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeDomain startTime) {
        this.startTime = startTime;
    }

    public TimeDomain getUpTime() {
        return upTime;
    }

    public void setUpTime(TimeDomain upTime) {
        this.upTime = upTime;
    }

    public TimeDomain getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeDomain endTime) {
        this.endTime = endTime;
    }
}
