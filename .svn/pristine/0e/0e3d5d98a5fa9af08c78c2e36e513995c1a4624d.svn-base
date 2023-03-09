package com.jymj.zhglxt.rjhj.bean;


import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity;
import com.jymj.zhglxt.rjhj.bean.yl.IllegalFileEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZjdLzEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZjdSqEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZjdTcEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljj on 2017/11/15.
 */
public class YLEntity implements Serializable {
    private String Bh;
    private Integer gid;
    //面积
    private BigDecimal area;
    //建筑面积
    private BigDecimal jianzhuArea;
    //name 放企业名称 给图层显示用
    private String name;
    //备注
    private String remark;
    //门牌号
    private String mph;
    //用地类型
    private int ylType;
    private String ylTypeText;
    //图层颜色显示123456
    private int legent;
    //是否闲置
    private int isidle;
    private String isidleText;
    //村名
    private String xzqmc;
    //院落id
    private Integer objectid;
    //批准面积
    private double pzmj;
    //批准部门
    private String pzbm;
    //批准文号
    private String pzwh;
    //批准时间
    private String pzsj;
    //批准用途
    private String pzyt;
    //区域地价
    private double qydj;
    //人口
    private int rk;
    //权属性质
    private int qsxz;
    private String qsxzText;
    //使用权类型
    private int syqlx;
    private String syqlxText;
    //土地来源
    private int tdly;
    private String tdlyText;
    //是否有超占  0完全  1部分  2无
    private int hasoccupy;
    private String hasoccupyText;
    //产权来源
    private int cqly;
    private String cqlyText;
    private String code;
    //产权人
    private Integer zhaiid;
    private int zzcy;//集体组织成员
    private String zhaiName;
    //户主名称
    private String hzmc;
    //yl是否添加mph
    private int yllegent;
    //中心点
    private String center;
    //特殊情况
    private String tsqk;
    //登录人
    private int dlr;
    private String dlrname;
    //院落几何形状 by DL 2018年1月23日
    private String geometry;
    private int nongcount;
    private int feinongcount;
    private int hucount;
    //雷达图  用地面积
    private Float ldArea = 0f;
    //雷达图   人口数
    private Float ldRk = 0f;
    //雷达图   取得方式
    private Float ldQdfs = 0f;
    //雷达图   容积率
    private Float ldRjl = 0f;
    //雷达图   低价关系
    private Float ldDjgx = 0f;

    private String situation;//	varchar	255	0	-1		违法描述	pg_catalog	default	0	0
    private int apptype;//翻建状态
    private int sqtype;//申请状态
    private int lztype;//流转状态
    private int tctype;//退出状态
    private List<YLEntity> yhdList;//一户多宅
    private String secend;//与bh对应
    private List<Integer> qsxzList;
    private List<Integer> syqlxList;
    private List<Integer> cqlyList;
    private ApplyEntity applyEntity;
    private ZjdSqEntity zjdSqEntity;
    private ZjdLzEntity zjdLzEntity;
    private ZjdTcEntity zjdTcEntity;
    private List<IllegalFileEntity> illegalFileEntityList;
    private int syqk;//使用情况  1经营  2闲置  5自住   6出租
    private int jcnd;//建成年代  1 1983年前  2 1983年后
    private int jzjg;//建筑结构 1 简易房 2砖木结构 3钢筋混泥土结构 4钢结构
    private int jzcs;//建筑层数 1 2层以下   2 3层    3 3层以上
    private BigDecimal czmj;//出租面积
    private BigDecimal czjg;//出租价格
    private String yllegentText;
    private String[] tsqkText;
    private String syqkText;
    private String jcndText;
    private String jzjgText;
    private String jzcsText;
    private String zhen;

    private String landType;
    private String townname;
    private String fqd;
    private float shapeArea;
    private int ds;
    private Boolean fj;
    private String qszsh;//权属证书号
    private Integer czqkint;//czqkint   院落加的int类型的宅基地处置情况
    private String czqk;//宅基地处置情况
    private List<ApplyEntity> applyEntities;
    private List<ZhaiEntity> zhaiEntities;

    private String fl2;
    private Integer ldrks;//流动人口数
    private String qs;//权属
    private String bz;//
    private String yt;//用途
    private String ctps;//进度
    private String ct;//名称
    private List<String> files;

    public List<String> getFiles() {
        return files==null?new ArrayList<>():files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getQs() {
        return qs==null?"":qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public Integer getLdrks() {
        return ldrks==null?0:ldrks;
    }

    public void setLdrks(Integer ldrks) {
        this.ldrks = ldrks;
    }

    public String getFl2() {
        return fl2==null?"":fl2;
    }

    public void setFl2(String fl2) {
        this.fl2 = fl2;
    }

    public Integer getCzqkint() {
        return czqkint==null?1:czqkint;
    }

    public void setCzqkint(Integer czqkint) {
        this.czqkint = czqkint;
    }

    public String getLandType() {
        return landType==null?"":landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getTownname() {
        return townname==null?"":townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public String getFqd() {
        return fqd==null?"":fqd;
    }

    public void setFqd(String fqd) {
        this.fqd = fqd;
    }

    public float getShapeArea() {
        return shapeArea;
    }

    public void setShapeArea(float shapeArea) {
        this.shapeArea = shapeArea;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }

    public Boolean getFj() {
        return fj;
    }

    public void setFj(Boolean fj) {
        this.fj = fj;
    }

    public String getQszsh() {
        return qszsh==null?"":qszsh;
    }

    public void setQszsh(String qszsh) {
        this.qszsh = qszsh;
    }

    public String getCzqk() {
        return czqk==null?"":czqk;
    }

    public void setCzqk(String czqk) {
        this.czqk = czqk;
    }

    public List<ApplyEntity> getApplyEntities() {
        return applyEntities;
    }

    public void setApplyEntities(List<ApplyEntity> applyEntities) {
        this.applyEntities = applyEntities;
    }

    public List<ZhaiEntity> getZhaiEntities() {
        return zhaiEntities;
    }

    public void setZhaiEntities(List<ZhaiEntity> zhaiEntities) {
        this.zhaiEntities = zhaiEntities;
    }

    public ZjdBean getZjdqk() {
        ZjdBean zjdBean = new ZjdBean();
        if (getApptype() != 0) {
            zjdBean.zjdqk="翻建";
            zjdBean.sqsj=getApplyEntity().getSqDate();
            zjdBean.spzt=getApplyEntity().getSptypeText();
            zjdBean.sqr=getApplyEntity().getName();
            zjdBean.iphone=getApplyEntity().getIphone();
            return zjdBean;
        } else if (getSqtype() != 0) {
            zjdBean.zjdqk="申请";
            zjdBean.sqsj=getZjdSqEntity().getSqDate();
            zjdBean.spzt=getZjdSqEntity().getSptypeText();
            zjdBean.sqr=getZjdSqEntity().getSqName();
            zjdBean.iphone=getZjdSqEntity().getIphone();
            return zjdBean;
        } else if (getLztype() != 0) {
            zjdBean.zjdqk="流转";
            zjdBean.sqsj=getZjdLzEntity().getSqDate();
            zjdBean.spzt=getZjdLzEntity().getSptypeText();
            zjdBean.sqr=getZjdLzEntity().getSqName();
            zjdBean.iphone=getZjdLzEntity().getIphone();
            return zjdBean;
        } else {// if (tctype!=0)
            zjdBean.zjdqk="退出";
            zjdBean.sqsj=getZjdTcEntity().getSqDate();
            zjdBean.spzt=getZjdTcEntity().getSptypeText();
            zjdBean.sqr=getZjdTcEntity().getSqName();
            zjdBean.iphone=getZjdTcEntity().getIphone();
            return zjdBean;
        }


    }
    public class ZjdBean{
        String zjdqk;
        String sqsj;
        String spzt;
        String sqr;
        String iphone;

        public String getZjdqk() {
            return zjdqk==null?"":zjdqk;
        }

        public void setZjdqk(String zjdqk) {
            this.zjdqk = zjdqk;
        }

        public String getSqsj() {
            return sqsj==null?"":sqsj;
        }

        public void setSqsj(String sqsj) {
            this.sqsj = sqsj;
        }

        public String getSpzt() {
            return spzt==null?"":spzt;
        }

        public void setSpzt(String spzt) {
            this.spzt = spzt;
        }

        public String getSqr() {
            return sqr==null?"":sqr;
        }

        public void setSqr(String sqr) {
            this.sqr = sqr;
        }

        public String getIphone() {
            return iphone==null?"":iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public int getZzcy() {
        return zzcy;
    }

    public void setZzcy(int zzcy) {
        this.zzcy = zzcy;
    }

    public void setGid(Integer gid) {//
        this.gid = gid;
    }

    public String getYlTypeText() {
        return ylTypeText==null?"":ylTypeText;
    }

    public String getIsidleText() {
        return isidleText==null?"":isidleText;
    }

    public String getQsxzText() {
        return qsxzText==null?"":qsxzText;
    }

    public String getSyqlxText() {
        return syqlxText==null?"":syqlxText;
    }

    public String getTdlyText() {
        return tdlyText==null?"":tdlyText;
    }

    public String getHasoccupyText() {
        return hasoccupyText==null?"":hasoccupyText;
    }

    public int getSyqk() {
        return syqk;
    }

    public void setSyqk(int syqk) {
        this.syqk = syqk;
    }

    public int getJcnd() {
        return jcnd;
    }

    public void setJcnd(int jcnd) {
        this.jcnd = jcnd;
    }

    public int getJzjg() {
        return jzjg;
    }

    public void setJzjg(int jzjg) {
        this.jzjg = jzjg;
    }

    public int getJzcs() {
        return jzcs;
    }

    public void setJzcs(int jzcs) {
        this.jzcs = jzcs;
    }

    public BigDecimal getCzmj() {
        return czmj==null?new BigDecimal(0):czmj;
    }

    public void setCzmj(BigDecimal czmj) {
        this.czmj = czmj;
    }

    public BigDecimal getCzjg() {
        return czjg==null?new BigDecimal(0):czjg;
    }

    public void setCzjg(BigDecimal czjg) {
        this.czjg = czjg;
    }

    public String getYllegentText() {
        return yllegentText;
    }

    public void setYllegentText(String yllegentText) {
        this.yllegentText = yllegentText;
    }

    public String getTsqkText() {
        String tsqk1="";
        if (tsqkText==null){
            return tsqk1;
        }else {
            for (int i = 0; i < tsqkText.length; i++) {
                if (i==tsqkText.length-1)
                tsqk1=tsqk1+tsqkText[i];
                else {
                    tsqk1=tsqk1+tsqkText[i]+"、";
                }
            }
            return tsqk1;
        }

//        return tsqkText==null?new String[]{}:tsqkText;
    }

    public void setTsqkText(String[] tsqkText) {
        this.tsqkText = tsqkText;
    }

    public String getSyqkText() {
        return syqkText==null?"":syqkText;
    }

    public void setSyqkText(String syqkText) {
        this.syqkText = syqkText;
    }

    public String getJcndText() {
        return jcndText==null?"":jcndText;
    }

    public void setJcndText(String jcndText) {
        this.jcndText = jcndText;
    }

    public String getJzjgText() {
        return jzjgText==null?"":jzjgText;
    }

    public void setJzjgText(String jzjgText) {
        this.jzjgText = jzjgText;
    }

    public String getJzcsText() {
        return jzcsText==null?"":jzcsText;
    }

    public void setJzcsText(String jzcsText) {
        this.jzcsText = jzcsText;
    }

    public int getApptype() {
        return apptype;
    }

    public void setApptype(int apptype) {
        this.apptype = apptype;
    }

    public int getSqtype() {
        return sqtype;
    }

    public void setSqtype(int sqtype) {
        this.sqtype = sqtype;
    }

    public int getLztype() {
        return lztype;
    }

    public void setLztype(int lztype) {
        this.lztype = lztype;
    }

    public int getTctype() {
        return tctype;
    }

    public void setTctype(int tctype) {
        this.tctype = tctype;
    }

    public List<YLEntity> getYhdList() {
        return yhdList==null?new ArrayList<>():yhdList;
    }

    public void setYhdList(List<YLEntity> yhdList) {
        this.yhdList = yhdList;
    }

    public String getSecend() {
        return secend==null?"":secend;
    }

    public void setSecend(String secend) {
        this.secend = secend;
    }

    public List<Integer> getQsxzList() {
        return qsxzList==null?new ArrayList<>():qsxzList;
    }

    public void setQsxzList(List<Integer> qsxzList) {
        this.qsxzList = qsxzList;
    }

    public List<Integer> getSyqlxList() {
        return syqlxList==null?new ArrayList<>():syqlxList;
    }

    public void setSyqlxList(List<Integer> syqlxList) {
        this.syqlxList = syqlxList;
    }

    public List<Integer> getCqlyList() {
        return cqlyList==null?new ArrayList<>():cqlyList;
    }

    public void setCqlyList(List<Integer> cqlyList) {
        this.cqlyList = cqlyList;
    }

    public ApplyEntity getApplyEntity() {
        return applyEntity==null?new ApplyEntity():applyEntity;
    }

    public void setApplyEntity(ApplyEntity applyEntity) {
        this.applyEntity = applyEntity;
    }

    public ZjdSqEntity getZjdSqEntity() {
        return zjdSqEntity==null?new ZjdSqEntity():zjdSqEntity;
    }

    public void setZjdSqEntity(ZjdSqEntity zjdSqEntity) {
        this.zjdSqEntity = zjdSqEntity;
    }

    public ZjdLzEntity getZjdLzEntity() {
        return zjdLzEntity==null?new ZjdLzEntity():zjdLzEntity;
    }

    public void setZjdLzEntity(ZjdLzEntity zjdLzEntity) {
        this.zjdLzEntity = zjdLzEntity;
    }

    public ZjdTcEntity getZjdTcEntity() {
        return zjdTcEntity==null?new ZjdTcEntity():zjdTcEntity;
    }

    public void setZjdTcEntity(ZjdTcEntity zjdTcEntity) {
        this.zjdTcEntity = zjdTcEntity;
    }

    public String getBh() {
        return Bh==null?"":Bh;
    }

    public void setBh(String bh) {
        Bh = bh;
    }

    public Float getLdArea() {
        return ldArea == null ? 0 : ldArea * 100;
    }

    public void setLdArea(Float ldArea) {
        this.ldArea = ldArea;
    }

    public Float getLdRk() {
        return ldRk == null ? 0 : ldRk * 100;
    }

    public void setLdRk(Float ldRk) {
        this.ldRk = ldRk;
    }

    public Float getLdQdfs() {
        return ldQdfs == null ? 0 : ldQdfs * 100;
    }

    public void setLdQdfs(Float ldQdfs) {
        this.ldQdfs = ldQdfs;
    }

    public Float getLdRjl() {
        return ldRjl == null ? 0 : ldRjl * 100;
    }

    public void setLdRjl(Float ldRjl) {
        this.ldRjl = ldRjl;
    }

    public Float getLdDjgx() {
        return ldDjgx == null ? 0 : ldDjgx * 100;
    }

    public void setLdDjgx(Float ldDjgx) {
        this.ldDjgx = ldDjgx;
    }

    public Integer getZhaiid() {
        return zhaiid==null?0:zhaiid;
    }


    public String getZhaiName() {
        if (zhaiName == null) {
            return "";
        }
        return zhaiName;
    }

    public void setZhaiName(String zhaiName) {
        this.zhaiName = zhaiName;
    }
    public String getCenter1(){
        return center==null?"":center;
    }
    public LatLng getCenter() {

        if (center != null) {
            String[] points = center.substring(6, center.length() - 1).split(" ");
            if (points != null && points.length > 1) {
                CoordinateConverter converter = new CoordinateConverter();
                // CoordType.GPS 待转换坐标类型
                converter.from(CoordinateConverter.CoordType.GPS);
                LatLng sl = new LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]));
                // sourceLatLng待转换坐标点 LatLng类型
                converter.coord(sl);
                LatLng latLng = converter.convert();
                return sl;
            } else {
                return new LatLng(0, 0);
            }
        }

        return new LatLng(0, 0);
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getGid() {
        return gid==null?0:gid;
    }


    public BigDecimal getArea() {
        return area==null?BigDecimal.ZERO:area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getJianzhuArea() {
        return jianzhuArea==null?BigDecimal.ZERO:jianzhuArea;
    }

    public void setJianzhuArea(BigDecimal jianzhuArea) {
        this.jianzhuArea = jianzhuArea;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        if (remark == null) {
            return "";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMph() {
        if (mph == null) {
            return "";
        }
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public int getYlType() {
        return ylType;
    }

    public int getLegent() {
        return legent;
    }

    public int getIsidle() {
        return isidle;
    }

    public String getXzqmc() {
        if (xzqmc == null) {
            return "";
        }
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer getObjectid() {
        return objectid==null?0:objectid;
    }

    public double getPzmj() {
        return pzmj;
    }

    public void setPzmj(double pzmj) {
        this.pzmj = pzmj;
    }

    public String getPzbm() {
        return pzbm == null ? "" : pzbm;
    }

    public void setPzbm(String pzbm) {
        this.pzbm = pzbm;
    }

    public String getPzwh() {
        return pzwh == null ? "" : pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getPzsj() {
        return pzsj == null ? "" : pzsj;
    }

    public void setPzsj(String pzsj) {
        this.pzsj = pzsj;
    }

    public String getPzyt() {
        return pzyt == null ? "" : pzyt;
    }

    public void setPzyt(String pzyt) {
        this.pzyt = pzyt;
    }

    public double getQydj() {
        return qydj;
    }

    public void setQydj(double qydj) {
        this.qydj = qydj;
    }

    public int getRk() {
        return rk;
    }

    public int getQsxz() {
        return qsxz;
    }

    public int getSyqly() {
        return syqlx;
    }

    public void setSyqly(int syqlx) {
        this.syqlx = syqlx;
    }

    public int getTdly() {
        return tdly;
    }


    public int getHasoccupy() {
        return hasoccupy;
    }

    public int getCqly() {
        return cqly;
    }


    public void setYlType(int ylType) {
        this.ylType = ylType;
    }

    public void setYlTypeText(String ylTypeText) {
        this.ylTypeText = ylTypeText;
    }

    public void setLegent(int legent) {
        this.legent = legent;
    }

    public void setIsidle(int isidle) {
        this.isidle = isidle;
    }

    public void setIsidleText(String isidleText) {
        this.isidleText = isidleText;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public void setRk(int rk) {
        this.rk = rk;
    }

    public void setQsxz(int qsxz) {
        this.qsxz = qsxz;
    }

    public void setQsxzText(String qsxzText) {
        this.qsxzText = qsxzText;
    }

    public int getSyqlx() {
        return syqlx;
    }

    public void setSyqlx(int syqlx) {
        this.syqlx = syqlx;
    }

    public void setSyqlxText(String syqlxText) {
        this.syqlxText = syqlxText;
    }

    public void setTdly(int tdly) {
        this.tdly = tdly;
    }

    public void setTdlyText(String tdlyText) {
        this.tdlyText = tdlyText;
    }


    public void setHasoccupyText(String hasoccupyText) {
        this.hasoccupyText = hasoccupyText;
    }

    public void setCqly(int cqly) {
        this.cqly = cqly;
    }

    public String getCqlyText() {
        return cqlyText==null?"":cqlyText;
    }

    public void setCqlyText(String cqlyText) {
        this.cqlyText = cqlyText;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setZhaiid(Integer zhaiid) {
        this.zhaiid = zhaiid;
    }

    public String getHzmc() {
        return hzmc == null ? "" : hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public int getYllegent() {
        return yllegent;
    }

    public void setYllegent(int yllegent) {
        this.yllegent = yllegent;
    }

    public String getTsqk() {
        return tsqk==null?"":tsqk;
    }

    public void setTsqk(String tsqk) {
        this.tsqk = tsqk;
    }

    public int getDlr() {
        return dlr;
    }

    public void setDlr(int dlr) {
        this.dlr = dlr;
    }

    public String getDlrname() {
        return dlrname==null?"":dlrname;
    }

    public void setDlrname(String dlrname) {
        this.dlrname = dlrname;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }


    public void setHasoccupy(int hasoccupy) {
        this.hasoccupy = hasoccupy;
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

    public int getHucount() {
        return hucount;
    }

    public void setHucount(int hucount) {
        this.hucount = hucount;
    }

    public List<IllegalFileEntity> getIllegalFileEntityList() {
        return illegalFileEntityList==null?new ArrayList<>():illegalFileEntityList;
    }

    public void setIllegalFileEntityList(List<IllegalFileEntity> illegalFileEntityList) {
        this.illegalFileEntityList = illegalFileEntityList;
    }

    public String getSituation() {
        return situation == null ? "" : situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getBz() {
        return bz==null?"":bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getYt() {
        return yt==null?"":yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getCtps() {
        return ctps==null?"":ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getCt() {
        return ct==null?"":ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
