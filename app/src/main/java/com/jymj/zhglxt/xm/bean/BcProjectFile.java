package com.jymj.zhglxt.xm.bean;

import java.util.Date;

/**
 * Created by lc on 2022/12/5.
 */
public class BcProjectFile {
    private static final String PATH = "projectFile";
    private Long id;
    private Long projectId;
    private String filename;
    private String path;
    private String remark;
    private String createTime;
    private String updateTime;
    private Long userId;
    private String url;
    private Integer type;//编号
    private Integer sorting;//排序
    public Integer getType() {
        return type==null?0:type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSorting() {
        return sorting==null?0:sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }


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

    public String getFilename() {
        return filename==null?"":filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path==null?"":path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime==null?"":updateTime;
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

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
