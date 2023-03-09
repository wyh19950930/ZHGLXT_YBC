package com.jymj.zhglxt.rjhj.bean.yl;


import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljj on 2018/8/30.
 */
public class ApplyEntity implements Serializable {

    private int id;
    //申请人
    private int userId;
    //'院落id'
    private int ylId;
    private YLEntity ylEntity;
    //'申请状态
    private Integer sptype;
    private String sptypeText;
    //'申请时间'
    private String sqDate;
    //'审批时间'
    private String spdate;
    //'审批内容'--备注
    private String spcontent;
    //'审批建筑面积'
    private float spjzmj;
    //是否删除 false否  true是
    private boolean isdelete = false;
    //'验收时间'
    private String ysdate;
    private String bhDate;//驳回
    //'验收人'
    private int ysUserId;
    //'当事人姓名'
    private String name;
    //'当事人电话'
    private String iphone;
    //'紧急电话'
    private String jjphone;
    //待申请时间
    private String dspdate;
    //行政区名称
    private String xzqmc;
    //户主名称
    private String hzmc;
    //门牌号
    private String mph;
    //面积
    private float area;
    //建筑面积
    private float jianzhuArea;
    //人口
    private int rk;
    //农业人口
    private int nongcount;
    //非农人口
    private int feinongcount;
    private String geometry;
  /*  //建筑面积
    private BigDecimal jianzhuArea;
    //建筑面积
    private BigDecimal jianzhuArea;
    //建筑面积
    private BigDecimal jianzhuArea;*/

    //施工时间
    private String sgdate;
    //是否有审批通知书 有-true  无-false
    private boolean ishave = false;
    //'审批人'
    private int spUserId;
    //审批意见
    private String remark;
    //'待验收'
    private String dysdate;

    //申请资料
    private List<ApplyFileEntity> applyFileList;
    //施工中子项目
    private ApplyChildEntity applyChildEntity;
    //驳回信息
    private List<ApplyLogEntity> applyLogEntityList;

    private float zjdArea;//宅基地面积
    private float fjzdArea;//房基占地面积
    private float jdgd;//基底高度
    private String address;//地址
    private String east;//东至
    private String south;//南至
    private String west;//西至
    private String north;//北至
    private int landtype;//地类
    private int jflx;//建房类型
    private String xybztjbh;//选用的通用标准图集编号
    private String ftysjbz;//非通用设计图采用的设计标准
    private String headTitanic;//文号头
    private String titanic;//文号
    private String code;
    private String zhen;


    private int counts;

    private ZhaiEntity zhaiEntity=new ZhaiEntity();//申请人信息
    private List<ZhaiEntity> zhaiEntities;//户籍信息
    private List<ApplyFwEntity> applyFwEntities;//院落房屋信息

    public String getHeadTitanic() {
        return headTitanic==null?"":headTitanic;
    }

    public void setHeadTitanic(String headTitanic) {
        this.headTitanic = headTitanic;
    }

    public String getTitanic() {
        return titanic==null?"":titanic;
    }

    public void setTitanic(String titanic) {
        this.titanic = titanic;
    }

    public ZhaiEntity getZhaiEntitie() {
        return zhaiEntity==null?new ZhaiEntity():zhaiEntity;
    }

    public void setZhaiEntitie(ZhaiEntity zhaiEntitie) {
        this.zhaiEntity = zhaiEntitie;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public YLEntity getYlEntity() {
        return ylEntity==null?new YLEntity():ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public String getHzmc() {
        return hzmc==null?"":hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public String getMph() {
        return mph==null?"":mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getJianzhuArea() {
        return jianzhuArea;
    }

    public void setJianzhuArea(float jianzhuArea) {
        this.jianzhuArea = jianzhuArea;
    }

    public int getRk() {
        return rk;
    }

    public void setRk(int rk) {
        this.rk = rk;
    }

    public int getNongcount() {
        return nongcount;
    }

    public void setNongcount(int nongcount) {
        this.nongcount = nongcount;
    }

    public int getFeinongcount() {
        return feinongcount;
    }

    public void setFeinongcount(int feinongcount) {
        this.feinongcount = feinongcount;
    }

    public boolean isIshave() {
        return ishave;
    }

    public float getZjdArea() {
        return zjdArea;
    }

    public void setZjdArea(float zjdArea) {
        this.zjdArea = zjdArea;
    }

    public float getFjzdArea() {
        return fjzdArea;
    }

    public void setFjzdArea(float fjzdArea) {
        this.fjzdArea = fjzdArea;
    }

    public float getJdgd() {
        return jdgd;
    }

    public void setJdgd(float jdgd) {
        this.jdgd = jdgd;
    }

    public String getAddress() {
        return address==null?"":address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEast() {
        return east==null?"":east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getSouth() {
        return south==null?"":south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west==null?"":west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getNorth() {
        return north==null?"":north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public int getLandtype() {
        return landtype;
    }

    public void setLandtype(int landtype) {
        this.landtype = landtype;
    }

    public int getJflx() {
        return jflx;
    }

    public void setJflx(int jflx) {
        this.jflx = jflx;
    }

    public String getXybztjbh() {
        return xybztjbh==null?"":xybztjbh;
    }

    public void setXybztjbh(String xybztjbh) {
        this.xybztjbh = xybztjbh;
    }

    public String getFtysjbz() {
        return ftysjbz==null?"":ftysjbz;
    }

    public void setFtysjbz(String ftysjbz) {
        this.ftysjbz = ftysjbz;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<ZhaiEntity> getZhaiEntities() {
        return zhaiEntities==null?new ArrayList<>():zhaiEntities;
    }

    public void setZhaiEntities(List<ZhaiEntity> zhaiEntities) {
        this.zhaiEntities = zhaiEntities;
    }

    public List<ApplyFwEntity> getApplyFwEntities() {
        return applyFwEntities==null?new ArrayList<>():applyFwEntities;
    }

    public void setApplyFwEntities(List<ApplyFwEntity> applyFwEntities) {
        this.applyFwEntities = applyFwEntities;
    }

    public void setSptypeText(String sptypeText) {
        this.sptypeText = sptypeText;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getBhDate() {
        return bhDate==null?"":bhDate;
    }

    public void setBhDate(String bhDate) {
        this.bhDate = bhDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYlId() {
        return ylId;
    }

    public void setYlId(int ylId) {
        this.ylId = ylId;
    }

    public YLEntity getYLEntity() {
        return ylEntity==null?new YLEntity():ylEntity;
    }

    public void setYLEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public Integer getSptype() {
        return sptype==null?0:sptype;
    }

    public void setSptype(Integer sptype) {
        this.sptype = sptype;
    }

    public String getSpdate() {
        return spdate==null?"":spdate;
    }

    public void setSpdate(String spdate) {
        this.spdate = spdate;
    }

    public int getSpUserId() {
        return spUserId;
    }

    public void setSpUserId(int spUserId) {
        this.spUserId = spUserId;
    }

    public String getSqDate() {
        return sqDate==null?"":sqDate.substring(0,10);
    }

    public void setSqDate(String sqDate) {
        this.sqDate = sqDate;
    }

    public String getSpcontent() {
        return spcontent==null?"":spcontent;
    }

    public void setSpcontent(String spcontent) {
        this.spcontent = spcontent;
    }

    public float getSpjzmj() {
        return spjzmj;
    }

    public void setSpjzmj(float spjzmj) {
        this.spjzmj = spjzmj;
    }

    public boolean isdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getYsdate() {
        return ysdate==null?"":ysdate;
    }

    public void setYsdate(String ysdate) {
        this.ysdate = ysdate;
    }

    public int getYsUserId() {
        return ysUserId;
    }

    public void setYsUserId(int ysUserId) {
        this.ysUserId = ysUserId;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIphone() {
        return iphone==null?"":iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getJjphone() {
        return jjphone==null?"":jjphone;
    }

    public void setJjphone(String jjphone) {
        this.jjphone = jjphone;
    }

    public String getDspdate() {
        return dspdate==null?"":dspdate;
    }

    public void setDspdate(String dspdate) {
        this.dspdate = dspdate;
    }

    public String getSgdate() {
        return sgdate==null?"":sgdate;
    }

    public void setSgdate(String sgdate) {
        this.sgdate = sgdate;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ApplyFileEntity> getApplyFileList() {
        return applyFileList==null?new ArrayList<>() :applyFileList;
    }

    public void setApplyFileList(List<ApplyFileEntity> applyFileList) {
        this.applyFileList = applyFileList;
    }

    public ApplyChildEntity getApplyChildEntity() {
        return applyChildEntity==null?new ApplyChildEntity():applyChildEntity;
    }

    public void setApplyChildEntity(ApplyChildEntity applyChildEntity) {
        this.applyChildEntity = applyChildEntity;
    }

    public List<ApplyLogEntity> getApplyLogEntityList() {
        return applyLogEntityList==null?new ArrayList<>():applyLogEntityList;
    }

    public void setApplyLogEntityList(List<ApplyLogEntity> applyLogEntityList) {
        this.applyLogEntityList = applyLogEntityList;
    }

    public String getDysdate() {
        return dysdate==null?"":dysdate;
    }

    public void setDysdate(String dysdate) {
        this.dysdate = dysdate;
    }

    public boolean getIshave() {
        if (ishave != true) {
            return false;
        }
        return ishave;
    }

    public void setIshave(boolean ishave) {
        this.ishave = ishave;
    }

    public String getSptypeText() {
        if (getSptype() != 0) {
            return ApplyEnum.getName(getSptype());
        }
        return "备案资料";
    }
}
