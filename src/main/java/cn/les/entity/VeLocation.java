package cn.les.entity;

import java.io.Serializable;

/**
 * Created by 严峰 on 2016/7/29 0029.
 */
public class VeLocation implements Serializable{
    private String veId;
    private String ve_name;
    private String longitude;
    private String latitude;
    private String altitude;

    public String getVeId() {
        return veId;
    }

    public void setVeId(String veId) {
        this.veId = veId;
    }

    public String getVe_name() {
        return ve_name;
    }

    public void setVe_name(String ve_name) {
        this.ve_name = ve_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
}
