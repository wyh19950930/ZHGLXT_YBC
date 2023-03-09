package com.jymj.zhglxt.zjd.bean.bcjc.bcqh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/11/7.家庭社会保障
 */
public class JtshbzqkEntity {
    private Long id;
    private Long jtjcId;
    private String cjgygwrs;//	参加公益岗位人数
    private String hdnyzcbt;//	获得农业政策性补贴
    private String hzsfh;//	合作社盈余返还
    private String czyilbxrs;//	参加城镇职工基本医疗保险人数
    private String cxyilbxrs;//	参加城乡基本医疗保险人数
    private String czylbxrs;//	参加城镇职工基本养老保险人数
    private String cxylbxrs;//	参加城乡基本养老保险人数
    private String czzc;//最需要的财政支持项目
    private String czzcbz;//最需要的财政支持项目备注
    private String pxxm;//最需要的培训项目
    private String pxxmbz;//最需要的培训项目备注
    private String zyqd;//获取政策信息的主要渠道
    private String zyqdbz;//获取政策信息的主要渠道备注
    private String createTime;
    private String updateTime;
    private String years;
    private Long userId;

    private List<Long> jtczxqqkids;
    private List<JtczxqqkEntity> jtczxqqkEntities;

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

    public String getCjgygwrs() {
        return cjgygwrs==null?"":cjgygwrs;
    }

    public void setCjgygwrs(String cjgygwrs) {
        this.cjgygwrs = cjgygwrs;
    }

    public String getHdnyzcbt() {
        return hdnyzcbt==null?"":hdnyzcbt;
    }

    public void setHdnyzcbt(String hdnyzcbt) {
        this.hdnyzcbt = hdnyzcbt;
    }

    public String getHzsfh() {
        return hzsfh==null?"":hzsfh;
    }

    public void setHzsfh(String hzsfh) {
        this.hzsfh = hzsfh;
    }

    public String getCzyilbxrs() {
        return czyilbxrs==null?"":czyilbxrs;
    }

    public void setCzyilbxrs(String czyilbxrs) {
        this.czyilbxrs = czyilbxrs;
    }

    public String getCxyilbxrs() {
        return cxyilbxrs==null?"":cxyilbxrs;
    }

    public void setCxyilbxrs(String cxyilbxrs) {
        this.cxyilbxrs = cxyilbxrs;
    }

    public String getCzylbxrs() {
        return czylbxrs==null?"":czylbxrs;
    }

    public void setCzylbxrs(String czylbxrs) {
        this.czylbxrs = czylbxrs;
    }

    public String getCxylbxrs() {
        return cxylbxrs==null?"":cxylbxrs;
    }

    public void setCxylbxrs(String cxylbxrs) {
        this.cxylbxrs = cxylbxrs;
    }

    public String getCzzc() {
        return czzc==null?"":czzc;
    }

    public void setCzzc(String czzc) {
        this.czzc = czzc;
    }

    public String getCzzcbz() {
        return czzcbz==null?"":czzcbz;
    }

    public void setCzzcbz(String czzcbz) {
        this.czzcbz = czzcbz;
    }

    public String getPxxmbz() {
        return pxxmbz==null?"":pxxmbz;
    }

    public void setPxxmbz(String pxxmbz) {
        this.pxxmbz = pxxmbz;
    }

    public String getZyqdbz() {
        return zyqdbz==null?"":zyqdbz;
    }

    public void setZyqdbz(String zyqdbz) {
        this.zyqdbz = zyqdbz;
    }

    public String getPxxm() {
        return pxxm==null?"":pxxm;
    }

    public void setPxxm(String pxxm) {
        this.pxxm = pxxm;
    }

    public String getZyqd() {
        return zyqd==null?"":zyqd;
    }

    public void setZyqd(String zyqd) {
        this.zyqd = zyqd;
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

    public List<Long> getJtczxqqkids() {
        if (jtczxqqkids==null){
            jtczxqqkids = new ArrayList<>();
        }
        return jtczxqqkids;
    }

    public void setJtczxqqkids(List<Long> jtczxqqkids) {
        this.jtczxqqkids = jtczxqqkids;
    }

    public List<JtczxqqkEntity> getJtczxqqkEntities() {
        if (jtczxqqkEntities==null){
            jtczxqqkEntities = new ArrayList<>();
        }
        return jtczxqqkEntities;
    }

    public void setJtczxqqkEntities(List<JtczxqqkEntity> jtczxqqkEntities) {
        this.jtczxqqkEntities = jtczxqqkEntities;
    }
}
