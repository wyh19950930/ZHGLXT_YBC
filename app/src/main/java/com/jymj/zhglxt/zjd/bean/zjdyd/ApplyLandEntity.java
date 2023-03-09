package com.jymj.zhglxt.zjd.bean.zjdyd;

import com.jymj.zhglxt.rjhj.bean.YLEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ApplyFwEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${lc} on 2021/9/1.
 */
public class ApplyLandEntity implements Serializable {
    private static final long serialVersionUID = -1360515728465008972L;
    
    private Integer id;
    private Integer userId;//	记录人
    private Integer ylId;//	院落id
    private Integer apUserId;//	申请人
    private int sptype;//	申请状态1备案2提交资料3待审核4审核通过5审核驳回
    private String sqDate;//	申请时间
    private String spdate;//	审批时间
    private String shdate;//	审核时间
    private boolean isdelete;//	是否删除 false否  true是
    private String name;//	当事人姓名
    private String iphone;//	当事人电话
    private String jjphone;//	紧急电话
    private String bhDate;//	驳回时间
    private double zjdArea;//	宅基地面积
    private double	fjzdArea;//	房基占地面积
    private double	jdgd;//	基底高度
    private String address;//	地址
    private String east;//	东至
    private String south;//	南至
    private String west;//	西至
    private String north;//	北至
    private int landtype;//	地类
    private int jflx;//	建房类型
    private String xybztjbh;//	选用的通用标准图集编号
    private String ftysjbz;//	非通用设计图采用的设计标准
    private String xzqmc;//
    private String code;//
    private String zhen;//
    private int household;//	分户  第几户
    private String geometry;
    private String center;

    private ZhaiEntity zhaiEntity;//户主信息
    private ArrayList<ApplyLandFile> applyLandFiles=new ArrayList<>();
    private ArrayList<ZhaiEntity> zhaiEntities=new ArrayList<>();
    private ArrayList<ApplyFwEntity> applyFwEntities=new ArrayList<>();
    private YLEntity ylEntity;

    //***************院落的数据************************************
    //面积
    private double area;
    //建筑面积
    private double jianzhuArea;
    private double ylArea;	//院落面积
    private double ylJzmj;	//院落建筑面积
    private String qszsh;//权属证书号
    private Integer czqkint;//czqkint   院落加的int类型的宅基地处置情况
    private String czqk;//宅基地处置情况
    //院落id
    private Integer objectid;
    private String titanic;//文号
    private String headTitanic;//文号头
    //院落几何形状 by DL 2018年1月23日
//    private String geometry;


    public String getTitanic() {
        return titanic==null?"":titanic;
    }

    public void setTitanic(String titanic) {
        this.titanic = titanic;
    }

    public String getHeadTitanic() {
        return headTitanic==null?"":headTitanic;
    }

    public void setHeadTitanic(String headTitanic) {
        this.headTitanic = headTitanic;
    }

    public double getYlArea() {
        return ylArea;
    }

    public void setYlArea(double ylArea) {
        this.ylArea = ylArea;
    }

    public double getYlJzmj() {
        return ylJzmj;
    }

    public void setYlJzmj(double ylJzmj) {
        this.ylJzmj = ylJzmj;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getJianzhuArea() {
        return jianzhuArea;
    }

    public void setJianzhuArea(double jianzhuArea) {
        this.jianzhuArea = jianzhuArea;
    }

    public String getQszsh() {
        return qszsh==null?"":qszsh;
    }

    public void setQszsh(String qszsh) {
        this.qszsh = qszsh;
    }

    public Integer getCzqkint() {
        return czqkint==null?1:czqkint;
    }

    public void setCzqkint(Integer czqkint) {
        this.czqkint = czqkint;
    }

    public String getCzqk() {
        return czqk==null?"":czqk;
    }

    public void setCzqk(String czqk) {
        this.czqk = czqk;
    }

    public Integer getObjectid() {
        return objectid==null?0:objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public Integer getId() {
        return id==null?0:id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getYlId() {
        return ylId==null?0:ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public Integer getApUserId() {
        return apUserId==null?0:apUserId;
    }

    public void setApUserId(Integer apUserId) {
        this.apUserId = apUserId;
    }

    public int getSptype() {
        return sptype;
    }

    public void setSptype(int sptype) {
        this.sptype = sptype;
    }

    public String getSqDate() {
        return sqDate==null?"":sqDate;
    }

    public void setSqDate(String sqDate) {
        this.sqDate = sqDate;
    }

    public String getSpdate() {
        return spdate==null?"":spdate;
    }

    public void setSpdate(String spdate) {
        this.spdate = spdate;
    }

    public String getShdate() {
        return shdate==null?"":shdate;
    }

    public void setShdate(String shdate) {
        this.shdate = shdate;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
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

    public String getBhDate() {
        return bhDate==null?"":bhDate;
    }

    public void setBhDate(String bhDate) {
        this.bhDate = bhDate;
    }

    public double getZjdArea() {
        return zjdArea;
    }

    public void setZjdArea(double zjdArea) {
        this.zjdArea = zjdArea;
    }

    public double getFjzdArea() {
        return fjzdArea;
    }

    public void setFjzdArea(double fjzdArea) {
        this.fjzdArea = fjzdArea;
    }

    public double getJdgd() {
        return jdgd;
    }

    public void setJdgd(double jdgd) {
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

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public int getHousehold() {
        return household;
    }

    public void setHousehold(int household) {
        this.household = household;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public ZhaiEntity getZhaiEntity() {
        return zhaiEntity==null?new ZhaiEntity():zhaiEntity;
    }

    public void setZhaiEntity(ZhaiEntity zhaiEntity) {
        this.zhaiEntity = zhaiEntity;
    }

    public ArrayList<ApplyLandFile> getApplyLandFiles() {
        if (applyLandFiles==null){
            applyLandFiles = new ArrayList<>();
        }
        return applyLandFiles;
    }

    public void setApplyLandFiles(ArrayList<ApplyLandFile> applyLandFiles) {
        this.applyLandFiles = applyLandFiles;
    }

    public ArrayList<ZhaiEntity> getZhaiEntities() {
        if (zhaiEntities==null){
            zhaiEntities = new ArrayList<>();
        }
        return zhaiEntities;
    }

    public void setZhaiEntities(ArrayList<ZhaiEntity> zhaiEntities) {
        this.zhaiEntities = zhaiEntities;
    }

    public ArrayList<ApplyFwEntity> getApplyFwEntities() {
        if (applyFwEntities==null){
            applyFwEntities = new ArrayList<>();
        }
        return applyFwEntities;
    }

    public void setApplyFwEntities(ArrayList<ApplyFwEntity> applyFwEntities) {
        this.applyFwEntities = applyFwEntities;
    }

    public YLEntity getYlEntity() {
        if (ylEntity==null){
            ylEntity = new YLEntity();
        }
        return ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }
}
