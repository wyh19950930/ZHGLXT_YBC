package com.jymj.zhglxt.rjhj.bean;

/**
 * Created by ${lc} on 2020/5/6.
 */
public class SysXzqFhEntity {
    private Long xzqId;
    private String name;
    private String code;
    private String geometry;
    private String center;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Long getXzqId() {
        return xzqId;
    }

    public void setXzqId(Long xzqId) {
        this.xzqId = xzqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
