package com.izen1231.ontime.domain;

public class TotalAlarmTimeDomain {

    TimeDomain totalSpendTime;
    TimeDomain readyTime;
    TimeDomain startTime;
    TimeDomain wakeupTime;

    public TimeDomain getTotalSpendTime() {
        return totalSpendTime;
    }

    public void setTotalSpendTime(TimeDomain totalSpendTime) {
        this.totalSpendTime = totalSpendTime;
    }

    public TimeDomain getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(TimeDomain readyTime) {
        this.readyTime = readyTime;
    }

    public TimeDomain getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeDomain startTime) {
        this.startTime = startTime;
    }

    public TimeDomain getWakeupTime() {
        return wakeupTime;
    }

    public void setWakeupTime(TimeDomain wakeupTime) {
        this.wakeupTime = wakeupTime;
    }
}
