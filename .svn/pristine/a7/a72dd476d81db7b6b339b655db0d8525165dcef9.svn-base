package com.jymj.zhglxt.zjd.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 属性筛查
 */
public class SxsxBean {

    public int id;
    public String sxName="";//属性名称
    public String sxTj="";//属性条件
    public List<String> sxTjLsit;//属性条件
    public String sxSl="0";//属性数量
    public String sxdw;//属性单位
    public String sxSllx;//属性数量类型

    public SxsxBean() {
    }

    public SxsxBean(int id,String sxName, String sxdw, String sxSllx) {
        this.id = id;
        this.sxName = sxName;
        this.sxdw = sxdw;
        this.sxSllx = sxSllx;
    }

    public SxsxBean(int id, String sxTj) {
        this.id = id;
        this.sxTj = sxTj;
    }

    public SxsxBean(String sxName, String sxTj, String sxSl) {
        this.sxName = sxName;
        this.sxTj = sxTj;
        this.sxSl = sxSl;
    }

    public String getSxName() {
        return sxName==null?"":sxName;
    }

    public void setSxName(String sxName) {
        this.sxName = sxName;
    }

    public String getSxTj() {
        return sxTj==null?"":sxTj;
    }

    public void setSxTj(String sxTj) {
        this.sxTj = sxTj;
    }

    public int getSxTjInt() {
        int sxTjInt = 0;
        if (sxTj.equals(">")){
            sxTjInt = 1;
        }else if (sxTj.equals("<")){
            sxTjInt = 2;
        }else if (sxTj.equals("=")){
            sxTjInt = 3;
        }else if (sxTj.equals("≥")){
            sxTjInt = 4;
        }else if (sxTj.equals("≤")){
            sxTjInt = 5;
        }else if (sxTj.equals("之前")){
            sxTjInt = 2;
        }else if (sxTj.equals("之后")){
            sxTjInt = 1;
        }
        return sxTjInt;
    }

    public String getSxSl() {
        return sxSl==null?"":sxSl;
    }

    public void setSxSl(String sxSl) {
        this.sxSl = sxSl;
    }

    public List<String> getSxTjLsit() {
        return sxTjLsit==null?new ArrayList<>():sxTjLsit;
    }

    public void setSxTjLsit(List<String> sxTjLsit) {
        this.sxTjLsit = sxTjLsit;
    }

    public String getSxdw() {
        return sxdw==null?"":sxdw;
    }

    public void setSxdw(String sxdw) {
        this.sxdw = sxdw;
    }

    public String getSxSllx() {
        return sxSllx==null?"":sxSllx;
    }

    public void setSxSllx(String sxSllx) {
        this.sxSllx = sxSllx;
    }
}
