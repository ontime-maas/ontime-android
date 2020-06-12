package com.izen1231.ontime.domain.path;

import java.util.List;

public class SubPathList {
    int trafficType;
    double distance;
    int sectionTime;
    int stationCount;
    List<LaneList> lane;
    String startName;
    double startX;
    double startY;
    String endName;
    double endX;
    double endY;
    String way;
    int wayCode;
    String door;
    int startID;
    int endID;
    double startExitX;
    double startExitY;
    String endExitNo;
    double endExitX;
    double endExitY;
    PassStopList passStopList;

    public int getTrafficType() {
        return trafficType;
    }

    public double getDistance() {
        return distance;
    }

    public int getSectionTime() {
        return sectionTime;
    }

    public int getStationCount() {
        return stationCount;
    }

    public List<LaneList> getLane() {
        return lane;
    }

    public String getStartName() {
        return startName;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public String getEndName() {
        return endName;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public String getWay() {
        return way;
    }

    public int getWayCode() {
        return wayCode;
    }

    public String getDoor() {
        return door;
    }

    public int getStartID() {
        return startID;
    }

    public int getEndID() {
        return endID;
    }

    public double getStartExitX() {
        return startExitX;
    }

    public double getStartExitY() {
        return startExitY;
    }

    public String getEndExitNo() {
        return endExitNo;
    }

    public double getEndExitX() {
        return endExitX;
    }

    public double getEndExitY() {
        return endExitY;
    }

    public PassStopList getPassStopList() {
        return passStopList;
    }

    @Override
    public String toString() {
        return "SubPathList{" +
                "trafficType=" + trafficType +
                ", distance=" + distance +
                ", sectionTime=" + sectionTime +
                ", stationCount=" + stationCount +
                ", lane=" + lane +
                ", startName='" + startName + '\'' +
                ", startX=" + startX +
                ", startY=" + startY +
                ", endName='" + endName + '\'' +
                ", endX=" + endX +
                ", endY=" + endY +
                ", way='" + way + '\'' +
                ", wayCode=" + wayCode +
                ", door='" + door + '\'' +
                ", startID=" + startID +
                ", endID=" + endID +
                ", startExitX=" + startExitX +
                ", startExitY=" + startExitY +
                ", endExitNo='" + endExitNo + '\'' +
                ", endExitX=" + endExitX +
                ", endExitY=" + endExitY +
                ", passStopList=" + passStopList +
                '}';
    }
}
