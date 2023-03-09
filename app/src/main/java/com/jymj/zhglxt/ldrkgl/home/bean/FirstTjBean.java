package com.jymj.zhglxt.ldrkgl.home.bean;

import java.util.ArrayList;
import java.util.List;

public class FirstTjBean {//流动人口首页统计

    private List<IndustryEntity> industry;
    private XzqInfoEntity xzqInfo;

    public List<IndustryEntity> getIndustry() {
        return industry==null?new ArrayList<>():industry;
    }

    public void setIndustry(List<IndustryEntity> industry) {
        this.industry = industry;
    }

    public XzqInfoEntity getXzqInfo() {
        return xzqInfo==null?new XzqInfoEntity():xzqInfo;
    }

    public void setXzqInfo(XzqInfoEntity xzqInfo) {
        this.xzqInfo = xzqInfo;
    }
}
