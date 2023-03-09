package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.bean.enums.HuTypeEnum;
import com.jymj.zhglxt.bean.enums.HyzkEnum;
import com.jymj.zhglxt.bean.enums.JzsyEnum;
import com.jymj.zhglxt.bean.enums.SexEnum;
import com.jymj.zhglxt.bean.enums.WhcdEnum;
import com.jymj.zhglxt.bean.enums.ZjxyEnum;
import com.jymj.zhglxt.bean.enums.ZslbEnum;
import com.jymj.zhglxt.bean.enums.ZzmmEnum;
import com.jymj.zhglxt.rjhj.bean.YLEntity;
import com.jymj.zhglxt.rjhj.bean.yl.enums.CszyEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.DjfzqkEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ljj on 2018/6/7.
 */
public class FloatPopulat implements Serializable {
    private Integer id;
    private String name;//姓名**********************************必填
    private String hzmc;//户主名称**********************************
    private Integer sex;//性别**********************************必填
    private String sexText;
    private String idCard;//身份证号**********************************必填
    private String address;//常住地址**********************************必填
    private String birthday;//出生日期
    private Integer ylId;//**********************************必填
    private YLEntity ylEntity;
    private String code;
    //民族
    private String nation;//**********************************
    //录入人
    private Integer enPsn;
    //录入时间
    private String enDate;
    //修改人
    private Integer upPsn;
    //修改时间
    private String upDate;
    //省
    private String province;//**********************************
    //县
    private String county;//**********************************
    //'电话
    private String iphone;//**********************************
    //'文化程度
    private Integer whcd;//**********************************
    private String whcdText;
   /* //'行业类型
    private Integer hylx;
    private String hylxText;*/
    //'婚姻状况
    private Integer hyzk;//**********************************
    private String hyzkText;
    //'居住事由'
    private Integer jzsy;//**********************************
    private String jzsyText;
    //'从事职业'
    private Integer cszy;//**********************************
    private String cszyText;
    //'住所类别'
    private Integer zslb;//**********************************
    private String zslbText;
    //'政治面貌'
    private Integer zzmm;//**********************************
    private String zzmmText;
    //'宗教信仰'
    private Integer zjxy;//**********************************
    private String zjxyText;
    //'户别'
    private Integer hb;//**********************************
    private String hbText;
    //'登记及发证情况1登记类2居住证'
    private Integer djfz;//**********************************
    private String djfzText;
    //'居住证号码'
    private String jzzhm;//**********************************
    //'登记日期'
    private String djdate;//**********************************
    //'预计到期日期'
    private String dqdate;//**********************************
    //'出租房东姓名'
    private String fdname;//**********************************
    //'起租日期'
    private String czStartDate;//**********************************
    //'停租日期'
    private String czEndDate;//**********************************
    //'房东电话'
    private String fdphone;//**********************************
    //'工作单位'
    private String gzdw;//**********************************
    //'工作时间'
    private String gzsj;//**********************************
    //'照片地址'
    private String photourl;
    //'随行人数'
    private Integer sxrk;
    //居住地址
    private String jzdz;//**********************************
    //流入true
    // 流出false
    private boolean lrlc;//**********************************
    //村名
    private String xzqmc;//**********************************
    private BigDecimal zlmj;//租赁面积//**********************************
    private BigDecimal zlje;//租赁金额//**********************************
 private ArrayList<FloatFileEntity> floatFileEntities;

    public String getHzmc() {
        return hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    //    private List<FloatPopSxEty> floatPopSxEtyList;
    private Integer[] ids;//添加的时候上传图片id

    public ArrayList<FloatFileEntity> getFloatFileEntities() {
        return floatFileEntities;
    }

    public void setFloatFileEntities(ArrayList<FloatFileEntity> floatFileEntities) {
        this.floatFileEntities = floatFileEntities;
    }

    public BigDecimal getZlmj() {
        return zlmj==null?new BigDecimal(0):zlmj;
    }

    public void setZlmj(BigDecimal zlmj) {
        this.zlmj = zlmj;
    }

    public BigDecimal getZlje() {
        return zlje==null?new BigDecimal(0):zlje;
    }

    public void setZlje(BigDecimal zlje) {
        this.zlje = zlje;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getId() {
        return id==null?-1:id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexText() {
        if (getSex()==null)
            return null;
        return SexEnum.getName(getSex());
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getYlId() {
        return ylId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday==null?"请选择时间":birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getEnPsn() {
        return enPsn;
    }

    public void setEnPsn(Integer enPsn) {
        this.enPsn = enPsn;
    }

    public String getEnDate() {
        return enDate;
    }

    public void setEnDate(String enDate) {
        this.enDate = enDate;
    }

    public Integer getUpPsn() {
        return upPsn;
    }

    public void setUpPsn(Integer upPsn) {
        this.upPsn = upPsn;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Integer getWhcd() {
        return whcd;
    }

    public void setWhcd(Integer whcd) {
        this.whcd = whcd;
    }

    public Integer getHyzk() {
        return hyzk;
    }

    public void setHyzk(Integer hyzk) {
        this.hyzk = hyzk;
    }

    public Integer getJzsy() {
        return jzsy;
    }

    public void setJzsy(Integer jzsy) {
        this.jzsy = jzsy;
    }

    public Integer getCszy() {
        return cszy;
    }

    public void setCszy(Integer cszy) {
        this.cszy = cszy;
    }

    public Integer getZslb() {
        return zslb;
    }

    public void setZslb(Integer zslb) {
        this.zslb = zslb;
    }

    public Integer getZzmm() {
        return zzmm;
    }

    public void setZzmm(Integer zzmm) {
        this.zzmm = zzmm;
    }

    public Integer getZjxy() {
        return zjxy;
    }

    public void setZjxy(Integer zjxy) {
        this.zjxy = zjxy;
    }

    public Integer getHb() {
        return hb;
    }

    public void setHb(Integer hb) {
        this.hb = hb;
    }

    public Integer getDjfz() {
        return djfz;
    }

    public void setDjfz(Integer djfz) {
        this.djfz = djfz;
    }

    public String getJzzhm() {
        return jzzhm;
    }

    public void setJzzhm(String jzzhm) {
        this.jzzhm = jzzhm;
    }

    public String getDjdate() {
        return djdate==null?"请选择时间":djdate;
    }

    public void setDjdate(String djdate) {
        this.djdate = djdate;
    }

    public String getDqdate() {
        return dqdate==null?"请选择时间":dqdate;
    }

    public void setDqdate(String dqdate) {
        this.dqdate = dqdate;
    }

    public String getFdname() {
        return fdname;
    }

    public void setFdname(String fdname) {
        this.fdname = fdname;
    }

    public String getCzStartDate() {
        return czStartDate==null?"请选择时间":czStartDate;
    }

    public void setCzStartDate(String czStartDate) {
        this.czStartDate = czStartDate;
    }

    public String getCzEndDate() {
        return czEndDate==null?"请选择时间":czEndDate;
    }

    public void setCzEndDate(String czEndDate) {
        this.czEndDate = czEndDate;
    }

    public String getFdphone() {
        return fdphone;
    }

    public void setFdphone(String fdphone) {
        this.fdphone = fdphone;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getGzsj() {
        return gzsj==null?"请选择时间":gzsj;
    }

    public void setGzsj(String gzsj) {
        this.gzsj = gzsj;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public Integer getSxrk() {
        return sxrk;
    }

    public void setSxrk(Integer sxrk) {
        this.sxrk = sxrk;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

   /* public List<FloatPopSxEty> getFloatPopSxEtyList() {
        return floatPopSxEtyList;
    }

    public void setFloatPopSxEtyList(List<FloatPopSxEty> floatPopSxEtyList) {
        this.floatPopSxEtyList = floatPopSxEtyList;
    }*/

    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }

    public boolean isLrlc() {
        return lrlc;
    }

    public void setLrlc(boolean lrlc) {
        this.lrlc = lrlc;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

 /*   public Integer getHylx() {
        return hylx;
    }

    public void setHylx(Integer hylx) {
        this.hylx = hylx;
    }

   *//* public String getHylxText() {
        if (getHylx()==null)
            return null;
        return Hylx.getName(getHylx());
    }*//*

    public void setHylxText(String hylxText) {
        this.hylxText = hylxText;
    }*/

    public String getCszyText(){
        if (getCszy()==null)
            return null;
        return CszyEnum.getName(getCszy());
    }

    public String getHyzkText(){
        if (getHyzk()==null)
            return null;
        return HyzkEnum.getName(getHyzk());
    }

    public String getJzsyText(){
        if(getJzsy()==null)
            return null;
        return JzsyEnum.getName(getJzsy());
    }

    public String getWhcdText(){
        if (getWhcd()==null)
            return null;
        return WhcdEnum.getName(getWhcd());
    }
    public String getZjxyText(){
        if(getZjxy()==null)
            return null;
        return ZjxyEnum.getName(getZjxy());
    }
    public String getZslbText(){
        if(getZslb()==null){
            return null;
        }
        return ZslbEnum.getName(getZslb());
    }
    public String getZzmmText(){
        if(getZzmm()==null){
            return null;
        }
        return ZzmmEnum.getName(getZzmm());
    }
    public String getHbText(){
        if(getHb()==null){
            return null;
        }
        return HuTypeEnum.getName(getHb());
    }
    public String getDjfzText(){
        if(getDjfz()==null){
            return null;
        }
        return DjfzqkEnum.getName(getDjfz());
    }

}
