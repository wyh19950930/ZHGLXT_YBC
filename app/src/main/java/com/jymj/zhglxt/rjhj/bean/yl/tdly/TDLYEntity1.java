package com.jymj.zhglxt.rjhj.bean.yl.tdly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ljj on 2017/3/16.
 */
public class TDLYEntity1 implements Serializable {

    private Integer gid;
    private String dlbm;
    private String dlmc;
    private String qsxz;
    private String lx;
    private String qsdwmc;
    private String xzqmc;
    private BigDecimal area;
    private BigDecimal area1;
    private String code;
    private Integer type;
    private String geometry;
    private String center;

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    //8
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getDlbm() {
        return dlbm;
    }

    public void setDlbm(String dlbm) {
        this.dlbm = dlbm;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    public String getQsxz() {
        return qsxz;
    }

    public void setQsxz(String qsxz) {
        this.qsxz = qsxz;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public BigDecimal getArea() {
        return area == null ? null : area.setScale(1, RoundingMode.HALF_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getArea1() {
        return area1 == null ? null : area1.setScale(1, RoundingMode.HALF_UP);
    }

    public void setArea1(BigDecimal area1) {
        this.area1 = area1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQsdwmc() {
        return qsdwmc;
    }

    public void setQsdwmc(String qsdwmc) {
        this.qsdwmc = qsdwmc;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
