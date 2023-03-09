package com.jymj.zhglxt.rjhj.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ${lc} on 2020/12/25.
 */
public class HomeEntity {
    private String xzqmc;
    private Integer zfs;//总发生
    private Integer wcl;//未处理
    private Integer ycl;//已处理
    private Integer zdsj;//重大事件
    private int overdue;//逾期

    private BigDecimal wclrhb;//未处理日环比
    private BigDecimal yclrhb;//已处理日环比
    private BigDecimal zdsjrhb;//重大事件日环比

    private Integer zthj;//整体环境问题数
    private Integer gcwt;//公厕问题
    private Integer wswt;//污水问题
    private Integer ljwt;//垃圾问题
    private Integer crwt;//村容问题

    private BigDecimal zthjrhb;//整体环境问题数日环比
    private BigDecimal gcwtrhb;//公厕问题日环比
    private BigDecimal wswtrhb;//污水问题日环比
    private BigDecimal ljwtrhb;//垃圾问题日环比
    private BigDecimal crwtrhb;//村容问题日环比
    private BigDecimal zgl;//整改率

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    public BigDecimal getZgl() {
        if (getYcl()==0){
            int i = 0;
            if (getZfs()==0){
                i = 100;//100
            }
            return new BigDecimal(i);
        }else{
            float i = (getYcl()+0f) / (getZfs()+0f)*100;
            BigDecimal scale = new BigDecimal(i).setScale(1, RoundingMode.HALF_UP);//2
            return scale;
        }
    }

    public void setZgl(BigDecimal zgl) {
        this.zgl = zgl;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer getZfs() {
        return zfs == null ? 0 : zfs;
    }

    public void setZfs(Integer zfs) {
        this.zfs = zfs;
    }

    public Integer getWcl() {
        return wcl == null ? 0 : wcl;
    }

    public void setWcl(Integer wcl) {
        this.wcl = wcl;
    }

    public Integer getYcl() {
        return ycl == null ? 0 : ycl;
    }

    public void setYcl(Integer ycl) {
        this.ycl = ycl;
    }

    public Integer getZdsj() {
        return zdsj == null ? 0 : zdsj;
    }

    public void setZdsj(Integer zdsj) {
        this.zdsj = zdsj;
    }

    public BigDecimal getWclrhb() {
        return wclrhb==null?new BigDecimal(0):wclrhb;
    }

    public void setWclrhb(BigDecimal wclrhb) {
        this.wclrhb = wclrhb;
    }

    public BigDecimal getYclrhb() {
        return yclrhb==null?new BigDecimal(0):yclrhb;
    }

    public void setYclrhb(BigDecimal yclrhb) {
        this.yclrhb = yclrhb;
    }

    public BigDecimal getZdsjrhb() {
        return zdsjrhb==null?new BigDecimal(0):zdsjrhb;
    }

    public void setZdsjrhb(BigDecimal zdsjrhb) {
        this.zdsjrhb = zdsjrhb;
    }

    public Integer getZthj() {
        return zthj == null ? 0 : zthj;
    }

    public void setZthj(Integer zthj) {
        this.zthj = zthj;
    }

    public Integer getGcwt() {
        return gcwt == null ? 0 : gcwt;
    }

    public void setGcwt(Integer gcwt) {
        this.gcwt = gcwt;
    }

    public Integer getWswt() {
        return wswt == null ? 0 : wswt;
    }

    public void setWswt(Integer wswt) {
        this.wswt = wswt;
    }

    public Integer getLjwt() {
        return ljwt == null ? 0 : ljwt;
    }

    public void setLjwt(Integer ljwt) {
        this.ljwt = ljwt;
    }

    public Integer getCrwt() {
        return crwt == null ? 0 : crwt;
    }

    public void setCrwt(Integer crwt) {
        this.crwt = crwt;
    }

    public BigDecimal getZthjrhb() {
        return zthjrhb==null?new BigDecimal(0):zthjrhb;
    }

    public void setZthjrhb(BigDecimal zthjrhb) {
        this.zthjrhb = zthjrhb;
    }

    public BigDecimal getGcwtrhb() {
        return gcwtrhb==null?new BigDecimal(0):gcwtrhb;
    }

    public void setGcwtrhb(BigDecimal gcwtrhb) {
        this.gcwtrhb = gcwtrhb;
    }

    public BigDecimal getWswtrhb() {
        return wswtrhb==null?new BigDecimal(0):wswtrhb;
    }

    public void setWswtrhb(BigDecimal wswtrhb) {
        this.wswtrhb = wswtrhb;
    }

    public BigDecimal getLjwtrhb() {
        return ljwtrhb==null?new BigDecimal(0):ljwtrhb;
    }

    public void setLjwtrhb(BigDecimal ljwtrhb) {
        this.ljwtrhb = ljwtrhb;
    }

    public BigDecimal getCrwtrhb() {
        return crwtrhb==null?new BigDecimal(0):crwtrhb;
    }

    public void setCrwtrhb(BigDecimal crwtrhb) {
        this.crwtrhb = crwtrhb;
    }
}
