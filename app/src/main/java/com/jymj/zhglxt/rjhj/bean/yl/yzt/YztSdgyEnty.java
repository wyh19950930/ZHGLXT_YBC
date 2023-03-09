package com.jymj.zhglxt.rjhj.bean.yl.yzt;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ljj on 2020/6/11.
 * 一张图---湿地公园
 */
public class YztSdgyEnty implements Serializable {
    private Integer gid;
    private String layer;
    private BigDecimal area;
    private String cjrq;
    private String code;
    private String xzqmc;
    private String geometry;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
