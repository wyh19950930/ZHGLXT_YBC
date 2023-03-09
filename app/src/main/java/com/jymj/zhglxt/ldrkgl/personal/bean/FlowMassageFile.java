package com.jymj.zhglxt.ldrkgl.personal.bean;


/**
 * Created by ${lc} on 2022/2/22.
 */
public class FlowMassageFile {
    private Long id;
    private Long messageId;
    private String filename;
    private String url;
    private Integer filetype;
    private String createDate;
    private String remark;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFilename() {
        return filename==null?"":filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPath() {
        return path==null?"":path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
