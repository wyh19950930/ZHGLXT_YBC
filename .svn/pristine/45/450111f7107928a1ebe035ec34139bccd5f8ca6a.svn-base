package com.jymj.zhglxt.zjd.bean.yzt.cs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ljj on 2017/4/28.
 */
public class MoveCost2 implements Comparable, Serializable {
    private Integer costId;
    //序号
    private String count;
    //项目
    private String project;
    //工程量
    private BigDecimal quantitie;
    //工程量单位
    private String quaunit;
    //计费单价
    private BigDecimal bilprice;
    //单位
    private String unit;
    //总计（万元）
    private BigDecimal sumcost;
    //备注
    private String text;
    //主键id
    private Integer keyId;
    //父id
    private Integer parentId;
    //1中心区棚改2村民自主楼3美丽乡村
    private Integer type;
    //镇code
    private String code;
    //排序用
    private Integer orderId;
    
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
    
    public String getQuantitie() {
        return quantitie==null? "" :quantitie.stripTrailingZeros().toPlainString();
    }
    
    public void setQuantitie(BigDecimal quantitie) {
        this.quantitie = quantitie;
    }
    
    public String getBilprice() {
        return bilprice==null? "" :bilprice.stripTrailingZeros().toPlainString();
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
    
    public String getSumcost() {
        return sumcost==null? "" :sumcost.stripTrailingZeros().toPlainString();
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
    
    public Integer getKeyId() {
        return keyId;
    }
    
    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }
    
    public String getQuaunit() {
        return quaunit;
    }
    
    public void setQuaunit(String quaunit) {
        this.quaunit = quaunit;
    }
    
    public String getProject() {
        return project;
    }
    
    public void setProject(String project) {
        this.project = project;
    }
    
    public Integer getParentId() {
        return parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
    
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public int compareTo(Object o) {
        MoveCost2 m = (MoveCost2) o;
        int c = m.getCostId();
        return this.costId.compareTo(c);
    }
    
}
