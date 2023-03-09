package com.jymj.zhglxt.zjd.bean.bcjc;


import java.util.Date;

/**
 * Created by lc on 2022/8/23.
 */
public class BcrejectedEntity {
    private Long id;
    private Long bcid;
    private String xzqmc;
    private String code;
    private String content;
    private Integer process;
    private String createTime;
    private String updateTime;
    private Integer type;
    private String years;

    public String getYears() {
        return years == null?"":years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBcid() {
        return bcid;
    }

    public void setBcid(Long bcid) {
        this.bcid = bcid;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content == null?"":content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
