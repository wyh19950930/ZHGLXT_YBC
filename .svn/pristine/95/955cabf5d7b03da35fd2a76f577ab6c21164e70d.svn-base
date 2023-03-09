package com.jymj.zhglxt.zjd.bean.cygl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2021/11/2.
 */
public class EnterpriseBasisEntity implements Serializable{

    private static final long serialVersionUID = 3746456744662873925L;

    private Long gid;
//    private BigDecimal	area;//面积
    private BigDecimal	zdmj;//面积
    private String	code;
    private String	xzqmc;//行政区名称
    private String	townname;//镇名
    private String	bh;//编号
    private String	remark;//备注
//    private BigDecimal	jianzhuArea;//建筑面积
    private BigDecimal	jzmj;//建筑面积
    private String	qs;//权属
    private String	qsdw;//权属单位
    private String	dkbh;//地块编号
//    private String	qymc;//企业名称
    private String	name;//企业名称
    private String	fwcqr;//房屋产权人
    private double	xmin1;
    private double ymax;
    private String geometry;
    private String	center;//中心点
    private String	mobile;//手机号
    private Integer type;//0 现在数据   1 历史数据
    private String createDate;//Date 创建时间
    private String zcdz;//注册地址
    private String zcdate;//Date 注册时间
    private Integer qylx;
    private String qylxText;//企业类型
    private Integer oldid;
    private String tdsyr;
    private String fwcqdw;
    /*private EnterpriseEntity enterpriseEntity;
    private List<EnterpriseEntity> enterpriseEntities;
    private List<EnterpriseInfoEntity> enterpriseInfoEntities;
    private EnterpriseBuilding enterpriseBuilding;*/

//    private String garden;//工业园区
    private String yuanqu;//工业园区
    private List<Long> infoIds;
    private EnterpriseEntity enterpriseEntity;//企业信息表
    private List<EnterpriseEntity> enterpriseEntities;//承租单位信息表
//    private EnterpriseInfoEntity enterpriseInfoEntitie;//企业用地信息表
    private ArrayList<EnterpriseInfoEntity> enterpriseInfoEntities;//企业用地信息表
    private EnterpriseBuilding enterpriseBuilding;//企业建筑表
    private List<EnterpriseFileEntity> enterpriseFileEntities;//企业文件表

    public List<Long> getInfoIds() {
        if (infoIds == null){
            infoIds = new ArrayList<>();
        }
        return infoIds;
    }

    public void setInfoIds(List<Long> infoIds) {
        this.infoIds = infoIds;
    }

    public String getTdsyr() {
        return tdsyr==null?"":tdsyr;
    }

    public void setTdsyr(String tdsyr) {
        this.tdsyr = tdsyr;
    }

    public String getFwcqdw() {
        return fwcqdw==null?"":fwcqdw;
    }

    public void setFwcqdw(String fwcqdw) {
        this.fwcqdw = fwcqdw;
    }

    public String getYuanqu() {
        return yuanqu==null?"":yuanqu;
    }

    public void setYuanqu(String yuanqu) {
        this.yuanqu = yuanqu;
    }

    public ArrayList<EnterpriseInfoEntity> getEnterpriseInfoEntities() {
        if (enterpriseInfoEntities==null){
            enterpriseInfoEntities = new ArrayList<>();
        }
        return enterpriseInfoEntities;
    }

    public void setEnterpriseInfoEntities(ArrayList<EnterpriseInfoEntity> enterpriseInfoEntities) {
        this.enterpriseInfoEntities = enterpriseInfoEntities;
    }

    public EnterpriseInfoEntity getEnterpriseInfoEntitie() {//用地信息改成列表
        /*if (enterpriseInfoEntitie==null){
            enterpriseInfoEntitie = new EnterpriseInfoEntity();
        }
        return enterpriseInfoEntitie;*/
        return new EnterpriseInfoEntity();
    }

    public void setEnterpriseInfoEntitie(EnterpriseInfoEntity enterpriseInfoEntitie) {
//        this.enterpriseInfoEntitie = enterpriseInfoEntitie;
    }

    public List<EnterpriseFileEntity> getEnterpriseFileEntities() {
        if (enterpriseFileEntities==null){
            enterpriseFileEntities = new ArrayList<EnterpriseFileEntity>();
        }
        return enterpriseFileEntities;
    }

    public void setEnterpriseFileEntities(List<EnterpriseFileEntity> enterpriseFileEntities) {
        this.enterpriseFileEntities = enterpriseFileEntities;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public BigDecimal getZdmj() {
        return zdmj==null?BigDecimal.ZERO:zdmj.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setZdmj(BigDecimal zdmj) {
        this.zdmj = zdmj;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getTownname() {
        return townname==null?"":townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
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

    public BigDecimal getJzmj() {
        return jzmj==null?BigDecimal.ZERO:jzmj.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    public String getQs() {
        return qs==null?"":qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getQsdw() {
        return qsdw==null?"":qsdw;
    }

    public void setQsdw(String qsdw) {
        this.qsdw = qsdw;
    }

    public String getDkbh() {
        return dkbh==null?"":dkbh;
    }

    public void setDkbh(String dkbh) {
        this.dkbh = dkbh;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFwcqr() {
        return fwcqr==null?"":fwcqr;
    }

    public void setFwcqr(String fwcqr) {
        this.fwcqr = fwcqr;
    }

    public double getXmin1() {
        return xmin1;
    }

    public void setXmin1(double xmin1) {
        this.xmin1 = xmin1;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
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

    public String getMobile() {
        return mobile==null?"":mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getZcdz() {
        return zcdz==null?"":zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz;
    }

    public String getZcdate() {
        return zcdate==null?"":zcdate;
    }

    public void setZcdate(String zcdate) {
        this.zcdate = zcdate;
    }

    public Integer getQylx() {
        return qylx==null?0:qylx;
    }

    public void setQylx(Integer qylx) {
        this.qylx = qylx;
    }

    public String getQylxText() {
        return EnterpriseTypeEnum.getName(getQylx());//qylxText==null?"":qylxText
    }

    public void setQylxText(String qylxText) {
        this.qylxText = qylxText;
    }

    public Integer getOldid() {
        return oldid;
    }

    public void setOldid(Integer oldid) {
        this.oldid = oldid;
    }

    public EnterpriseEntity getEnterpriseEntity() {
        if (enterpriseEntity==null){
            enterpriseEntity = new EnterpriseEntity();
        }
        return enterpriseEntity;
    }

    public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
        this.enterpriseEntity = enterpriseEntity;
    }

    public List<EnterpriseEntity> getEnterpriseEntities() {
        if (enterpriseEntities == null){
            enterpriseEntities = new ArrayList<>();
        }
        return enterpriseEntities;
    }

    public void setEnterpriseEntities(List<EnterpriseEntity> enterpriseEntities) {
        this.enterpriseEntities = enterpriseEntities;
    }

    /*public List<EnterpriseInfoEntity> getEnterpriseInfoEntities() {
        if (enterpriseInfoEntities==null){
            enterpriseInfoEntities = new ArrayList<>();
        }
        return enterpriseInfoEntities;
    }

    public void setEnterpriseInfoEntities(List<EnterpriseInfoEntity> enterpriseInfoEntities) {
        this.enterpriseInfoEntities = enterpriseInfoEntities;
    }*/

    public EnterpriseBuilding getEnterpriseBuilding() {
        if (enterpriseBuilding==null){
            enterpriseBuilding = new EnterpriseBuilding();
        }
        return enterpriseBuilding;
    }

    public void setEnterpriseBuilding(EnterpriseBuilding enterpriseBuilding) {
        this.enterpriseBuilding = enterpriseBuilding;
    }
}
