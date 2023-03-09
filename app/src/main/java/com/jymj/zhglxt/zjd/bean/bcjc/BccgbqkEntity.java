package com.jymj.zhglxt.zjd.bean.bcjc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/8/18.村干部基本情况
 */
public class BccgbqkEntity {
    private Long id;
    private String csjmc;//		村书记名称
    private String csjsjh;//		村书记手机号
    private Integer csjwhcd;//		村书记文化程度
    private Integer csjnl;//		村书记年龄
    private String csjrz;//		村书记任职时间
    private String czrmc;//		村主任名称
    private String czrsjh;//		村主任手机号
    private Integer czrwhcd;//		村主任文化程度
    private Integer czrnl;//		村主任年龄
    private String czrrz;//		村主任任职时间
    private String cszmc;//		村股份合作社社长名称
    private String cszsjh;//		村股份合作社社长手机号
    private Integer cszwhcd;//		村股份合作社社长文化程度
    private Integer csznl;//		村股份合作社社长年龄
    private String cszrz;//		村股份合作社社长任职时间
    private Integer dyrs;//		党员人数
    private Integer dyrs50;//		50岁以上的党员人数
    private String sfydysj;//		是否有“第一书记”
    private String dwmc;//		单位名称
    private String xzqmc;
    private String code;
    private String createTime;
    private String updateTime;
    private String years;//	年份
    private Long userId;

    private String cunname;//	填写名字
    private String zhenname;//	审核名字
    private String qvname;//	审批名字
    private String shtime;//	审核时间
    private String sptime;//	审批时间
    private Integer process;//	流程  1 村填写  2 镇审核  3 区审批
    private String xzq;//区
    private String zhen;//镇
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

    public String getCunname() {
        return cunname==null?"":cunname;
    }

    public void setCunname(String cunname) {
        this.cunname = cunname;
    }

    public String getZhenname() {
        return zhenname==null?"":zhenname;
    }

    public void setZhenname(String zhenname) {
        this.zhenname = zhenname;
    }

    public String getQvname() {
        return qvname==null?"":qvname;
    }

    public void setQvname(String qvname) {
        this.qvname = qvname;
    }

    public String getShtime() {
        return shtime==null?"":shtime;
    }

    public void setShtime(String shtime) {
        this.shtime = shtime;
    }

    public String getSptime() {
        return sptime==null?"":sptime;
    }

    public void setSptime(String sptime) {
        this.sptime = sptime;
    }

    public Integer getProcess() {
        return process==null?1:process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCsjmc() {
        return csjmc == null?"":csjmc;
    }

    public void setCsjmc(String csjmc) {
        this.csjmc = csjmc;
    }

    public String getCsjsjh() {
        return csjsjh == null?"":csjsjh;
    }

    public void setCsjsjh(String csjsjh) {
        this.csjsjh = csjsjh;
    }

    public Integer getCsjwhcd() {
        return csjwhcd==null?1:csjwhcd;
    }

    public void setCsjwhcd(Integer csjwhcd) {
        this.csjwhcd = csjwhcd;
    }

    public Integer getCsjnl() {
        return csjnl==null?0:csjnl;
    }

    public void setCsjnl(Integer csjnl) {
        this.csjnl = csjnl;
    }

    public String getCsjrz() {
        return csjrz;
    }

    public void setCsjrz(String csjrz) {
        this.csjrz = csjrz;
    }

    public String getCzrmc() {
        return czrmc == null?"":czrmc;
    }

    public void setCzrmc(String czrmc) {
        this.czrmc = czrmc;
    }

    public String getCzrsjh() {
        return czrsjh == null?"":czrsjh;
    }

    public void setCzrsjh(String czrsjh) {
        this.czrsjh = czrsjh;
    }

    public Integer getCzrwhcd() {
        return czrwhcd==null?1:czrwhcd;
    }

    public void setCzrwhcd(Integer czrwhcd) {
        this.czrwhcd = czrwhcd;
    }

    public Integer getCzrnl() {
        return czrnl==null?0:czrnl;
    }

    public void setCzrnl(Integer czrnl) {
        this.czrnl = czrnl;
    }

    public String getCzrrz() {
        return czrrz == null?"":czrrz;
    }

    public void setCzrrz(String czrrz) {
        this.czrrz = czrrz;
    }

    public String getCszmc() {
        return cszmc == null?"":cszmc;
    }

    public void setCszmc(String cszmc) {
        this.cszmc = cszmc;
    }

    public String getCszsjh() {
        return cszsjh == null?"":cszsjh;
    }

    public void setCszsjh(String cszsjh) {
        this.cszsjh = cszsjh;
    }

    public Integer getCszwhcd() {
        return cszwhcd==null?1:cszwhcd;
    }

    public void setCszwhcd(Integer cszwhcd) {
        this.cszwhcd = cszwhcd;
    }

    public Integer getCsznl() {
        return csznl==null?0:csznl;
    }

    public void setCsznl(Integer csznl) {
        this.csznl = csznl;
    }

    public String getCszrz() {
        return cszrz == null?"":cszrz;
    }

    public void setCszrz(String cszrz) {
        this.cszrz = cszrz;
    }

    public Integer getDyrs() {
        return dyrs==null?0:dyrs;
    }

    public void setDyrs(Integer dyrs) {
        this.dyrs = dyrs;
    }

    public Integer getDyrs50() {
        return dyrs50==null?0:dyrs50;
    }

    public void setDyrs50(Integer dyrs50) {
        this.dyrs50 = dyrs50;
    }

    public String getSfydysj() {
        return sfydysj == null?"A":sfydysj;
    }

    public void setSfydysj(String sfydysj) {
        this.sfydysj = sfydysj;
    }

    public String getDwmc() {
        return dwmc == null?"":dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
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
