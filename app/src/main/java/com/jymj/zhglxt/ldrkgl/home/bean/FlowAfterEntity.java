package com.jymj.zhglxt.ldrkgl.home.bean;

import java.io.Serializable;

/**
 * Created by ${lc} on 2022/2/15.
 */
public class FlowAfterEntity implements Serializable{
    private static final long serialVersionUID = -7222046645749881430L;

    private Long id;
    private Long flowId;// flow_info 表 id
    private String name;// 姓名
    private String idCard;// 身份证
    private Integer sex;// 性别
    private String province;// 省
    private String city;// 市
    private String county;// 县
    private String address;// 详细地址
    private Integer industry;// 行业
    private int age;// 年龄
    private String registerDate;// 登记日期
    private String createDate;// 创建时间
    private String updateDate;// 修改时间
    private Integer education;// 教育程度

    public Integer getEducation() {
        return education == null?11:education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard==null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSex() {
        return sex == null?1:sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province==null?"":province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city==null?"":city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county==null?"":county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address==null?"":address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIndustry() {
        return industry == null?0:industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegisterDate() {
        return registerDate==null?"2000-01-01":registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate==null?"":updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
