package com.jymj.zhglxt.xm.bean;

/**
 * Created by lc on 2022/12/13.
 */
public class ProjectCountVO {
    private Long id;
    private String location;
    private Integer xmlx;
    private Integer counts;
    private String xzqmc;
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getXmlx() {
        return xmlx;
    }

    public void setXmlx(Integer xmlx) {
        this.xmlx = xmlx;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
