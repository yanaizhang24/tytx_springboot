package cn.les.entity;

import java.io.Serializable;

/**
 * Created by 严峰 on 2016/7/22 0022.
 */
public class VeKey implements Serializable{
    private String ve_id;
    private String service_id;
    //private String change_time;
    private String base_key;
    private String service_name;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getVe_id() {
        return ve_id;
    }

    public void setVe_id(String ve_id) {
        this.ve_id = ve_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getBase_key() {
        return base_key;
    }

    public void setBase_key(String base_key) {
        this.base_key = base_key;
    }
}
