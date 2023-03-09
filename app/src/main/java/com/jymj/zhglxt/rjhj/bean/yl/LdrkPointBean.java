package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.zjd.bean.YlEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LdrkPointBean implements Serializable {
    private YlEntity ylEntity;
    private List<FloatPopulat> floatPopulats;

    public YlEntity getYlEntity() {
        return ylEntity==null?new YlEntity():ylEntity;
    }

    public void setYlEntity(YlEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public List<FloatPopulat> getFloatPopulats() {
        return floatPopulats==null?new ArrayList<>():floatPopulats;
    }

    public void setFloatPopulats(List<FloatPopulat> floatPopulats) {
        this.floatPopulats = floatPopulats;
    }
}
