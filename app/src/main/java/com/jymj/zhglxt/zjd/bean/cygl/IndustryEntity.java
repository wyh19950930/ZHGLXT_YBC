package com.jymj.zhglxt.zjd.bean.cygl;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/4/28 10:12
 */
public class IndustryEntity {
    private Integer industry;//行业
    private String industryText;
    private Integer count;//数量

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getIndustryText() {
        return industryText;
    }

    public void setIndustryText(String industryText) {
        this.industryText = industryText;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
