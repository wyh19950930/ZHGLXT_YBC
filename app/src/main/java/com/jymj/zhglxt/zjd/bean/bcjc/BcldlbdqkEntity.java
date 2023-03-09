package com.jymj.zhglxt.zjd.bean.bcjc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/7/28.劳动力变动情况
 */
public class BcldlbdqkEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String ldlzs;//	实际户籍就业劳动力总数
    private String nlss;//	按年龄划分--≤30
    private String nlls;//	按年龄划分--≥60
    private String cyyc;//	按产业划分--一产
    private String cyec;//	按产业划分--二产
    private String sysc;//	按产业划分--三产
    private String jtjy;//	按就业渠道划分--家庭经营
    private String cjtjy;//	按就业渠道划分--村集体经营
    private String cjtjjzz;//	按就业渠道划分--村集体经济组织(非企业)
    private String wclg;//	按就业渠道划分--外出劳工
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

    public String getLdlzs() {
        return ldlzs == null?"":ldlzs;
    }

    public void setLdlzs(String ldlzs) {
        this.ldlzs = ldlzs;
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

    public String getCyyc() {
        return cyyc == null?"":cyyc;
    }

    public void setCyyc(String cyyc) {
        this.cyyc = cyyc;
    }

    public String getCyec() {
        return cyec == null?"":cyec;
    }

    public void setCyec(String cyec) {
        this.cyec = cyec;
    }

    public String getSysc() {
        return sysc == null?"":sysc;
    }

    public void setSysc(String sysc) {
        this.sysc = sysc;
    }

    public String getJtjy() {
        return jtjy == null?"":jtjy;
    }

    public void setJtjy(String jtjy) {
        this.jtjy = jtjy;
    }

    public String getCjtjy() {
        return cjtjy == null?"":cjtjy;
    }

    public void setCjtjy(String cjtjy) {
        this.cjtjy = cjtjy;
    }

    public String getCjtjjzz() {
        return cjtjjzz == null?"":cjtjjzz;
    }

    public void setCjtjjzz(String cjtjjzz) {
        this.cjtjjzz = cjtjjzz;
    }

    public String getWclg() {
        return wclg == null?"":wclg;
    }

    public void setWclg(String wclg) {
        this.wclg = wclg;
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
