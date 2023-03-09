package com.jymj.zhglxt.zjd.bean.yzt.nyd;


import com.jymj.zhglxt.rjhj.bean.yl.tdly.TDLYEntity1;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ${lc} on 2020/4/9. 长子营农用地
 */
public class ZzyNydEntity {
    private Integer gid;
    private String xzqmc;
    private String bh;//合同编号
    private String czr;//承租人
    private BigDecimal area;//面积
    private String remark;	//备注
    private String fl;//分类
    private String code;
    private String geom;
    private String geometry;
    private String townname;
    private String center;
    private ZzynydHtEntity zzynydHtEntity;//合同
    private List<TDLYEntity1> tdlyEntities;//土现
    private List<ZzynydZjEntity> zzynydZjEntities;//租金

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public List<TDLYEntity1> getTdlyEntities() {
        return tdlyEntities;
    }

    public void setTdlyEntities(List<TDLYEntity1> tdlyEntities) {
        this.tdlyEntities = tdlyEntities;
    }

    public List<ZzynydZjEntity> getZzynydZjEntities() {
        return zzynydZjEntities;
    }

    public void setZzynydZjEntities(List<ZzynydZjEntity> zzynydZjEntities) {
        this.zzynydZjEntities = zzynydZjEntities;
    }

    public ZzynydHtEntity getZzynydHtEntity() {
        return zzynydHtEntity==null?new ZzynydHtEntity():zzynydHtEntity;
    }

    public void setZzynydHtEntity(ZzynydHtEntity zzynydHtEntity) {
        this.zzynydHtEntity = zzynydHtEntity;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getCzr() {
        return czr==null?"":czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public BigDecimal getArea() {
        return area == null ? new BigDecimal(0) : area.setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFl() {
        return fl==null?"":fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGeom() {
        return geom==null?"":geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getTownname() {
        return townname==null?"":townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public ZzyNydEntity(String xzqmc, String czr, BigDecimal area) {
        this.xzqmc = xzqmc;
        this.czr = czr;
        this.area = area;
    }
}
