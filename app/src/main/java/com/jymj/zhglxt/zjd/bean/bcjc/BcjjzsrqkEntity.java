package com.jymj.zhglxt.zjd.bean.bcjc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/7/29.村经济总收入情况
 */
public class BcjjzsrqkEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String cjjzsr;//	村经济总收入
    private String gyjj;//	公有经济
    private String cjtzzsr;//	村集体组织收入
    private String cjtqysr;//	村集体企业收入
    private String fgysr;//	非公有经济
    private String nhsr;//	农户收入
    private String syqysr;//	私营企业收入
    private String gtjj;//	个体经济
    private String ycsr;//	一产收入
    private String ecsr;//	二产收入
    private String scsr;//	三产收入
    private String create_time;
    private String update_time;
    private String years;//	年份
    private Long userId;
    private String nmhzs;//农民合作社

    private String cunname;//	填写名字
    private String zhenname;//审核名字
    private String qvname;//	审批名字
    private String shtime;//	审核时间
    private String sptime;//	审批时间
    private String xftime;//	下发时间
    private Integer process;//	流程  1 村填写  2 镇审核  3 区审批  4完结
    private String xzq;
    private String zhen;

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

    public String getNmhzs() {
        return nmhzs == null?"":nmhzs;
    }

    public void setNmhzs(String nmhzs) {
        this.nmhzs = nmhzs;
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

    public String getCjjzsr() {
        return cjjzsr == null?"":cjjzsr;
    }

    public void setCjjzsr(String cjjzsr) {
        this.cjjzsr = cjjzsr;
    }

    public String getGyjj() {
        return gyjj == null?"":gyjj;
    }

    public void setGyjj(String gyjj) {
        this.gyjj = gyjj;
    }

    public String getCjtzzsr() {
        return cjtzzsr == null?"":cjtzzsr;
    }

    public void setCjtzzsr(String cjtzzsr) {
        this.cjtzzsr = cjtzzsr;
    }

    public String  getCjtqysr() {
        return cjtqysr == null?"":cjtqysr;
    }

    public void setCjtqysr(String cjtqysr) {
        this.cjtqysr = cjtqysr;
    }

    public String getFgysr() {
        return fgysr == null?"":fgysr;
    }

    public void setFgysr(String fgysr) {
        this.fgysr = fgysr;
    }

    public String getNhsr() {
        return nhsr == null?"":nhsr;
    }

    public void setNhsr(String nhsr) {
        this.nhsr = nhsr;
    }

    public String getSyqysr() {
        return syqysr == null?"":syqysr;
    }

    public String getGtjj() {
        return gtjj==null?"":gtjj;
    }

    public void setGtjj(String gtjj) {
        this.gtjj = gtjj;
    }

    public void setSyqysr(String syqysr) {
        this.syqysr = syqysr;
    }

    public String getYcsr() {
        return ycsr == null?"":ycsr;
    }

    public void setYcsr(String ycsr) {
        this.ycsr = ycsr;
    }

    public String getEcsr() {
        return ecsr == null?"":ecsr;
    }

    public void setEcsr(String ecsr) {
        this.ecsr = ecsr;
    }

    public String getScsr() {
        return scsr == null?"":scsr;
    }

    public void setScsr(String scsr) {
        this.scsr = scsr;
    }

    public String getCreate_time() {
        return create_time == null?"":create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time == null?"":update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
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
