package com.jymj.zhglxt.zjd.bean.yzt.polygon;

import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.util.List;

/**
 * Administrator on 2018/1/10 create this class
 */

public class PolygonDetailEntity {
    
    private int ylCount;
    private List<YLEntity> ylEntityList;
    
    public int getYlCount() {
        return ylCount;
    }
    
    public void setYlCount(int ylCount) {
        this.ylCount = ylCount;
    }
    
    public List<YLEntity> getYlEntityList() {
        return ylEntityList;
    }
    
    public void setYlEntityList(List<YLEntity> ylEntityList) {
        this.ylEntityList = ylEntityList;
    }
}
