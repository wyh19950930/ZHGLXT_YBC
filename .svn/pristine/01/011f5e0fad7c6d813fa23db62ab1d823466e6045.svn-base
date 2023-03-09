package com.jymj.zhglxt.zjd.bean.xm;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lc on 2022/8/2.项目
 */
public class ProjectEntity {
    private Long id;
    private String name;//	项目名称
    private String xmlb;//	项目类别
    private BigDecimal area;//	用地面积
    private BigDecimal zdmj;//	征地面积
    private BigDecimal dsw;//	地上物
    private Integer dzrk;//	待转人口
    private String tzzt;//	投资主体
    private String tzfs;//	投资方式
    private BigDecimal zjxq;//	资金需求
    private BigDecimal xmjdzdmj;//	项目进度征地面积
    private BigDecimal xnjddsw;//	项目进度地上物
    private BigDecimal xmjddzrk;//	项目进度待转人口
    private String createTime;
    private String updateTime;
    private Long userId;
    private List<Long> ghid;
    private String geometry;
    private String center;
    private String code;
    private Integer steps;//步骤  1项目立项  2 epc招标  3 手续办理  4 施工阶段  5 合同签订
    private Integer progress;//步骤进度   1 未开始  2办理中   3已完成

    private List<ProjectQuarterEntity> projectQuarterEntities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name == null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXmlb() {
        return xmlb == null?"":xmlb;
    }

    public void setXmlb(String xmlb) {
        this.xmlb = xmlb;
    }

    public BigDecimal getArea() {
        return area == null?new BigDecimal("0"):area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getZdmj() {
        return zdmj == null?new BigDecimal("0"):zdmj;
    }

    public void setZdmj(BigDecimal zdmj) {
        this.zdmj = zdmj;
    }

    public BigDecimal getDsw() {
        return dsw == null?new BigDecimal("0"):dsw;
    }

    public void setDsw(BigDecimal dsw) {
        this.dsw = dsw;
    }

    public Integer getDzrk() {
        return dzrk;
    }

    public void setDzrk(Integer dzrk) {
        this.dzrk = dzrk;
    }

    public String getTzzt() {
        return tzzt == null?"":tzzt;
    }

    public void setTzzt(String tzzt) {
        this.tzzt = tzzt;
    }

    public String getTzfs() {
        return tzfs == null?"":tzfs;
    }

    public void setTzfs(String tzfs) {
        this.tzfs = tzfs;
    }

    public BigDecimal getZjxq() {
        return zjxq == null?new BigDecimal("0"):zjxq;
    }

    public void setZjxq(BigDecimal zjxq) {
        this.zjxq = zjxq;
    }

    public BigDecimal getXmjdzdmj() {
        return xmjdzdmj == null?new BigDecimal("0"):xmjdzdmj;
    }

    public void setXmjdzdmj(BigDecimal xmjdzdmj) {
        this.xmjdzdmj = xmjdzdmj;
    }

    public BigDecimal getXnjddsw() {
        return xnjddsw == null?new BigDecimal("0"):xnjddsw;
    }

    public void setXnjddsw(BigDecimal xnjddsw) {
        this.xnjddsw = xnjddsw;
    }

    public BigDecimal getXmjddzrk() {
        return xmjddzrk == null?new BigDecimal("0"):xmjddzrk;
    }

    public void setXmjddzrk(BigDecimal xmjddzrk) {
        this.xmjddzrk = xmjddzrk;
    }

    public String getCreateTime() {
        return createTime == null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime == null?"":updateTime;
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

    public List<Long> getGhid() {
        if (ghid == null) {
            ghid = new ArrayList<>();
        }
        return ghid;
    }

    public void setGhid(List<Long> ghid) {
        this.ghid = ghid;
    }

    public String getGeometry() {
        return geometry == null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getCenter() {
        return center == null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getSteps() {
        return steps == null ? 1 : steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getProgress() {
        return progress == null ? 1 : progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public List<ProjectQuarterEntity> getProjectQuarterEntities() {
        if (projectQuarterEntities == null){
            projectQuarterEntities = new ArrayList<>();
        }
        return projectQuarterEntities;
    }

    public void setProjectQuarterEntities(List<ProjectQuarterEntity> projectQuarterEntities) {
        this.projectQuarterEntities = projectQuarterEntities;
    }
}
