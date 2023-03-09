package com.jymj.zhglxt.rjhj.bean;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ${lc} on 2022/1/24.镇统计
 */
public class TownCountEntity implements Serializable {
    private String xzqmc;
    private String code;
    private int total;//总数
    private int yzg;//已整改
    private int wzg;//未整改
    private int zdwt;//重大问题
    private BigDecimal zgl;//整改率
    private String center;//中心点
    private String geometry;
    private boolean isShow=true;

    public BigDecimal getZgl() {
        return zgl==null? BigDecimal.ZERO:zgl;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYzg() {
        return yzg;
    }

    public void setYzg(int yzg) {
        this.yzg = yzg;
    }

    public int getWzg() {
        return wzg;
    }

    public void setWzg(int wzg) {
        this.wzg = wzg;
    }

    public int getZdwt() {
        return zdwt;
    }

    public void setZdwt(int zdwt) {
        this.zdwt = zdwt;
    }

    public BigDecimal isZgl() {
        return zgl == null?new BigDecimal(0):zgl;
    }

    public void setZgl(BigDecimal zgl) {
        this.zgl = zgl;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
    /* public BigDecimal getZgl() {
        if (getTotal() != null && getYzg() != null){
            return getTotal() == 0 ? BigDecimal.ZERO : getYzg() == 0 ? BigDecimal.ZERO :
            BigDecimal.valueOf(getYzg()).divide(BigDecimal.valueOf(getTotal()),6, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)).setScale(1,RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }*/
}
