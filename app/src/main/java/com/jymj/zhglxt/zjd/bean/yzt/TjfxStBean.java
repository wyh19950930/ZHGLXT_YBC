package com.jymj.zhglxt.zjd.bean.yzt;

import java.math.BigDecimal;

public class TjfxStBean {

    public String name;
    public int counts;//宗数
    public BigDecimal area;//占地面积
    public BigDecimal jzmj;//建筑面积

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public BigDecimal getArea() {
        return area==null?BigDecimal.ZERO:area.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getJzmj() {
        return jzmj==null?BigDecimal.ZERO:jzmj.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }
}
