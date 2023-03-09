package com.jymj.zhglxt.zjd.bean.yzt.nyd;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2020/4/9.长子营农用地合同租金
 */
public class ZzynydZjEntity {
    private Integer id;
    private String bh;//	编码
    private BigDecimal nzj;;//年租金 （元/亩/年）
    private BigDecimal nyszj;//平均年应收租金（元）
    private BigDecimal zjze;//租金总额（元）
    private String fktime;//付款时间
    private String fkfs;//	付款方式

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public BigDecimal getNzj() {
        return nzj==null?new BigDecimal(0):nzj;
    }

    public void setNzj(BigDecimal nzj) {
        this.nzj = nzj;
    }

    public BigDecimal getNyszj() {
        return nyszj==null?new BigDecimal(0):nyszj;
    }

    public void setNyszj(BigDecimal nyszj) {
        this.nyszj = nyszj;
    }

    public BigDecimal getZjze() {
        return zjze==null?new BigDecimal(0):zjze;
    }

    public void setZjze(BigDecimal zjze) {
        this.zjze = zjze;
    }

    public String getFktime() {
        return fktime==null?"":fktime;
    }

    public void setFktime(String fktime) {
        this.fktime = fktime;
    }

    public String getFkfs() {
        return fkfs==null?"":fkfs;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }
}
