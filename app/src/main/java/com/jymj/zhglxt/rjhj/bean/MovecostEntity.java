package com.jymj.zhglxt.rjhj.bean;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2020/9/2.
 */
public class MovecostEntity {
    private Integer costId;
    private String count;//序号
    private String project;//项目
    private BigDecimal quantitie;//工程量
    private BigDecimal bilprice;//计费单价
    private String unit;//单位
    private BigDecimal sumcost;//总计（万元）
    private String text;//备注
    private Integer keyid;
    private String quaunit;
    private Integer type;//1--总测算 2--户测算
    private String code;
    private Integer parentid;
    private BigDecimal n1;	//第一年    百分比
    private BigDecimal n2;//第二年    百分比
    private BigDecimal n3;//第三年    百分比
    private BigDecimal n4;//第四年    百分比
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCount() {
        return count==null?"":count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getProject() {
        return project==null?"":project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public BigDecimal getQuantitie() {
        return quantitie == null?new BigDecimal(0):quantitie;
    }

    public void setQuantitie(BigDecimal quantitie) {
        this.quantitie = quantitie;
    }

    public BigDecimal getBilprice() {
        return bilprice==null?new BigDecimal(0):bilprice;
    }

    public void setBilprice(BigDecimal bilprice) {
        this.bilprice = bilprice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getSumcost() {
        return sumcost==null?new BigDecimal(0):sumcost;
    }

    public void setSumcost(BigDecimal sumcost) {
        this.sumcost = sumcost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public String getQuaunit() {
        return quaunit;
    }

    public void setQuaunit(String quaunit) {
        this.quaunit = quaunit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public BigDecimal getN1() {
        return n1 == null?new BigDecimal(0):n1;
    }

    public void setN1(BigDecimal n1) {
        this.n1 = n1;
    }

    public BigDecimal getN2() {
        return n2== null?new BigDecimal(0):n2;
    }

    public void setN2(BigDecimal n2) {
        this.n2 = n2;
    }

    public BigDecimal getN3() {
        return n3== null?new BigDecimal(0):n3;
    }

    public void setN3(BigDecimal n3) {
        this.n3 = n3;
    }

    public BigDecimal getN4() {
        return n4== null?new BigDecimal(0):n4;
    }

    public void setN4(BigDecimal n4) {
        this.n4 = n4;
    }
}
