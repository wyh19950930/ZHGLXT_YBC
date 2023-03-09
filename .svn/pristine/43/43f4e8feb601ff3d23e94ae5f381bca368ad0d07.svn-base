package com.jymj.zhglxt.rjhj.bean.yl.tdly;

import java.math.BigDecimal;

public class TdlyEntity {
    private Integer gid;
    private String dlbm;
    private String dlmc;
    private String qsxz;
    private String qsdwmc;
    private String xzqmc;
    private BigDecimal area;
    private BigDecimal area1;
    private String code;
    private String geometry;
    private String lx;
    private String layer;

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area.setScale(2,BigDecimal.ROUND_UP);
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public BigDecimal getArea1() {
        return area1==null?new BigDecimal(0):area1.setScale(2,BigDecimal.ROUND_UP);
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
    
    public String getGeometry() {
        return geometry==null?"":geometry;
    }
    
    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
    
    public int getGid() {
        return gid;
    }
    
    public void setGid(int gid) {
        this.gid = gid;
    }
    
    public String getQsdwmc() {
        return qsdwmc;
    }
    
    public void setQsdwmc(String qsdwmc) {
        this.qsdwmc = qsdwmc;
    }
    
    public String getQsxz() {
        if (qsxz != null) {
            if (qsxz.equals("20") || qsxz.equals("20")) {
                return "国有";
            }
        }
        return "集体";
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

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }
}
