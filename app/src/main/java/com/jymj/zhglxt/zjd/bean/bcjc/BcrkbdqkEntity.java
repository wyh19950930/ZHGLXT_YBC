package com.jymj.zhglxt.zjd.bean.bcjc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/7/28.
 */

public class BcrkbdqkEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String qchs;//全村户籍人口--户数
    private String qcrk;//全村户籍人口--人口
    private String nyhjh;//农业户籍(户)
    private String nyhjr;//农业户籍(人)
    private String nlss;//按年龄划分--≤30
    private String nlls;//按年龄划分--≥60
    private String bcczrk;//本村户籍常住人口
    private String wlczrk;//外来常住人口
    private String createTime;
    private String updateTime;
    private String years;//年份
    private Long userId;

    private String cunname;//	填写名字
    private String zhenname;//审核名字
    private String qvname;//	审批名字
    private String shtime;//	审核时间
    private String sptime;//	审批时间
    private String xftime;//	下发时间
    private Integer process;//	流程  1 村填写  2 镇审核  3 区审批  4完结
    private String cunphone;//	号码
    private String zhen;//	镇名
    private String xzq;//	区

    private List<BcrejectedEntity> bcrejectedEntities;

    public String getCunphone() {
        return cunphone==null?"":cunphone;
    }

    public void setCunphone(String cunphone) {
        this.cunphone = cunphone;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getXzq() {
        return xzq==null?"":xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public Long getId() {
        return id == null?0L:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXzqmc() {
        return xzqmc == null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code == null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQchs() {
        return qchs == null?"":qchs;
    }

    public void setQchs(String qchs) {
        this.qchs = qchs;
    }

    public String getQcrk() {
        return qcrk == null?"":qcrk;
    }

    public void setQcrk(String qcrk) {
        this.qcrk = qcrk;
    }

    public String getNyhjh() {
        return nyhjh == null?"":nyhjh;
    }

    public void setNyhjh(String nyhjh) {
        this.nyhjh = nyhjh;
    }

    public String getNyhjr() {
        return nyhjr == null?"":nyhjr;
    }

    public void setNyhjr(String nyhjr) {
        this.nyhjr = nyhjr;
    }

    public String getNlss() {
        return nlss == null?"":nlss;
    }

    public void setNlss(String nlss) {
        this.nlss = nlss;
    }

    public String getNlls() {
        return nlls == null?"":nlls;
    }

    public void setNlls(String nlls) {
        this.nlls = nlls;
    }

    public String getBcczrk() {
        return bcczrk == null?"":bcczrk;
    }

    public void setBcczrk(String bcczrk) {
        this.bcczrk = bcczrk;
    }

    public String getWlczrk() {
        return wlczrk == null?"":wlczrk;
    }

    public void setWlczrk(String wlczrk) {
        this.wlczrk = wlczrk;
    }

    public String getCreateTime() {
        return createTime == null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime == null?"":updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getYears() {
        return years == null?"":years;
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

    public String getCunname() {
        return cunname == null?"":cunname;
    }

    public void setCunname(String cunname) {
        this.cunname = cunname;
    }

    public String getZhenname() {
        return zhenname == null?"":zhenname;
    }

    public void setZhenname(String zhenname) {
        this.zhenname = zhenname;
    }

    public String getQvname() {
        return qvname == null?"":qvname;
    }

    public void setQvname(String qvname) {
        this.qvname = qvname;
    }

    public String getShtime() {
        return shtime == null?"":shtime;
    }

    public void setShtime(String shtime) {
        this.shtime = shtime;
    }

    public String getSptime() {
        return sptime == null?"":sptime;
    }

    public void setSptime(String sptime) {
        this.sptime = sptime;
    }

    public String getXftime() {
        return xftime == null?"":xftime;
    }

    public void setXftime(String xftime) {
        this.xftime = xftime;
    }

    public Integer getProcess() {
        return process == null?0:process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public List<BcrejectedEntity> getBcrejectedEntities() {
        if (bcrejectedEntities==null){
            bcrejectedEntities = new ArrayList<>();
        }
        return bcrejectedEntities;
    }

    public void setBcrejectedEntities(List<BcrejectedEntity> bcrejectedEntities) {
        this.bcrejectedEntities = bcrejectedEntities;
    }
}
