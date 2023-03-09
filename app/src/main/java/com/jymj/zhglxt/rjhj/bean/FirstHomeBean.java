package com.jymj.zhglxt.rjhj.bean;

import java.util.ArrayList;

public class FirstHomeBean {
    HomeEntity homeEntity;
    ArrayList<HomeEntity> homeEntities;
    RjhjReport rjhjReport;


    public HomeEntity getHomeEntity() {
        return homeEntity;
    }

    public void setHomeEntity(HomeEntity homeEntity) {
        this.homeEntity = homeEntity;
    }

    public ArrayList<HomeEntity> getHomeEntities() {
        return homeEntities==null?new ArrayList<>():homeEntities;
    }

    public void setHomeEntities(ArrayList<HomeEntity> homeEntities) {
        this.homeEntities = homeEntities;
    }

    public RjhjReport getRjhjReport() {
        return rjhjReport;
    }

    public void setRjhjReport(RjhjReport rjhjReport) {
        this.rjhjReport = rjhjReport;
    }
}
