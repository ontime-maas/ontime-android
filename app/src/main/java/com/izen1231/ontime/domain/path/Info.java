package com.izen1231.ontime.domain.path;

public class Info {

    String mapObj;//
    int payment;//
    int busTransitCount;//
    int subwayTransitCount;//
    int busStationCount;//
    int subwayStationCount;//
    int totalStationCount;//
    int totalTime;//
    int totalWalk;//
    double trafficDistance; //
    double totalDistance;//
    String firstStartStation;//
    String lastEndStation;//
    int totalWalkTime;

    public String getMapObj() {
        return mapObj;
    }

    public void setMapObj(String mapObj) {
        this.mapObj = mapObj;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getBusTransitCount() {
        return busTransitCount;
    }

    public void setBusTransitCount(int busTransitCount) {
        this.busTransitCount = busTransitCount;
    }

    public int getSubwayTransitCount() {
        return subwayTransitCount;
    }

    public void setSubwayTransitCount(int subwayTransitCount) {
        this.subwayTransitCount = subwayTransitCount;
    }

    public int getBusStationCount() {
        return busStationCount;
    }

    public void setBusStationCount(int busStationCount) {
        this.busStationCount = busStationCount;
    }

    public int getSubwayStationCount() {
        return subwayStationCount;
    }

    public void setSubwayStationCount(int subwayStationCount) {
        this.subwayStationCount = subwayStationCount;
    }

    public int getTotalStationCount() {
        return totalStationCount;
    }

    public void setTotalStationCount(int totalStationCount) {
        this.totalStationCount = totalStationCount;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalWalk() {
        return totalWalk;
    }

    public void setTotalWalk(int totalWalk) {
        this.totalWalk = totalWalk;
    }

    public double getTrafficDistance() {
        return trafficDistance;
    }

    public void setTrafficDistance(double trafficDistance) {
        this.trafficDistance = trafficDistance;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getFirstStartStation() {
        return firstStartStation;
    }

    public void setFirstStartStation(String firstStartStation) {
        this.firstStartStation = firstStartStation;
    }

    public String getLastEndStation() {
        return lastEndStation;
    }

    public void setLastEndStation(String lastEndStation) {
        this.lastEndStation = lastEndStation;
    }

    public int getTotalWalkTime() {
        return totalWalkTime;
    }

    public void setTotalWalkTime(int totalWalkTime) {
        this.totalWalkTime = totalWalkTime;
    }

    @Override
    public String toString() {
        return "Info{" +
                "mapObj='" + mapObj + '\'' +
                ", payment=" + payment +
                ", busTransitCount=" + busTransitCount +
                ", subwayTransitCount=" + subwayTransitCount +
                ", busStationCount=" + busStationCount +
                ", subwayStationCount=" + subwayStationCount +
                ", totalStationCount=" + totalStationCount +
                ", totalTime=" + totalTime +
                ", totalWalk=" + totalWalk +
                ", trafficDistance=" + trafficDistance +
                ", totalDistance=" + totalDistance +
                ", firstStartStation='" + firstStartStation + '\'' +
                ", lastEndStation='" + lastEndStation + '\'' +
                ", totalWalkTime=" + totalWalkTime +
                '}';
    }
}
