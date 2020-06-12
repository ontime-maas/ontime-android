package com.izen1231.ontime.domain;

public class AddressDomain {

    private static AddressDomain addressDomain;

    private String saddress;
    private String sx;
    private String sy;

    private String eaddress;
    private String ex;
    private String ey;

    public synchronized static AddressDomain getInstance(){
        if(addressDomain == null){
            addressDomain = new AddressDomain();
        }
        return addressDomain;
    }

    public String getSaddress() {
        return saddress;
    }

    public synchronized void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSx() {
        return sx;
    }

    public synchronized void setSx(String sx) {
        this.sx = sx;
    }

    public String getSy() {
        return sy;
    }

    public synchronized void setSy(String sy) {
        this.sy = sy;
    }

    public String getEaddress() {
        return eaddress;
    }

    public synchronized void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public String getEx() {
        return ex;
    }

    public synchronized void setEx(String ex) {
        this.ex = ex;
    }

    public String getEy() {
        return ey;
    }

    public synchronized void setEy(String ey) {
        this.ey = ey;
    }

    @Override
    public String toString() {
        return "AddressDomain{" +
                "saddress='" + saddress + '\'' +
                ", sx='" + sx + '\'' +
                ", sy='" + sy + '\'' +
                ", eaddress='" + eaddress + '\'' +
                ", ex='" + ex + '\'' +
                ", ey='" + ey + '\'' +
                '}';
    }
}
