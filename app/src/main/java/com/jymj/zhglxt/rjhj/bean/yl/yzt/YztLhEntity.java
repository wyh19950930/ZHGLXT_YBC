package com.jymj.zhglxt.rjhj.bean.yl.yzt;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2020/6/11.
 */
public class YztLhEntity {
    private Integer gid;
    private String layer;//林地名称
    //private String jstime;//建设时间
    private String dkbh;//地块编号
    private String tdxz;//土地现状
    private String remark;//备注
    private BigDecimal area;
    private String xzqmc;
    private String lx;
    private String code;
    private String cjrq;
    private String cqr;
    private String geometry;
    private String name;
    private String sfcc;
    private double jzmj;

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSfcc() {
        return sfcc==null?"":sfcc;
    }

    public void setSfcc(String sfcc) {
        this.sfcc = sfcc;
    }

    public double getJzmj() {
        return jzmj;
    }

    public void setJzmj(double jzmj) {
        this.jzmj = jzmj;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getCqr() {
        return cqr==null?"":cqr;
    }

    public void setCqr(String cqr) {
        this.cqr = cqr;
    }

    public String getLayer() {
        return layer==null?"":layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getDkbh() {
        return dkbh==null?"":dkbh;
    }

    public void setDkbh(String dkbh) {
        this.dkbh = dkbh;
    }

    public String getTdxz() {
        return tdxz==null?"":tdxz;
    }

    public void setTdxz(String tdxz) {
        this.tdxz = tdxz;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area.setScale(2,BigDecimal.ROUND_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getLx() {
        return lx==null?"":lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCjrq() {
        return cjrq==null?"":cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
