package com.izen1231.ontime.domain.path;

public class StationList {

    int index;
    int stationID;
    String stationName;
    String x;
    String y;

    public int getIndex() {
        return index;
    }

    public int getStationID() {
        return stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public String toString() {
        return "StationList{" +
                "index=" + index +
                ", stationID=" + stationID +
                ", stationName='" + stationName + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
