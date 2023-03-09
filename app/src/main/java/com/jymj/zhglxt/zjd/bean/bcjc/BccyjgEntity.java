package com.jymj.zhglxt.zjd.bean.bcjc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lc on 2022/7/28.产业结构
 */
public class BccyjgEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String dycy;//	种植业
    private String zzy;//	种植业
    private String zzyls;//	种植业--粮食
    private String zzysc;//	种植业--蔬菜
    private String ly;//	林业
    private String lylg;//	林业--林果
    private String lysg;//	林业--水果
    private String my;//	牧业
    private String yy;//	渔业
    private String decy;//	第二产业
    private String dscy;//	第三产业
    private String dscms;//民宿
    private String dscfd;//餐饮
    private String dscqt;//其他
    private String scfw;//	生产性服务
    private String shfw;//	生活性服务
    private Integer lx;//	类型  1主要产品  2产值  3吸纳本村就业  4吸纳外来就业
    private String createTime;
    private String updateTime;
    private String years;//	年份
    private Long userId;

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

    public String getDycy() {
        return dycy==null?"":dycy;
    }

    public void setDycy(String dycy) {
        this.dycy = dycy;
    }

    public Long getId() {
        return id == null?0L:id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getZzy() {
        return zzy==null?"":zzy;
    }

    public void setZzy(String zzy) {
        this.zzy = zzy;
    }

    public String getZzyls() {
        return zzyls==null?"":zzyls;
    }

    public void setZzyls(String zzyls) {
        this.zzyls = zzyls;
    }

    public String getZzysc() {
        return zzysc==null?"":zzysc;
    }

    public void setZzysc(String zzysc) {
        this.zzysc = zzysc;
    }

    public String getLy() {
        return ly==null?"":ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getLylg() {
        return lylg==null?"":lylg;
    }

    public void setLylg(String lylg) {
        this.lylg = lylg;
    }

    public String getLysg() {
        return lysg==null?"":lysg;
    }

    public void setLysg(String lysg) {
        this.lysg = lysg;
    }

    public String getMy() {
        return my==null?"":my;
    }

    public void setMy(String my) {
        this.my = my;
    }

    public String getYy() {
        return yy==null?"":yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getDecy() {
        return decy==null?"":decy;
    }

    public void setDecy(String decy) {
        this.decy = decy;
    }

    public String getDscy() {
        return dscy==null?"":dscy;
    }

    public void setDscy(String dscy) {
        this.dscy = dscy;
    }

    public String getDscms() {
        return dscms==null?"":dscms;
    }

    public void setDscms(String dscms) {
        this.dscms = dscms;
    }

    public String getDscfd() {
        return dscfd==null?"":dscfd;
    }

    public void setDscfd(String dscfd) {
        this.dscfd = dscfd;
    }

    public String getDscqt() {
        return dscqt==null?"":dscqt;
    }

    public void setDscqt(String dscqt) {
        this.dscqt = dscqt;
    }

    public String getScfw() {
        return scfw==null?"":scfw;
    }

    public void setScfw(String scfw) {
        this.scfw = scfw;
    }

    public String getShfw() {
        return shfw==null?"":shfw;
    }

    public void setShfw(String shfw) {
        this.shfw = shfw;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
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
        return years==null?"":years;
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
