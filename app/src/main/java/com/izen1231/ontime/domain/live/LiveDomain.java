package com.izen1231.ontime.domain.live;

import java.util.List;

public class LiveDomain {

    List<list> list ;

    public List<com.izen1231.ontime.domain.live.list> getList() {
        return list;
    }

    public void setList(List<com.izen1231.ontime.domain.live.list> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "LiveDomain{" +
                "list=" + list +
                '}';
    }
}
