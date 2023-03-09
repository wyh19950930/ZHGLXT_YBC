package com.jymj.zhglxt.zjd.bean.yzt;

import com.jymj.zhglxt.bean.enums.SexEnum;
import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2022/4/2.
 */
public class HujiEntity  implements Serializable {
    private Integer zhaiid;
    //户籍成员
    private String housecount;
    //社会关系  户主 之妻 之子 之女 之孙等
    private Integer socialrelat;
    private String socialrelatText;
    //就业情况
    private Integer empsituation;
    private String empsituationText;
    //户别
    private Integer huType;
    private String huTypeText;
    //院落编号id
    private String ylbhid;
    private String ysbhid;
    //出生年月
    private String birthday;
    //备注
    private String text;
    //是否删除
    private boolean isdelete;
    //院落id
    private Integer ylId;
    //院落
    private YLEntity ylEntity;
    private String idCard;
    private Integer sex;
    private String sexText;
    //操作人
    private String noteTaker;
    //信息提供者
    private String noteInfo;
    //信息来源
    private String infoRes;
    //民族
    private Integer nation;
    private String nationText;
    //户口迁移情况
    private String housemove;
    //村名
    private String xzqmc;
    //年龄
    private Integer age;
    //户主
    private Integer fatherid;
    //户主名称
    private String fatherName;
    //yl中心点
    private String center;

    //'电话
    private String phone;
    //'文化程度
    private Integer whcd;
    private String whcdText;
    //'婚姻状况
    private Integer hyzk;
    private String hyzkText;
    //'政治面貌'
    private Integer zzmm;
    private String zzmmText;
    //'是否劳动力'
    private Integer ldl;
    private String ldlText;
    //是否集体经济组织成员
    private Integer zzcy;
    private String zzcyText;
    //'是否人户分离'
    private Integer rhfl;
    private String rhflText;
    //'人员状态'
    private Integer ryzt;
    private String ryztText;
    //'股东'
    private Integer gd;

    private Integer fjhz = 0;//翻建户主

    private String hjdz;//户籍地址

    private String code;//
    private Integer household;
    private Integer hierarchy;//层级

    private List<HujiRelationEntity> hujiRelationEntities;//关系表

    public Integer getZhaiid() {
        return zhaiid==null?0:zhaiid;
    }

    public void setZhaiid(Integer zhaiid) {
        this.zhaiid = zhaiid;
    }

    public String getHousecount() {
        return housecount==null?"":housecount;
    }

    public void setHousecount(String housecount) {
        this.housecount = housecount;
    }

    public Integer getSocialrelat() {
        return socialrelat==null?0:socialrelat;
    }

    public void setSocialrelat(Integer socialrelat) {
        this.socialrelat = socialrelat;
    }

    public String getSocialrelatText() {
        return socialrelatText==null?"":socialrelatText;
    }

    public void setSocialrelatText(String socialrelatText) {
        this.socialrelatText = socialrelatText;
    }

    public Integer getEmpsituation() {
        return empsituation==null?0:empsituation;
    }

    public void setEmpsituation(Integer empsituation) {
        this.empsituation = empsituation;
    }

    public String getEmpsituationText() {
        return empsituationText==null?"":empsituationText;
    }

    public void setEmpsituationText(String empsituationText) {
        this.empsituationText = empsituationText;
    }

    public Integer getHuType() {
        return huType==null?0:huType;
    }

    public void setHuType(Integer huType) {
        this.huType = huType;
    }

    public String getHuTypeText() {
        return huTypeText==null?"":huTypeText;
    }

    public void setHuTypeText(String huTypeText) {
        this.huTypeText = huTypeText;
    }

    public String getYlbhid() {
        return ylbhid==null?"":ylbhid;
    }

    public void setYlbhid(String ylbhid) {
        this.ylbhid = ylbhid;
    }

    public String getYsbhid() {
        return ysbhid==null?"":ysbhid;
    }

    public void setYsbhid(String ysbhid) {
        this.ysbhid = ysbhid;
    }

    public String getBirthday() {
        return birthday==null?"":birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getText() {
        return text==null?"":text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getYlId() {
        return ylId==null?0:ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public YLEntity getYlEntity() {
        return ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public String getIdCard() {
        return idCard==null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSex() {
        return sex==null?0:sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexText() {
        return sexText==null?"":sexText;//sexText==null?"":sexText
    }

    public void setSexText(String sexText) {
        this.sexText = sexText;
    }

    public String getNoteTaker() {
        return noteTaker==null?"":noteTaker;
    }

    public void setNoteTaker(String noteTaker) {
        this.noteTaker = noteTaker;
    }

    public String getNoteInfo() {
        return noteInfo==null?"":noteInfo;
    }

    public void setNoteInfo(String noteInfo) {
        this.noteInfo = noteInfo;
    }

    public String getInfoRes() {
        return infoRes==null?"":infoRes;
    }

    public void setInfoRes(String infoRes) {
        this.infoRes = infoRes;
    }

    public Integer getNation() {
        return nation==null?0:nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getNationText() {
        return nationText==null?"":nationText;
    }

    public void setNationText(String nationText) {
        this.nationText = nationText;
    }

    public String getHousemove() {
        return housemove==null?"":housemove;
    }

    public void setHousemove(String housemove) {
        this.housemove = housemove;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer getAge() {
        return age==null?0:age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getFatherid() {
        return fatherid==null?0:fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public String getFatherName() {
        return fatherName==null?"":fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getPhone() {
        return phone==null?"":phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getWhcd() {
        return whcd==null?0:whcd;
    }

    public void setWhcd(Integer whcd) {
        this.whcd = whcd;
    }

    public String getWhcdText() {
        return whcdText==null?"":whcdText;
    }

    public void setWhcdText(String whcdText) {
        this.whcdText = whcdText;
    }

    public Integer getHyzk() {
        return hyzk==null?0:hyzk;
    }

    public void setHyzk(Integer hyzk) {
        this.hyzk = hyzk;
    }

    public String getHyzkText() {
        return hyzkText==null?"":hyzkText;
    }

    public void setHyzkText(String hyzkText) {
        this.hyzkText = hyzkText;
    }

    public Integer getZzmm() {
        return zzmm==null?0:zzmm;
    }

    public void setZzmm(Integer zzmm) {
        this.zzmm = zzmm;
    }

    public String getZzmmText() {
        return zzmmText==null?"":zzmmText;
    }

    public void setZzmmText(String zzmmText) {
        this.zzmmText = zzmmText;
    }

    public Integer getLdl() {
        return ldl==null?0:ldl;
    }

    public void setLdl(Integer ldl) {
        this.ldl = ldl;
    }

    public String getLdlText() {
        return ldlText==null?"":ldlText;
    }

    public void setLdlText(String ldlText) {
        this.ldlText = ldlText;
    }

    public Integer getZzcy() {
        return zzcy==null?0:zzcy;
    }

    public void setZzcy(Integer zzcy) {
        this.zzcy = zzcy;
    }

    public String getZzcyText() {
        return zzcyText==null?"":zzcyText;
    }

    public void setZzcyText(String zzcyText) {
        this.zzcyText = zzcyText;
    }

    public Integer getRhfl() {
        return rhfl==null?0:rhfl;
    }

    public void setRhfl(Integer rhfl) {
        this.rhfl = rhfl;
    }

    public String getRhflText() {
        return rhflText==null?"":rhflText;
    }

    public void setRhflText(String rhflText) {
        this.rhflText = rhflText;
    }

    public Integer getRyzt() {
        return ryzt==null?0:ryzt;
    }

    public void setRyzt(Integer ryzt) {
        this.ryzt = ryzt;
    }

    public String getRyztText() {
        return ryztText==null?"":ryztText;
    }

    public void setRyztText(String ryztText) {
        this.ryztText = ryztText;
    }

    public Integer getGd() {
        return gd==null?0:gd;
    }

    public void setGd(Integer gd) {
        this.gd = gd;
    }

    public Integer getFjhz() {
        return fjhz==null?0:fjhz;
    }

    public void setFjhz(Integer fjhz) {
        this.fjhz = fjhz;
    }

    public String getHjdz() {
        return hjdz==null?"":hjdz;
    }

    public void setHjdz(String hjdz) {
        this.hjdz = hjdz;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHousehold() {
        return household==null?0:household;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }

    public Integer getHierarchy() {
        return hierarchy==null?0:hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public List<HujiRelationEntity> getHujiRelationEntities() {
        return hujiRelationEntities==null?new ArrayList<>() :hujiRelationEntities;
    }

    public void setHujiRelationEntities(List<HujiRelationEntity> hujiRelationEntities) {
        this.hujiRelationEntities = hujiRelationEntities;
    }
}
