package com.jymj.zhglxt.zjd.bean.xm;


import java.util.Date;

/**
 * Created by lc on 2022/8/2.项目季度
 */
public class ProjectQuarterEntity {
    private Long id;
    private Long projectId;
    private Integer progress;//	进度
    private Integer quarter;//	季度
    private String year;//	年
    private Integer xmlx;//	项目立项
    private Integer epczb;//	epc招标
    private Integer sxbl;//	手续办理
    private String bdcqz;//	不动产权证
    private String ydghxkz;//	用地规划许可证
    private String ggz;//	工规证
    private String sgxkz;//	施工许可证
    private Integer sgjd;//	施工阶段
    private String tfgc;//	土方工程
    private Integer htqd;//	合同签订
    private String httext;//	合同签订
    private String createTime;
    private String updateTime;
    private Long userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getXmlx() {
        return xmlx;
    }

    public void setXmlx(Integer xmlx) {
        this.xmlx = xmlx;
    }

    public Integer getEpczb() {
        return epczb;
    }

    public void setEpczb(Integer epczb) {
        this.epczb = epczb;
    }

    public Integer getSxbl() {
        return sxbl;
    }

    public void setSxbl(Integer sxbl) {
        this.sxbl = sxbl;
    }

    public String getBdcqz() {
        return bdcqz;
    }

    public void setBdcqz(String bdcqz) {
        this.bdcqz = bdcqz;
    }

    public String getYdghxkz() {
        return ydghxkz;
    }

    public void setYdghxkz(String ydghxkz) {
        this.ydghxkz = ydghxkz;
    }

    public String getGgz() {
        return ggz;
    }

    public void setGgz(String ggz) {
        this.ggz = ggz;
    }

    public String getSgxkz() {
        return sgxkz;
    }

    public void setSgxkz(String sgxkz) {
        this.sgxkz = sgxkz;
    }

    public Integer getSgjd() {
        return sgjd;
    }

    public void setSgjd(Integer sgjd) {
        this.sgjd = sgjd;
    }

    public String getTfgc() {
        return tfgc;
    }

    public void setTfgc(String tfgc) {
        this.tfgc = tfgc;
    }

    public Integer getHtqd() {
        return htqd;
    }

    public void setHtqd(Integer htqd) {
        this.htqd = htqd;
    }

    public String getHttext() {
        return httext;
    }

    public void setHttext(String httext) {
        this.httext = httext;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
