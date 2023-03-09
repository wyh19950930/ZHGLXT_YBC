package com.jymj.zhglxt.rjhj.bean.yl.tdly;

import java.util.List;

/**
 * @package com.jymj.basemessagesystem.model
 * @fileName TdlyMapEntity
 * @date 2019/7/217:29
 * @name qzw
 */
public class TdlyMapEntity {

   /* private BigDecimal gyArea;
    private BigDecimal jtArea;
    private List<TdlyEntity> tdlyEntities;

    public BigDecimal getGyArea() {
        return gyArea;
    }

    public void setGyArea(BigDecimal gyArea) {
        this.gyArea = gyArea;
    }

    public BigDecimal getJtArea() {
        return jtArea;
    }

    public void setJtArea(BigDecimal jtArea) {
        this.jtArea = jtArea;
    }

    public List<TdlyEntity> getTdlyEntities() {
        return tdlyEntities;
    }

    public void setTdlyEntities(List<TdlyEntity> tdlyEntities) {
        this.tdlyEntities = tdlyEntities;
    }*/


       //里放的国有数据
    private List<TdlyEntity> tdlyGyEntityList;
    //里放的集体数据
    private List<TdlyEntity> tdlyJtEntityList;

    public List<TdlyEntity> getTdlyGyEntityList() {
        return tdlyGyEntityList;
    }

    public void setTdlyGyEntityList(List<TdlyEntity> tdlyGyEntityList) {
        this.tdlyGyEntityList = tdlyGyEntityList;
    }

    public List<TdlyEntity> getTdlyJtEntityList() {
        return tdlyJtEntityList;
    }

    public void setTdlyJtEntityList(List<TdlyEntity> tdlyJtEntityList) {
        this.tdlyJtEntityList = tdlyJtEntityList;
    }
}
