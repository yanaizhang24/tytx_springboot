package cn.les.entity;

import java.io.Serializable;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */
public class VePedto implements Serializable{
    private String ve_id;
    private Integer ve_atom;
    private String ve_description;
    private String ve_name;
    private String geolocation_id;
    private String otherInfo;
    private String pe_id;
    private Integer state;

    public String getVe_id() {
        return ve_id;
    }

    public void setVe_id(String ve_id) {
        this.ve_id = ve_id;
    }

    public Integer getVe_atom() {
        return ve_atom;
    }

    public void setVe_atom(Integer ve_atom) {
        this.ve_atom = ve_atom;
    }

    public String getVe_description() {
        return ve_description;
    }

    public void setVe_description(String ve_description) {
        this.ve_description = ve_description;
    }

    public String getVe_name() {
        return ve_name;
    }

    public void setVe_name(String ve_name) {
        this.ve_name = ve_name;
    }

    public String getGeolocation_id() {
        return geolocation_id;
    }

    public void setGeolocation_id(String geolocation_id) {
        this.geolocation_id = geolocation_id;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getPe_id() {
        return pe_id;
    }

    public void setPe_id(String pe_id) {
        this.pe_id = pe_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
