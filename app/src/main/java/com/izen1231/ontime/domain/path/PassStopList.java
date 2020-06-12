package com.izen1231.ontime.domain.path;

import java.io.Serializable;
import java.util.List;

public class PassStopList {
    List<StationList> stations;

    public List<StationList> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "PassStopList{" +
                "stations=" + stations +
                '}';
    }
}
