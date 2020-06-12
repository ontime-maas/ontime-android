package com.izen1231.ontime.domain.path;

public class LaneList {
    String name;
    String busNo;
    int type;
    int busID;
    int subwayCode;
    int subwayCityCode;

    public String getName() {
        return name;
    }

    public String getBusNo() {
        return busNo;
    }

    public int getType() {
        return type;
    }

    public int getBusID() {
        return busID;
    }

    public int getSubwayCode() {
        return subwayCode;
    }

    public int getSubwayCityCode() {
        return subwayCityCode;
    }

    @Override
    public String toString() {
        return "LaneList{" +
                "name='" + name + '\'' +
                ", busNo='" + busNo + '\'' +
                ", type=" + type +
                ", busID=" + busID +
                ", subwayCode=" + subwayCode +
                ", subwayCityCode=" + subwayCityCode +
                '}';
    }
}
