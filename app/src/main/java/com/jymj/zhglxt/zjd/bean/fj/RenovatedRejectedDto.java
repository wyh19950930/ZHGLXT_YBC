package com.jymj.zhglxt.zjd.bean.fj;

import java.util.ArrayList;
import java.util.List;

public class RenovatedRejectedDto {
    private Long id;
    private String cltime;//  处理时间
    private String bhbz;//    驳回备注
    private Long esid;//    renovated -- id
    private String bhr;// 驳回人
    private String bhyj;//    驳回意见
    private String bhtime;// 驳回时间
    private String clr;// 处理人
    private String clphone;// 处理人电话
    private String clbz;//    处理备注
    private String bhtel;//   驳回人电话
    private Long type;//    驳回  1  4->3
    private Long ylId;//    yl_objectid
    private List<RenovatedFile> renovatedFiles;

    public Long getId() {
        return id==null?0:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCltime() {
        return cltime;
    }

    public void setCltime(String cltime) {
        this.cltime = cltime;
    }

    public String getBhbz() {
        return bhbz==null?"":bhbz;
    }

    public void setBhbz(String bhbz) {
        this.bhbz = bhbz;
    }

    public Long getEsid() {
        return esid==null?0:esid;
    }

    public void setEsid(Long esid) {
        this.esid = esid;
    }

    public String getBhr() {
        return bhr==null?"":bhr;
    }

    public void setBhr(String bhr) {
        this.bhr = bhr;
    }

    public String getBhyj() {
        return bhyj==null?"":bhyj;
    }

    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }

    public String getBhtime() {
        return bhtime==null?"":bhtime;
    }

    public void setBhtime(String bhtime) {
        this.bhtime = bhtime;
    }

    public String getClr() {
        return clr==null?"":clr;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public String getClphone() {
        return clphone==null?"":clphone;
    }

    public void setClphone(String clphone) {
        this.clphone = clphone;
    }

    public String getClbz() {
        return clbz==null?"":clbz;
    }

    public void setClbz(String clbz) {
        this.clbz = clbz;
    }

    public String getBhtel() {
        return bhtel==null?"":bhtel;
    }

    public void setBhtel(String bhtel) {
        this.bhtel = bhtel;
    }

    public Long getType() {
        return type==null?0:type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getYlId() {
        return ylId==null?0:ylId;
    }

    public void setYlId(Long ylId) {
        this.ylId = ylId;
    }

    public List<RenovatedFile> getRenovatedFiles() {
        return renovatedFiles==null?new ArrayList<>():renovatedFiles;
    }

    public void setRenovatedFiles(List<RenovatedFile> renovatedFiles) {
        this.renovatedFiles = renovatedFiles;
    }
}
