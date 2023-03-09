package com.jymj.zhglxt.zjd.bean.yzt;

public class YztDpBean {

    /**
     * tbbh : dx0400
     * sslx : 塑料大棚
     * jssj : 2018
     * jyztmc : 北京厚地投资顾问有限公司
     * baqk : 无备案
     * ptmj : 854.6
     * geometry : POINT(116.256825 39.6750029999971)
     */

    private String tbbh;
    private String sslx;
    private String jssj;
    private String jyztmc;
    private String baqk;
    private double ptmj;
    private String geometry;

    public String getTbbh() {
        return tbbh==null?"":tbbh;
    }

    public void setTbbh(String tbbh) {
        this.tbbh = tbbh;
    }

    public String getSslx() {
        return sslx==null?"":sslx;
    }

    public void setSslx(String sslx) {
        this.sslx = sslx;
    }

    public String getJssj() {
        return jssj==null?"":jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getJyztmc() {
        return jyztmc==null?"":jyztmc;
    }

    public void setJyztmc(String jyztmc) {
        this.jyztmc = jyztmc;
    }

    public String getBaqk() {
        return baqk==null?"":baqk;
    }

    public void setBaqk(String baqk) {
        this.baqk = baqk;
    }

    public double getPtmj() {
        return ptmj;
    }

    public void setPtmj(double ptmj) {
        this.ptmj = ptmj;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
