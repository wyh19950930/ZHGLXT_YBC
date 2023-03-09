package com.jymj.zhglxt.zjd.bean.bcjc;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lc on 2022/7/28.外出务工人员就业结构变动情况
 */
public class BcwcjybdqkEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String cqwgzs;
    private String bdjy;//村外乡内
    private String xwqn;//乡外区内
    private String qwsn;//区外市内
    private String wss;//外省市
    private String createTime;
    private String updateTime;
    private String years;
    private String userId;

    private String cunname;//	填写名字
    private String zhenname;//审核名字
    private String qvname;//	审批名字
    private String shtime;//	审核时间
    private String sptime;//	审批时间
    private String xftime;//	下发时间
    private Integer process;//	流程  1 村填写  2 镇审核  3 区审批  4完结
    private String xzq;//区
    private String zhen;//镇名

    private List<BcrejectedEntity> bcrejectedEntities;

    public String getXzq() {
        return xzq==null?"":xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
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

    public String getCqwgzs() {
        return cqwgzs == null?"":cqwgzs;
    }

    public void setCqwgzs(String cqwgzs) {
        this.cqwgzs = cqwgzs;
    }

    public String getBdjy() {
        if (bdjy==null||bdjy.equals("")){
            bdjy = "0";
        }
        return bdjy;
    }

    public void setBdjy(String bdjy) {
        this.bdjy = bdjy;
    }

    public String getXwqn() {
        if (xwqn==null||xwqn.equals("")){
            xwqn = "0";
        }
        return xwqn;
    }

    public void setXwqn(String xwqn) {
        this.xwqn = xwqn;
    }

    public String getQwsn() {

        if (qwsn==null||qwsn.equals("")){
            qwsn = "0";
        }
        return qwsn;
    }

    public void setQwsn(String qwsn) {
        this.qwsn = qwsn;
    }

    public String getWss() {
        if (wss==null||wss.equals("")){
            wss = "0";
        }
        return wss;
    }

    public void setWss(String wss) {
        this.wss = wss;
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

    public String getUserId() {
        return userId == null?"":userId;
    }

    public void setUserId(String userId) {
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
