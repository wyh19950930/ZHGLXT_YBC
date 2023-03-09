package com.jymj.zhglxt.zjd.bean;

import java.math.BigDecimal;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/4/9 13:42
 */
public class BaseDataChartBean {
    private BigDecimal ob1;//道路及工服设施用地(公顷)
    private BigDecimal ob2;//宅院用地(公顷)
    private BigDecimal ob3;//平衡资金用地(公顷)
    private BigDecimal ob4;//剩余集体产业用地(公顷)
    private String name;//名称

    public BigDecimal getOb1() {
        return ob1;
    }

    public void setOb1(BigDecimal ob1) {
        this.ob1 = ob1;
    }

    public BigDecimal getOb2() {
        return ob2;
    }

    public void setOb2(BigDecimal ob2) {
        this.ob2 = ob2;
    }

    public BigDecimal getOb3() {
        return ob3;
    }

    public void setOb3(BigDecimal ob3) {
        this.ob3 = ob3;
    }

    public BigDecimal getOb4() {
        return ob4;
    }

    public void setOb4(BigDecimal ob4) {
        this.ob4 = ob4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseDataChartBean(BigDecimal ob1, BigDecimal ob2, BigDecimal ob3, BigDecimal ob4, String name) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.ob3 = ob3;
        this.ob4 = ob4;
        this.name = name;
    }
}
