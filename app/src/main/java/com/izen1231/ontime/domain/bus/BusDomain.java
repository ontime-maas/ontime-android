package com.izen1231.ontime.domain.bus;

import java.util.List;

public class BusDomain {

    String stationClass;
    String stationName;
    int  stationID;
    double x;
    double y;
    int CID;
    String cityName;
    String arsID;
    String param_do;
    String gu;
    String dong;
    String localStationID;
    String ebid;

    List<busInfo> businfo;



    public String getStationClass() {
        return stationClass;
    }

    public void setStationClass(String stationClass) {
        this.stationClass = stationClass;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArsID() {
        return arsID;
    }

    public void setArsID(String arsID) {
        this.arsID = arsID;
    }

    public String getParam_do() {
        return param_do;
    }

    public void setParam_do(String param_do) {
        this.param_do = param_do;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getLocalStationID() {
        return localStationID;
    }

    public void setLocalStationID(String localStationID) {
        this.localStationID = localStationID;
    }

    public String getEbid() {
        return ebid;
    }

    public void setEbid(String ebid) {
        this.ebid = ebid;
    }

    public List<busInfo> getBusinfo() {
        return businfo;
    }

    public void setBusinfo(List<busInfo> businfo) {
        this.businfo = businfo;
    }

    @Override
    public String toString() {
        return "BusDomain{" +
                "stationClass='" + stationClass + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationID=" + stationID +
                ", x=" + x +
                ", y=" + y +
                ", CID=" + CID +
                ", cityName='" + cityName + '\'' +
                ", arsID='" + arsID + '\'' +
                ", param_do='" + param_do + '\'' +
                ", gu='" + gu + '\'' +
                ", dong='" + dong + '\'' +
                ", localStationID='" + localStationID + '\'' +
                ", ebid='" + ebid + '\'' +
                ", businfo=" + businfo +
                '}';
    }
}
