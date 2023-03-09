package com.jymj.zhglxt.rjhj.bean;

import java.util.ArrayList;
import java.util.List;

public class WtlrBean {//问题录入点查
    public Integer czfw;
    public List<PjEnviorSupvsEntity> pjEnviorSupvsEntities;
    public PointRecordEntity pointRecordEntity;
    public CjEntity cjEntity ;

    public CjEntity getCjEntity() {
        return cjEntity;
    }

    public void setCjEntity(CjEntity cjEntity) {
        this.cjEntity = cjEntity;
    }

    public Integer getCzfw() {
        return czfw==null?0:czfw;
    }

    public void setCzfw(Integer czfw) {
        this.czfw = czfw;
    }

    public List<PjEnviorSupvsEntity> getPjEnviorSupvsEntities() {
        return pjEnviorSupvsEntities==null?new ArrayList<>():pjEnviorSupvsEntities;
    }

    public void setPjEnviorSupvsEntities(List<PjEnviorSupvsEntity> pjEnviorSupvsEntities) {
        this.pjEnviorSupvsEntities = pjEnviorSupvsEntities;
    }

    public PointRecordEntity getPointRecordEntity() {
        return pointRecordEntity==null?new PointRecordEntity():pointRecordEntity;
    }

    public void setPointRecordEntity(PointRecordEntity pointRecordEntity) {
        this.pointRecordEntity = pointRecordEntity;
    }
}
