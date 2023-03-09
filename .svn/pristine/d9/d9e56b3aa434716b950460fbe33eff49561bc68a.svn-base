package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dl on 2019/10/14.
 * 退出
 */
public class ZjdTcEntity implements Serializable {
    private Integer id;//
    private Integer ylId;//	yl---objectid
    private Integer sptype;//申请状态1提出申请2镇政府调查3签订协议4农业农村局备案5国土分局注销6审核驳回
    private String sqDate;//	申请日期
    private String sqName;//	申请人
    private String xyDate;//	date	签订协议日期
    private String baDate;//	date		备案日期
    private String zxDate;//	date注销日期
    private String dcDate;//	date		调查日期
    private String remark;//		备注	pg_catalog
    private String jjphone;//		紧急电话	pg_catalog
    private boolean isdelete;//	false	是否删除 false否  true是
    private String iphone;//	当事人电话
    private String sptypeText;//
    private List<Integer> zjdTcFileIds;
    private List<ZjdFileEntity> zjdFileEntityList;
    private String center;
    private String xzqmc;//村名
    private YLEntity ylEntity;

    public YLEntity getYlEntity() {
        return ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public List<ZjdFileEntity> getZjdFileEntityList() {
        return zjdFileEntityList;
    }

    public void setZjdFileEntityList(List<ZjdFileEntity> zjdFileEntityList) {
        this.zjdFileEntityList = zjdFileEntityList;
    }

    public List<Integer> getZjdTcFileIds() {
        return zjdTcFileIds;
    }

    public void setZjdTcFileIds(List<Integer> zjdTcFileIds) {
        this.zjdTcFileIds = zjdTcFileIds;
    }

    public Integer getId() {
        return id==null?0:id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYlId() {
        return ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public Integer getSptype() {
        return sptype;
    }

    public void setSptype(Integer sptype) {
        this.sptype = sptype;
    }

    public String getSqDate() {
        return sqDate==null?"":sqDate;
    }

    public void setSqDate(String sqDate) {
        this.sqDate = sqDate;
    }

    public String getSqName() {
        return sqName==null?"":sqName;
    }

    public void setSqName(String sqName) {
        this.sqName = sqName;
    }

    public String getXyDate() {
        return xyDate==null?"":xyDate;
    }

    public void setXyDate(String xyDate) {
        this.xyDate = xyDate;
    }

    public String getBaDate() {
        return baDate==null?"":baDate;
    }

    public void setBaDate(String baDate) {
        this.baDate = baDate;
    }

    public String getZxDate() {
        return zxDate==null?"":zxDate;
    }

    public void setZxDate(String zxDate) {
        this.zxDate = zxDate;
    }

    public String getDcDate() {
        return dcDate==null?"":dcDate;
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJjphone() {
        return jjphone;
    }

    public void setJjphone(String jjphone) {
        this.jjphone = jjphone;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getIphone() {
        return iphone==null?"":iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getSptypeText() {
        if (getSptype() != null){
            return ZjdTcEnum.getName(getSptype());
        }
        return "";
    }

    public void setSptypeText(String sptypeText) {
        this.sptypeText = sptypeText;
    }
}
