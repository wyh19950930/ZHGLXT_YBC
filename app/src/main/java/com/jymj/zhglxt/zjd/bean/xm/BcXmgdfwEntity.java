package com.jymj.zhglxt.zjd.bean.xm;

import java.math.BigDecimal;

/**
 * Created by lc on 2022/11/11.
 */
public class BcXmgdfwEntity {
    private Long gid;
    private String layer;
    private String xz;//现状
    private BigDecimal jzzb;//建筑指标
    private String ghcxj;//规划城乡建
    private BigDecimal rjl;//容积率
    private String xmmc;
    private String geometry;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getLayer() {
        return layer==null?"":layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getXz() {
        return xz==null?"":xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public BigDecimal getJzzb() {
        return jzzb==null?BigDecimal.ZERO:jzzb;
    }

    public void setJzzb(BigDecimal jzzb) {
        this.jzzb = jzzb;
    }

    public String getGhcxj() {
        return ghcxj==null?"":ghcxj;
    }

    public void setGhcxj(String ghcxj) {
        this.ghcxj = ghcxj;
    }

    public BigDecimal getRjl() {
        return rjl==null?BigDecimal.ZERO:rjl;
    }

    public void setRjl(BigDecimal rjl) {
        this.rjl = rjl;
    }

    public String getXmmc() {
        return xmmc==null?"":xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
