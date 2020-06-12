package com.izen1231.ontime.domain.live;

public class list {
    String stNm;
    String rtNm;
    String arrmsg1;
    String arrmsg2;
    String isFullFlag1;
    String isFullFlag2;

    public String getStNm() {
        return stNm;
    }

    public void setStNm(String stNm) {
        this.stNm = stNm;
    }

    public String getRtNm() {
        return rtNm;
    }

    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public String getArrmsg1() {
        return arrmsg1;
    }

    public void setArrmsg1(String arrmsg1) {
        this.arrmsg1 = arrmsg1;
    }

    public String getArrmsg2() {
        return arrmsg2;
    }

    public void setArrmsg2(String arrmsg2) {
        this.arrmsg2 = arrmsg2;
    }

    public String getIsFullFlag1() {
        return isFullFlag1;
    }

    public void setIsFullFlag1(String isFullFlag1) {
        this.isFullFlag1 = isFullFlag1;
    }

    public String getIsFullFlag2() {
        return isFullFlag2;
    }

    public void setIsFullFlag2(String isFullFlag2) {
        this.isFullFlag2 = isFullFlag2;
    }

    @Override
    public String toString() {
        return "list{" +
                "stNm='" + stNm + '\'' +
                ", rtNm='" + rtNm + '\'' +
                ", arrmsg1='" + arrmsg1 + '\'' +
                ", arrmsg2='" + arrmsg2 + '\'' +
                ", isFullFlag1='" + isFullFlag1 + '\'' +
                ", isFullFlag2='" + isFullFlag2 + '\'' +
                '}';
    }
}

