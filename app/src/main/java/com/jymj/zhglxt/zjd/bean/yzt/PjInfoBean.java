package com.jymj.zhglxt.zjd.bean.yzt;

import com.jymj.zhglxt.rjhj.bean.PjProjEntity;

import java.util.ArrayList;
import java.util.List;

public class PjInfoBean {

    private List<PjProjFileEnty> pjProjFileEnties;
    private PjProjEntity pjProjEntity;
    private List<CtQyEntity> ctQyEntities;

    public List<PjProjFileEnty> getPjProjFileEnties() {
        return pjProjFileEnties==null?new ArrayList<>():pjProjFileEnties;
    }

    public void setPjProjFileEnties(List<PjProjFileEnty> pjProjFileEnties) {
        this.pjProjFileEnties = pjProjFileEnties;
    }

    public PjProjEntity getPjProjEntity() {
        return pjProjEntity;
    }

    public void setPjProjEntity(PjProjEntity pjProjEntity) {
        this.pjProjEntity = pjProjEntity;
    }

    public List<CtQyEntity> getCtQyEntities() {
        return ctQyEntities==null?new ArrayList<>():ctQyEntities;
    }

    public void setCtQyEntities(List<CtQyEntity> ctQyEntities) {
        this.ctQyEntities = ctQyEntities;
    }
}
