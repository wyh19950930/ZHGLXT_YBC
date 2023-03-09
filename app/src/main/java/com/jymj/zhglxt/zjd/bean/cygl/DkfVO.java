package com.jymj.zhglxt.zjd.bean.cygl;


import java.math.BigDecimal;

public class DkfVO {

  private Integer gid;
  private Integer joinCount;
  private Integer targetFid;
  private String code;
  private Integer id;
  private String fl;
  private String xzqdm;
  private String xzqmc;
  private String xzq;
  private String zhen;
  private BigDecimal dcmj;
  private BigDecimal jsmj;
  private BigDecimal shapeLeng;
  private BigDecimal shapeArea;
  private String fqmc;
  private String ghqx;
  private BigDecimal ydmj;
  private String xmmc;
  private String rjl;
  private Double jzmj;
  private Double zdmj;
  private BigDecimal area;
  private String geom;
  private String geometry;
  private String ghmc;

  public String getGhmc() {
    return ghmc==null?"":ghmc;
  }

  public void setGhmc(String ghmc) {
    this.ghmc = ghmc;
  }

  public String getGeometry() {
    return geometry==null?"":geometry;
  }

  public void setGeometry(String geometry) {
    this.geometry = geometry;
  }

  public Integer getGid() {
    return gid;
  }

  public void setGid(Integer gid) {
    this.gid = gid;
  }

  public Integer getJoinCount() {
    return joinCount==null?0:joinCount;
  }

  public void setJoinCount(Integer joinCount) {
    this.joinCount = joinCount;
  }

  public Integer getTargetFid() {
    return targetFid==null?0:targetFid;
  }

  public void setTargetFid(Integer targetFid) {
    this.targetFid = targetFid;
  }

  public String getCode() {
    return code==null?"":code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFl() {
    return fl==null?"":fl;
  }

  public void setFl(String fl) {
    this.fl = fl;
  }

  public String getXzqdm() {
    return xzqdm==null?"":xzqdm;
  }

  public void setXzqdm(String xzqdm) {
    this.xzqdm = xzqdm;
  }

  public String getXzqmc() {
    return xzqmc==null?"":xzqmc;
  }

  public void setXzqmc(String xzqmc) {
    this.xzqmc = xzqmc;
  }

  public String getXzq() {
    return xzq==null?"":xzq;
  }

  public void setXzq(String xzq) {
    this.xzq = xzq;
  }

  public String getZhen() {
    return zhen==null?"":zhen;
  }

  public void setZhen(String zhen) {
    this.zhen = zhen;
  }

  public BigDecimal getDcmj() {
    return dcmj==null?BigDecimal.ZERO:dcmj;
  }

  public void setDcmj(BigDecimal dcmj) {
    this.dcmj = dcmj;
  }

  public BigDecimal getJsmj() {
    return jsmj==null?BigDecimal.ZERO:jsmj;
  }

  public void setJsmj(BigDecimal jsmj) {
    this.jsmj = jsmj;
  }

  public BigDecimal getShapeLeng() {
    return shapeLeng==null?BigDecimal.ZERO:shapeLeng;
  }

  public void setShapeLeng(BigDecimal shapeLeng) {
    this.shapeLeng = shapeLeng;
  }

  public BigDecimal getShapeArea() {
    return shapeArea==null?BigDecimal.ZERO:shapeArea;
  }

  public void setShapeArea(BigDecimal shapeArea) {
    this.shapeArea = shapeArea;
  }

  public String getFqmc() {
    return fqmc==null?"":fqmc;
  }

  public void setFqmc(String fqmc) {
    this.fqmc = fqmc;
  }

  public String getGhqx() {
    return ghqx==null?"":ghqx;
  }

  public void setGhqx(String ghqx) {
    this.ghqx = ghqx;
  }

  public BigDecimal getYdmj() {
    return ydmj==null?BigDecimal.ZERO:ydmj;
  }

  public void setYdmj(BigDecimal ydmj) {
    this.ydmj = ydmj;
  }

  public String getXmmc() {
    return xmmc==null?"":xmmc;
  }

  public void setXmmc(String xmmc) {
    this.xmmc = xmmc;
  }

  public String getRjl() {
    return rjl==null?"":rjl;
  }

  public void setRjl(String rjl) {
    this.rjl = rjl;
  }

  public Double getJzmj() {
    return jzmj==null?0:jzmj;
  }

  public void setJzmj(Double jzmj) {
    this.jzmj = jzmj;
  }

  public Double getZdmj() {
    return zdmj==null?0:zdmj;
  }

  public BigDecimal getArea() {
    return area==null?BigDecimal.ZERO:area;
  }

  public void setArea(BigDecimal area) {
    this.area = area;
  }

  public void setZdmj(Double zdmj) {
    this.zdmj = zdmj;
  }

  public String getGeom() {
    return geom==null?"":geom;
  }

  public void setGeom(String geom) {
    this.geom = geom;
  }
}
