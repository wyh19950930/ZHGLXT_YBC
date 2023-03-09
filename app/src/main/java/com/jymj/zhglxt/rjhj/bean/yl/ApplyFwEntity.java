package com.jymj.zhglxt.rjhj.bean.yl;

import java.io.Serializable;

public class ApplyFwEntity implements Serializable {
    private Integer id;
    private Integer ylId;//	院落id
    private Integer appId;//	翻建id
    private float	area;//	房屋面积
    private int	fwcs;//	房屋层数
    private float fwgd;//	房屋高度

    public Integer getId() {
        return id==null?0:id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYlId() {
        return ylId==null?0:ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public Integer getAppId() {
        return appId==null?0:appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getFwcs() {
        return fwcs;
    }

    public void setFwcs(int fwcs) {
        this.fwcs = fwcs;
    }

    public float getFwgd() {
        return fwgd;
    }

    public void setFwgd(float fwgd) {
        this.fwgd = fwgd;
    }
}
