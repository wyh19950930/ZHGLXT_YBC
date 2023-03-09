package com.jymj.zhglxt.zjd.bean.jsjb;

import java.util.ArrayList;
import java.util.List;

public class JsjbTj2Bean {
    //1总数 2未完成数 3完成数 4满意数
    public JsjbTjBean total;
    public List<ProblemTypeVo> problemType;

    public JsjbTjBean getTotal() {
        return total==null?new JsjbTjBean():total;
    }

    public void setTotal(JsjbTjBean total) {
        this.total = total;
    }

    public List<ProblemTypeVo> getProblemType() {
        return problemType==null?new ArrayList<>():problemType;
    }

    public void setProblemType(List<ProblemTypeVo> problemType) {
        this.problemType = problemType;
    }
}
