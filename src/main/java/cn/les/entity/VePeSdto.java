package cn.les.entity;

import java.io.Serializable;

/**
 * Created by 严峰 on 2016/7/12 0012.
 */
//页面展示用dto
public class VePeSdto implements Serializable{
    private boolean atom ;
    private String contain;
    private String corresponTo;
    private String creator;
    private String description;
    private String geolocation;
    private String id;
    private String key;
    private String name;
    private String otherInfo;
    private String privacy;
    private String serviceId;
    private String serviceId2;
    private String serviceName;
    private String serviceName2;
    private String serviceUrl;
    private String state;
    private String templateId;
    private String type;
    private String vEServices;

    public boolean isAtom() {
        return atom;
    }

    public void setAtom(boolean atom) {
        this.atom = atom;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getCorresponTo() {
        return corresponTo;
    }

    public void setCorresponTo(String corresponTo) {
        this.corresponTo = corresponTo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId2() {
        return serviceId2;
    }

    public void setServiceId2(String serviceId2) {
        this.serviceId2 = serviceId2;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName2() {
        return serviceName2;
    }

    public void setServiceName2(String serviceName2) {
        this.serviceName2 = serviceName2;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getvEServices() {
        return vEServices;
    }

    public void setvEServices(String vEServices) {
        this.vEServices = vEServices;
    }
}
