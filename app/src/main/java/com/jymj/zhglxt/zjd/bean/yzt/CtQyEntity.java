package com.jymj.zhglxt.zjd.bean.yzt;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2020/6/22.
 */
public class CtQyEntity {
    private Integer gid;
    private BigDecimal area;
    private String lx;
    private String cjrq;
    private String xzqdm;
    private String xzqmc;
    private String company;
    private String xmmc;
    private String textstring;
    private String geometry;
    private Integer id;//充当主键

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public String getXzqdm() {
        return xzqdm;
    }

    public void setXzqdm(String xzqdm) {
        this.xzqdm = xzqdm;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getTextstring() {
        return textstring;
    }

    public void setTextstring(String textstring) {
        this.textstring = textstring;
    }

    public String getGeom() {
        return geometry==null?"":geometry;
    }

    public void setGeom(String geom) {
        this.geometry = geom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
