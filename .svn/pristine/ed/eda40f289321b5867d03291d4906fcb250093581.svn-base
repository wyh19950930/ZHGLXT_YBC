package com.jymj.zhglxt.zjd.bean.cygl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2021/8/23.
 */
public class EnterpriseInfoEntity implements Serializable {
    private static final long serialVersionUID = -5985243525209511965L;

    private Long id;
    private String tdsyr;//	土地使用人
    private String tdxz;//	土地性质
    private String qs;//	权属
    private String tdqddate;//Date	土地取得年限
    private String tdsynx;//	土地剩余使用年限
    private String tdjg;//	土地价格（万元/亩）
    private BigDecimal  tdzmj;//	土地证面积（公顷）
    private String tdqdfs;//	土地取得方式
    private String createDate;//Date 创建时间
    private Integer qyId;//	enterprise_basis 表id

    private List<Long> ids;
    private List<EnterpriseFileEntity> enterpriseFileEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTdsyr() {
        return tdsyr==null?"":tdsyr;
    }

    public void setTdsyr(String tdsyr) {
        this.tdsyr = tdsyr;
    }

    public String getTdxz() {
        return tdxz==null?"":tdxz;
    }

    public void setTdxz(String tdxz) {
        this.tdxz = tdxz;
    }

    public String getQs() {
        return qs==null?"":qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getTdqddate() {
        return tdqddate==null?"":tdqddate;
    }

    public void setTdqddate(String tdqddate) {
        this.tdqddate = tdqddate;
    }

    public String getTdsynx() {
        return tdsynx==null?"":tdsynx;
    }

    public void setTdsynx(String tdsynx) {
        this.tdsynx = tdsynx;
    }

    public String getTdqdfs() {
        return tdqdfs==null?"":tdqdfs;
    }

    public void setTdqdfs(String tdqdfs) {
        this.tdqdfs = tdqdfs;
    }

    public String getTdjg() {
        return tdjg==null?"":tdjg;
    }

    public void setTdjg(String tdjg) {
        this.tdjg = tdjg;
    }

    public BigDecimal getTdzmj() {
        return tdzmj==null?BigDecimal.ZERO:tdzmj;
    }

    public void setTdzmj(BigDecimal tdzmj) {
        this.tdzmj = tdzmj;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getQyId() {
        return qyId;
    }

    public void setQyId(Integer qyId) {
        this.qyId = qyId;
    }

    public List<Long> getIds() {
        if (ids==null){
            ids = new ArrayList<Long>();
        }
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<EnterpriseFileEntity> getEnterpriseFileEntities() {
        if (enterpriseFileEntities==null){
            enterpriseFileEntities = new ArrayList<>();
        }
        return enterpriseFileEntities;
    }

    public void setEnterpriseFileEntities(List<EnterpriseFileEntity> enterpriseFileEntities) {
        this.enterpriseFileEntities = enterpriseFileEntities;
    }
}
