package com.izen1231.ontime.domain.path;

import java.util.List;

public class Result {
    List<PathList> path;

    int searchType;
    int startRadius;
    int endRadius;
    int busCount;
    int subwayCount;
    int subwayBusCount;
    double pointDistance;
    int outTrafficCheck;

    public List<PathList> getPath() {
        return path;
    }

    public int getSearchType() {
        return searchType;
    }

    public int getStartRadius() {
        return startRadius;
    }

    public int getEndRadius() {
        return endRadius;
    }

    public int getBusCount() {
        return busCount;
    }

    public int getSubwayCount() {
        return subwayCount;
    }

    public int getSubwayBusCount() {
        return subwayBusCount;
    }

    public double getPointDistance() {
        return pointDistance;
    }

    public int getOutTrafficCheck() {
        return outTrafficCheck;
    }

    @Override
    public String toString() {
        return "Result{" +
                "path=" + path +
                ", searchType=" + searchType +
                ", startRadius=" + startRadius +
                ", endRadius=" + endRadius +
                ", busCount=" + busCount +
                ", subwayCount=" + subwayCount +
                ", subwayBusCount=" + subwayBusCount +
                ", pointDistance=" + pointDistance +
                ", outTrafficCheck=" + outTrafficCheck +
                '}';
    }
}
