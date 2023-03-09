package com.jymj.zhglxt.rjhj.bean;

import java.math.BigDecimal;

public class PointAndTypeEntity {
    private Integer type;//缩放大小
    private BigDecimal xmin;//左下角
    private BigDecimal xmax;//右下角
    private BigDecimal ymin;//左上角
    private BigDecimal ymax;//左上角
    private Integer hjzzlx;//1 人居环境问题(1,3,7)  2 大集问题(6)
    private String code;//左上角

    public Integer getHjzzlx() {
        return hjzzlx;
    }

    public void setHjzzlx(Integer hjzzlx) {
        this.hjzzlx = hjzzlx;
    }

    public Integer getType() {
        return type==null?0:type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getXmin() {
        return xmin==null? BigDecimal.ZERO:xmax;
    }

    public void setXmin(BigDecimal xmin) {
        this.xmin = xmin;
    }

    public BigDecimal getXmax() {
        return xmax==null? BigDecimal.ZERO:xmax;
    }

    public void setXmax(BigDecimal xmax) {
        this.xmax = xmax;
    }

    public BigDecimal getYmin() {
        return ymin==null? BigDecimal.ZERO:ymin;
    }

    public void setYmin(BigDecimal ymin) {
        this.ymin = ymin;
    }

    public BigDecimal getYmax() {
        return ymax==null? BigDecimal.ZERO:ymax;
    }

    public void setYmax(BigDecimal ymax) {
        this.ymax = ymax;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
