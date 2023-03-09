package com.jymj.zhglxt.xm.bean;

/**
 * Created by lc on 2022/12/7.
 */
public class CjVO {
    private String dz;
    private String location;
    private String xzqmc;
    private String xzq;
    private String zhen;
    private String code;
    private String center;
    private String geometry;
    private String point;

    public String getPoint() {
        return point==null?"":point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDz() {
        return dz==null?"":dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getXzq() {
        return xzq==null?"":xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
