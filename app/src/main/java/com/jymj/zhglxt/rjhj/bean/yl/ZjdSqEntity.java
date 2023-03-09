package com.jymj.zhglxt.rjhj.bean.yl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dl on 2019/10/16.
 */
public class ZjdSqEntity implements Serializable {
    private Integer id;//
    private Integer ylId;//
    private String sqName;//	申请人姓名
    private String iphone;//申请人电话
    private String jjphone;//	紧急电话
    private Integer sptype;//状态  1 申请 2 镇政府审核 3 农业农村局备案 4 规自委确权 5申请驳回
    private String sqDate;//	申请日期
    private String shDate;//	审核时间
    private String baDate;//备案时间
    private String qqDate;//	确权时间
    private boolean isdelete;//是否删除 false否  true是
    private String remark;//	备注
    private String sptypeText;
    private List<Integer> fileIds;
    private String bhDate;
    private String geom;
    private List<ZjdFileEntity> zjdFileEntityList;

    public List<ZjdFileEntity> getZjdFileEntityList() {
        return zjdFileEntityList==null?new ArrayList<>():zjdFileEntityList;
    }

    public void setZjdFileEntityList(List<ZjdFileEntity> zjdFileEntityList) {
        this.zjdFileEntityList = zjdFileEntityList;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getBhDate() {
        return bhDate==null?"":bhDate;
    }

    public void setBhDate(String bhDate) {
        this.bhDate = bhDate;
    }

    public List<Integer> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Integer> fileIds) {
        this.fileIds = fileIds;
    }

    public String getSptypeText(){
        if(sptype!=null){
            return ZjdSqEnum.getName(sptype);
        }
        return null;
    }

    public void setSptypeText(String sptypeText) {
        this.sptypeText = sptypeText;
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

    public String getSqName() {
        return sqName;
    }

    public void setSqName(String sqName) {
        this.sqName = sqName;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getJjphone() {
        return jjphone;
    }

    public void setJjphone(String jjphone) {
        this.jjphone = jjphone;
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

    public String getShDate() {
        return shDate==null?"":shDate;
    }

    public void setShDate(String shDate) {
        this.shDate = shDate;
    }

    public String getBaDate() {
        return baDate==null?"":baDate;
    }

    public void setBaDate(String baDate) {
        this.baDate = baDate;
    }

    public String getQqDate() {
        return qqDate==null?"":qqDate;
    }

    public void setQqDate(String qqDate) {
        this.qqDate = qqDate;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void save(ZjdSqEntity zjdSqEntity) {
    }
}
