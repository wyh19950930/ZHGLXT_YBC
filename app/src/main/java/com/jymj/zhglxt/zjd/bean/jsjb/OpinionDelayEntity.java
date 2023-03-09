package com.jymj.zhglxt.zjd.bean.jsjb;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ${lc} on 2022/3/10.延时申请表
 */
public class OpinionDelayEntity implements Serializable{
    private static final long serialVersionUID = 7768507964651869472L;
    private Long id;
    private Long opinionId;//	public_opinion---id
    private String caseNumber;//	案卷号
    private String name;//	操作人名
    private Long userId;//	操作人
    private String cause;//	申请原因
    private String rejected;//	驳回原因
    private Integer delay;//	延期时间
    private Integer through;//	是否通过   1 是  0 否
    private String createDate;//	创建时间
    private String updateDate;//	修改时间
    private Integer isDelete;//	是否删除 0存在  1删除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Long opinionId) {
        this.opinionId = opinionId;
    }

    public String getCaseNumber() {
        return caseNumber==null?"":caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause==null?"":cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getRejected() {
        return rejected==null?"":rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public Integer getDelay() {
        return delay==null?0:delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Integer getThrough() {
        return through==null?0:through;
    }

    public void setThrough(Integer through) {
        this.through = through;
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

    public Integer getIsDelete() {
        return isDelete==null?0:isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
