package com.jymj.zhglxt.zjd.bean;


import com.jymj.zhglxt.rjhj.bean.yl.enums.CszyEnum;

/**
 * Created by ${lc} on 2020/7/15.
 */
public class FloatFhEntity {
    private Integer cszy;
    private String cszyText;
    private Integer counts;
    private String time;
    private Integer wlrk;
    private Integer bdrk;
    private boolean lrlc;

    public boolean isLrlc() {
        return lrlc;
    }

    public void setLrlc(boolean lrlc) {
        this.lrlc = lrlc;
    }

    public Integer getWlrk() {
        return wlrk==null?0:wlrk;
    }

    public void setWlrk(Integer wlrk) {
        this.wlrk = wlrk;
    }

    public Integer getBdrk() {
        return bdrk==null?0:bdrk;
    }

    public void setBdrk(Integer bdrk) {
        this.bdrk = bdrk;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getTime() {
        return time==null?"":time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCszy() {
        return cszy==null?0:cszy;
    }

    public void setCszy(Integer cszy) {
        this.cszy = cszy;
    }

    public String getCszyText(){
        if (getCszy()==null)
            return null;
        return CszyEnum.getName(getCszy());
    }
}
