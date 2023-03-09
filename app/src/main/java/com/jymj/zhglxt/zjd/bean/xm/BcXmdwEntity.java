package com.jymj.zhglxt.zjd.bean.xm;

/**
 * Created by lc on 2022/11/11.
 */
public class BcXmdwEntity {
    private Long gid;
    private String yt;
    private String xmmc;
    private String geometry;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getYt() {
        return yt==null?"":yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
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
