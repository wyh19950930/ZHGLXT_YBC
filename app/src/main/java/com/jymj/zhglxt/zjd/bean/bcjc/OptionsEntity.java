package com.jymj.zhglxt.zjd.bean.bcjc;

import com.jymj.zhglxt.R;

import java.math.BigDecimal;

/**
 * Created by lc on 2022/8/19.
 */
public class OptionsEntity {
    private Long id;
    private String options;//选项
    private Integer type;//类型
    private Integer sorting;//排序
    private String remark;
    private BigDecimal area;

    private boolean isCheck;

    public String getRemark() {
        return remark==null?"": remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getArea() {
        return area==null?BigDecimal.ZERO:area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptions() {
        return options==null?"":options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }
}
