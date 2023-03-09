package com.jymj.zhglxt.zjd.bean.jsjb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ${lc} on 2022/3/10.
 */
public class OpinionRejectedEntity implements Serializable {
    private static final long serialVersionUID = -4504325699877761876L;
    private Long id;
    private Long opinionId;//	public_opinion---id
    private String opinion;//	部门处理意见
    private String	rejected;//	驳回意见
    private String	chargeback;//	退单意见
    private String createDate;//	创建时间
    private String updateDate;//	修改时间
    private Integer type;//	驳回状态  100 部门驳回   101 处理驳回
    private Integer responsible;//	负责部门
    private Integer through;//	是否通过  1 是  0 否   请求是0  处理是1
    private Long rejectedPeople;//	驳回人

    private List<OpinionRejectedFile> opinionRejectedFiles;

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

    public String getOpinion() {
        return opinion==null?"":opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRejected() {
        return rejected==null?"":rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getChargeback() {
        return chargeback==null?"":chargeback;
    }

    public void setChargeback(String chargeback) {
        this.chargeback = chargeback;
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

    public Integer getType() {
        return type==null?0:type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getResponsible() {
        return responsible==null?0:responsible;
    }

    public void setResponsible(Integer responsible) {
        this.responsible = responsible;
    }

    public Integer getThrough() {
        return through==null?0:through;
    }

    public void setThrough(Integer through) {
        this.through = through;
    }

    public Long getRejectedPeople() {
        return rejectedPeople;
    }

    public void setRejectedPeople(Long rejectedPeople) {
        this.rejectedPeople = rejectedPeople;
    }

    public List<OpinionRejectedFile> getOpinionRejectedFiles() {
        return opinionRejectedFiles==null?new ArrayList<>():opinionRejectedFiles;
    }

    public void setOpinionRejectedFiles(List<OpinionRejectedFile> opinionRejectedFiles) {
        this.opinionRejectedFiles = opinionRejectedFiles;
    }
}
