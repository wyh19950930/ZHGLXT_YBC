package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljj on 2019/10/14.
 *
 */
public class ZjdLzEntity implements Serializable {

    private Integer id;
    private Integer ylId;
    private Integer sptype; //申请状态1提出申请2镇政府审核3镇政府备案4颁证5驳回
    private String sptypeText;
    private String sqDate; //申请日期
    private String sqName; //申请人
    private String iphone; //当事人电话
    private String jjphone; //紧急电话
    private String shDate; //审核日期
    private String baDate	;//备案日期
    private String bzDate	;//颁证日期
    private String bhDate	;//驳回
    private String remark; //备注
    private List<Integer> zjdFileIds;
    private YLEntity ylEntity;
    private List<ZjdFileEntity> zjdFileEntities;

    public List<ZjdFileEntity> getZjdFileEntities() {
        return zjdFileEntities;
    }

    public void setZjdFileEntities(List<ZjdFileEntity> zjdFileEntities) {
        this.zjdFileEntities = zjdFileEntities;
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

    public String getSptypeText(){
        if(sptype!=null){
            return ZjdLzEnum.getName(sptype);
        }
        return null;
    }

    public String getBhDate() {
        return bhDate==null?"":bhDate;
    }

    public void setBhDate(String bhDate) {
        this.bhDate = bhDate;
    }

    public String getSqDate() {
        return sqDate==null?"":sqDate;
    }

    public void setSqDate(String sqDate) {
        this.sqDate = sqDate;
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

    public String getBzDate() {
        return bzDate==null?"":bzDate;
    }

    public void setBzDate(String bzDate) {
        this.bzDate = bzDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Integer> getZjdFileIds() {
        return zjdFileIds;
    }

    public void setZjdFileIds(List<Integer> zjdFileIds) {
        this.zjdFileIds = zjdFileIds;
    }

    public YLEntity getYlEntity() {
        return ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public List<ZjdFileEntity> getZjdLzEntities() {
        return zjdFileEntities;
    }

    public void setZjdLzEntities(List<ZjdFileEntity> zjdLzEntities) {
        this.zjdFileEntities = zjdLzEntities;
    }
}
