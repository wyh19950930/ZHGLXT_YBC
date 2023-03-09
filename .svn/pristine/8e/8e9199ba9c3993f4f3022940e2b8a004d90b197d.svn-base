package com.jymj.zhglxt.xm.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/12/5.
 */
public class BcProjectEntity {
    private Long id;
    private String xzqmc;
    private String zhen;
    private String xzq;
    private String code;
    private String location;//	位置点位
    private String content;//	内容
    private String title;//	标题
    private BigDecimal ydgm;//	用地规模
    private BigDecimal jzgm;//	建筑规模
    private BigDecimal cdmj;//	场地面积
    private BigDecimal tzgm1;//	投资规模1
    private BigDecimal tzgm2;//	投资规模2
    private String ydlx;//	用地类型
    private Integer xmlx;//	项目类型 1 游  2 娱  3居  4食
    private String createTime;
    private String updateTime;
    private boolean collection;//是否收藏
    private Long userId;
    private List<Long> lxids;//要删除的类型id集合


    private List<BcProjectLx> projectLxList;//多选类型
    private List<BcProjectFile> projectFiles;
    private List<BcProjectFile> projectFiles1;

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getCdmj() {
        return cdmj==null?BigDecimal.ZERO:cdmj;
    }

    public void setCdmj(BigDecimal cdmj) {
        this.cdmj = cdmj;
    }

    public BigDecimal getTzgm1() {
        return tzgm1==null?BigDecimal.ZERO:tzgm1;
    }

    public void setTzgm1(BigDecimal tzgm1) {
        this.tzgm1 = tzgm1;
    }

    public BigDecimal getTzgm2() {
        return tzgm2==null?BigDecimal.ZERO:tzgm2;
    }

    public void setTzgm2(BigDecimal tzgm2) {
        this.tzgm2 = tzgm2;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getXzq() {
        return xzq==null?"":xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content==null?"":content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title==null?"":title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getYdgm() {
        return ydgm==null?BigDecimal.ZERO:ydgm;
    }

    public void setYdgm(BigDecimal ydgm) {
        this.ydgm = ydgm;
    }

    public BigDecimal getJzgm() {
        return jzgm==null?BigDecimal.ZERO:jzgm;
    }

    public void setJzgm(BigDecimal jzgm) {
        this.jzgm = jzgm;
    }

    public String getYdlx() {
        return ydlx==null?"":ydlx;
    }

    public void setYdlx(String ydlx) {
        this.ydlx = ydlx;
    }

    public Integer getXmlx() {
        return xmlx;
    }

    public void setXmlx(Integer xmlx) {
        this.xmlx = xmlx;
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

    public List<Long> getLxids() {
        if (lxids==null){
            lxids = new ArrayList<>();
        }
        return lxids;
    }

    public void setLxids(List<Long> lxids) {
        this.lxids = lxids;
    }

    public List<BcProjectLx> getProjectLxList() {
        if (projectLxList==null){
            projectLxList = new ArrayList<>();
        }
        return projectLxList;
    }

    public void setProjectLxList(List<BcProjectLx> projectLxList) {
        this.projectLxList = projectLxList;
    }

    public List<BcProjectFile> getProjectFiles() {
        if (projectFiles==null){
            projectFiles = new ArrayList<>();
        }
        return projectFiles;
    }

    public void setProjectFiles(List<BcProjectFile> projectFiles) {
        this.projectFiles = projectFiles;
    }

    public List<BcProjectFile> getProjectFiles1() {
        if (projectFiles1==null){
            projectFiles1 = new ArrayList<>();
        }
        return projectFiles1;
    }

    public void setProjectFiles1(List<BcProjectFile> projectFiles1) {
        this.projectFiles1 = projectFiles1;
    }
}
