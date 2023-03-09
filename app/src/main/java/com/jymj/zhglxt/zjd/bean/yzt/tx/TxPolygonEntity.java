package com.jymj.zhglxt.zjd.bean.yzt.tx;

public class TxPolygonEntity {
    private double jsydmj=0;
    private double nydmj=0;
    private double tjsmj=0;
    private double wlydmj=0;

    public double getJsydmj() {
        Double dd =new Double(jsydmj);
        return dd==null?0:jsydmj;
    }

    public void setJsydmj(double jsydmj) {
        this.jsydmj = jsydmj;
    }

    public double getNydmj() {
        Double dd =new Double(nydmj);
        return dd==null?0:nydmj;
    }

    public void setNydmj(double nydmj) {
        this.nydmj = nydmj;
    }

    public double getTjsmj() {
        Double dd =new Double(tjsmj);
        return dd==null?0:tjsmj;
    }

    public void setTjsmj(double tjsmj) {
        this.tjsmj = tjsmj;
    }

    public double getWlydmj() {
        Double dd =new Double(wlydmj);
        return dd==null?0:wlydmj;
    }

    public void setWlydmj(double wlydmj) {
        this.wlydmj = wlydmj;
    }
}
