package com.jymj.zhglxt.zjd.bean.cygl;

import java.io.Serializable;

/**
 * Created by ${lc} on 2021/8/25.
 */

public class EnterpriseFileEntity implements Serializable {
    private static final long serialVersionUID = 5629144825191078187L;
    private final String APPLE_SAVE = "EnterpriseFile.dirPath";

    private Long id;
    private Integer qyId;
    private String filename;
    private String name;
    private String path;
    private Integer filetype;
    private String createDate;//Date
    private String remark;
    private String url;
    private Long msid;

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQyId() {
        return qyId;
    }

    public void setQyId(Integer qyId) {
        this.qyId = qyId;
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

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getMsid() {
        return msid;
    }

    public void setMsid(Long msid) {
        this.msid = msid;
    }
}
