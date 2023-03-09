package com.jymj.zhglxt.zjd.bean.yzt;

public class QsEntity {

    /**
     * qs : 集体
     * zldwmc : 大兴区北臧村镇大臧村
     * area : 169177.48
     */

    private String qs;
    private String zldwmc;
    private double area;

    public String getQs() {
        return qs==null?"":qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getZldwmc() {
        return zldwmc==null?"":zldwmc;
    }

    public void setZldwmc(String zldwmc) {
        this.zldwmc = zldwmc;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
