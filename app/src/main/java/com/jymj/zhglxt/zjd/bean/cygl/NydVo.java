package com.jymj.zhglxt.zjd.bean.cygl;


import java.math.BigDecimal;

/**
 * Created by ${lc} on 2022/5/10.
 */
public class NydVo {
    private String xzqmc;
    private String code;
    private BigDecimal area;//面积
    private BigDecimal ydmj;//	用地面积亩
    private BigDecimal scss;//	生产设施亩
    private BigDecimal fsss;//	附属设施㎡
    private Integer counts;

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getArea() {
        return area==null?BigDecimal.ZERO:area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getYdmj() {
        return ydmj==null?BigDecimal.ZERO:ydmj;
    }

    public void setYdmj(BigDecimal ydmj) {
        this.ydmj = ydmj;
    }

    public BigDecimal getScss() {
        return scss==null?BigDecimal.ZERO:scss;
    }

    public void setScss(BigDecimal scss) {
        this.scss = scss;
    }

    public BigDecimal getFsss() {
        return fsss==null?BigDecimal.ZERO:fsss;
    }

    public void setFsss(BigDecimal fsss) {
        this.fsss = fsss;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
