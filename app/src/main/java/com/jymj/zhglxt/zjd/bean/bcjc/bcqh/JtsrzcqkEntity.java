package com.jymj.zhglxt.zjd.bean.bcjc.bcqh;

import java.util.Date;

/**
 * Created by lc on 2022/11/7.家庭收入支出情况表
 */
public class JtsrzcqkEntity {
    private Long id;
    private Long jtjcId;
    private String srhj;//	收入合计
    private String jtjysr;//	家庭经营收入
    private String gzxsr;//	工资性收入
    private String ccxsr;//	财产性收入
    private String zyxsr;//	转移性收入
    private String qtsr;//	其他收入
    private String zchj;//	支出合计
    private String sczl;//	生产资料
    private String shxf;//	生活消费
    private String jy;//	教育
    private String yl;//	医疗
    private String sylr;//	赡养老人
    private String sb;//	社保
    private String qtzc;//	其他支出
    private String createTime;
    private String updateTime;
    private String years;
    private Long userId;
    private String cjtjysr;//	村集体经营收入
    private String jtjydsr;//	家庭经营的收入


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJtjcId() {
        return jtjcId;
    }

    public void setJtjcId(Long jtjcId) {
        this.jtjcId = jtjcId;
    }

    public String getSrhj() {
        return srhj==null?"":srhj;
    }

    public void setSrhj(String srhj) {
        this.srhj = srhj;
    }

    public String getJtjysr() {
        return jtjysr==null?"":jtjysr;
    }

    public void setJtjysr(String jtjysr) {
        this.jtjysr = jtjysr;
    }

    public String getGzxsr() {
        return gzxsr==null?"":gzxsr;
    }

    public void setGzxsr(String gzxsr) {
        this.gzxsr = gzxsr;
    }

    public String getCcxsr() {
        return ccxsr==null?"":ccxsr;
    }

    public void setCcxsr(String ccxsr) {
        this.ccxsr = ccxsr;
    }

    public String getZyxsr() {
        return zyxsr==null?"":zyxsr;
    }

    public void setZyxsr(String zyxsr) {
        this.zyxsr = zyxsr;
    }

    public String getQtsr() {
        return qtsr==null?"":qtsr;
    }

    public void setQtsr(String qtsr) {
        this.qtsr = qtsr;
    }

    public String getZchj() {
        return zchj==null?"":zchj;
    }

    public void setZchj(String zchj) {
        this.zchj = zchj;
    }

    public String getSczl() {
        return sczl==null?"":sczl;
    }

    public void setSczl(String sczl) {
        this.sczl = sczl;
    }

    public String getShxf() {
        return shxf==null?"":shxf;
    }

    public void setShxf(String shxf) {
        this.shxf = shxf;
    }

    public String getJy() {
        return jy==null?"":jy;
    }

    public void setJy(String jy) {
        this.jy = jy;
    }

    public String getYl() {
        return yl==null?"":yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getSylr() {
        return sylr==null?"":sylr;
    }

    public void setSylr(String sylr) {
        this.sylr = sylr;
    }

    public String getSb() {
        return sb==null?"":sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getQtzc() {
        return qtzc==null?"":qtzc;
    }

    public void setQtzc(String qtzc) {
        this.qtzc = qtzc;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime==null?"":updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getYears() {
        return years == null ? "" : years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCjtjysr() {
        return cjtjysr==null?"":cjtjysr;
    }

    public void setCjtjysr(String cjtjysr) {
        this.cjtjysr = cjtjysr;
    }

    public String getJtjydsr() {
        return jtjydsr==null?"":jtjydsr;
    }

    public void setJtjydsr(String jtjydsr) {
        this.jtjydsr = jtjydsr;
    }
}
