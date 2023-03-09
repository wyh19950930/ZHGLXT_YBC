package com.jymj.zhglxt.zjd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 拆腾按制第三个页面的实体类（查看列表）或点查询
 */
public class YlEntity implements Serializable {
    private Integer gid;
    private Integer objectid ;
    private Integer ds;
    private Integer ncount;
    private Integer feinong;
    private Integer hs;
    private Integer rk;
    private String landType;
    private String tsqk;
    private String xzqmc;
    private String townname;
    private String code;
    private String bh;
    private String remark;
    private String wymc;
    private String mph;
    //户主姓名
    private String hzmc;
    private String fqd;
    //平行家庭
    private String pxjt;
    //一户多宅
    private String yhdz;
    //占地面积
    private BigDecimal area;
    //集体经济组织成员
    private BigDecimal jtjjzzcy;
    private String geom;
    private String center;
    //保障房套数
    private Integer bzfts;
    private String geometry;
    private Integer nongcount;
    private Integer feinongcount;
    private Integer hucount;
    //建筑面积
    private BigDecimal jianzhuArea;
    //院落置换数
    private Integer ylzh;
    //雷达图  用地面积
    private Float ldArea=0f;
    //雷达图   人口数
    private Float ldRk=0f;
    //雷达图   取得方式
    private Float ldQdfs=0f;
    //雷达图   容积率
    private Float ldRjl=0f;
    //雷达图   低价关系
    private Float ldDjgx=0f;
    private String hylx;  //行业类型

    private Integer apptype;
    private Integer sqtype;
    private Integer lztype;
    private Integer tctype;
    private String situation;//	varchar	255	0	-1		违法描述	pg_catalog	default	0	0
    //用地类型
    private Integer ylType;
    private String ylTypeText;
    private List<String> files;

    public List<String> getFiles() {
        return files==null?new ArrayList<>():files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getTownname() {
        return townname==null?"":townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public Integer getYlType() {
        return ylType;
    }

    public void setYlType(Integer ylType) {
        this.ylType = ylType;
    }

    public String getYlTypeText() {
        return ylTypeText;
    }

    public void setYlTypeText(String ylTypeText) {
        this.ylTypeText = ylTypeText;
    }

    public Integer getNongcount() {
        return nongcount==null?0:nongcount;
    }

    public void setNongcount(Integer nongcount) {
        this.nongcount = nongcount;
    }

    public Integer getFeinongcount() {
        return feinongcount==null?0:feinongcount;
    }

    public void setFeinongcount(Integer feinongcount) {
        this.feinongcount = feinongcount;
    }

    public Integer getHucount() {
        return hucount==null?0:hucount;
    }

    public void setHucount(Integer hucount) {
        this.hucount = hucount;
    }

    public Float getLdArea() {
        return ldArea;
    }

    public void setLdArea(Float ldArea) {
        this.ldArea = ldArea;
    }

    public Float getLdRk() {
        return ldRk;
    }

    public void setLdRk(Float ldRk) {
        this.ldRk = ldRk;
    }

    public Float getLdQdfs() {
        return ldQdfs;
    }

    public void setLdQdfs(Float ldQdfs) {
        this.ldQdfs = ldQdfs;
    }

    public Float getLdRjl() {
        return ldRjl;
    }

    public void setLdRjl(Float ldRjl) {
        this.ldRjl = ldRjl;
    }

    public Float getLdDjgx() {
        return ldDjgx;
    }

    public void setLdDjgx(Float ldDjgx) {
        this.ldDjgx = ldDjgx;
    }

    public String getHylx() {
        return hylx==null?"":hylx;
    }

    public void setHylx(String hylx) {
        this.hylx = hylx;
    }

    public Integer getApptype() {
        return apptype==null?0:apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public Integer getSqtype() {
        return sqtype==null?0:sqtype;
    }

    public void setSqtype(Integer sqtype) {
        this.sqtype = sqtype;
    }

    public Integer getLztype() {
        return lztype==null?0:lztype;
    }

    public void setLztype(Integer lztype) {
        this.lztype = lztype;
    }

    public Integer getTctype() {
        return tctype==null?0:tctype;
    }

    public void setTctype(Integer tctype) {
        this.tctype = tctype;
    }

    public String getSituation() {
        return situation==null?"":situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Integer getYlzh() {
        return ylzh==null?0:ylzh;
    }

    public void setYlzh(Integer ylzh) {
        this.ylzh = ylzh;
    }

    public BigDecimal getJianzhuArea() {
        return jianzhuArea==null?new BigDecimal(0):jianzhuArea;
    }

    public void setJianzhuArea(BigDecimal jianzhuArea) {
        this.jianzhuArea = jianzhuArea;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public Integer getGid() {
        return gid==null?0:gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getObjectid() {
        return objectid==null?0:objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public Integer getDs() {
        return ds==null?0:ds;
    }

    public void setDs(Integer ds) {
        this.ds = ds;
    }

    public Integer getNcount() {
        return ncount==null?0:ncount;
    }

    public void setNcount(Integer ncount) {
        this.ncount = ncount;
    }

    public Integer getFeinong() {
        return feinong==null?0:feinong;
    }

    public void setFeinong(Integer feinong) {
        this.feinong = feinong;
    }

    public Integer getHs() {
        return hs==null?0:hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public Integer getRk() {
        return rk==null?0:rk;
    }

    public void setRk(Integer rk) {
        this.rk = rk;
    }

    public String getLandType() {
        return landType==null?"":landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getTsqk() {
        return tsqk==null?"":tsqk;
    }

    public void setTsqk(String tsqk) {
        this.tsqk = tsqk;
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

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWymc() {
        return wymc==null?"":wymc;
    }

    public void setWymc(String wymc) {
        this.wymc = wymc;
    }

    public String getMph() {
        return mph==null?"":mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getHzmc() {
        return hzmc==null?"":hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public String getFqd() {
        return fqd==null?"":fqd;
    }

    public void setFqd(String fqd) {
        this.fqd = fqd;
    }

    public String getPxjt() {
        return pxjt==null?"":pxjt;
    }

    public void setPxjt(String pxjt) {
        this.pxjt = pxjt;
    }

    public String getYhdz() {
        return yhdz==null?"":yhdz;
    }

    public void setYhdz(String yhdz) {
        this.yhdz = yhdz;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area.setScale(1, BigDecimal.ROUND_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getJtjjzzcy() {
        return jtjjzzcy==null?new BigDecimal(0):jtjjzzcy;
    }

    public void setJtjjzzcy(BigDecimal jtjjzzcy) {
        this.jtjjzzcy = jtjjzzcy;
    }

    public String getGeom() {
        return geom==null?"":geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getBzfts() {
        return bzfts==null?0:bzfts;
    }

    public void setBzfts(Integer bzfts) {
        this.bzfts = bzfts;
    }
}
