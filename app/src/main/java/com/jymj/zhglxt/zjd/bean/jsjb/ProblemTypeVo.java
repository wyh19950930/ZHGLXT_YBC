package com.jymj.zhglxt.zjd.bean.jsjb;

/**
 * Created by lc on 2022/6/30.问题类型统计
 */
public class ProblemTypeVo {
    private Integer problemType;//	问题分类
    private String problemTypeText;//	问题分类
    private Integer counts;//数量

    public Integer getProblemType() {
        return problemType==null?0:problemType;
    }

    public void setProblemType(Integer problemType) {
        this.problemType = problemType;
    }

    public String getProblemTypeText() {
        return problemTypeText==null?"":problemTypeText;
    }

    public void setProblemTypeText(String problemTypeText) {
        this.problemTypeText = problemTypeText;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
