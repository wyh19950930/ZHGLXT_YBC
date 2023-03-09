package com.jymj.zhglxt.zjd.bean.cygl;

import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ${lc} on 2022/5/9.
 */
public class EnterpriseBusiness implements Serializable {
    private Long id;
    private Long qyId;//	enterprise_basis 表id
    private Long msid;//	enterprise 表id
    private String	yysr;//	营业收入
    private String	cz;//	产值
    private BigDecimal lrze;//	利润总额
    private BigDecimal	zcze;//	资产总额
    private int bdgrs;//	本地工人数量
    private int wdgrs;//	外地工人数量
    private int	dwss;//	就业人口居住区域单位宿舍
    private int	bc;//	就业人口居住区域本村
    private int	qt;//	就业人口居住区域其他
    private BigDecimal	zzs;//	增值税
    private BigDecimal	cswhjss;//	城市维护建设税
    private BigDecimal	jyfj;//	教育及附加
    private BigDecimal	qysds;//	企业所得税
    private BigDecimal	tdzzs;//	土地增值税
    private BigDecimal	tdsys;//	城镇土地使用税
    private BigDecimal	fcs;//	房产税
    private BigDecimal	yhs;//	印花税
    private BigDecimal	grsds;//	个人所得税
    private BigDecimal	hj;//	合计
    private String createDate;//创建时间
    private Integer jyzt;//经营状态
    private String jyztText;//经营状态
    private Integer type;//0 现在  1历史

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQyId() {
        return qyId;
    }

    public void setQyId(Long qyId) {
        this.qyId = qyId;
    }

    public Long getMsid() {
        return msid;
    }

    public void setMsid(Long msid) {
        this.msid = msid;
    }

    public String getYysr() {
        return yysr==null?"":yysr;
    }

    public void setYysr(String yysr) {
        this.yysr = yysr;
    }

    public String getCz() {
        return cz==null?"":cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public BigDecimal getLrze() {
        return lrze==null?BigDecimal.ZERO:lrze;
    }

    public void setLrze(BigDecimal lrze) {
        this.lrze = lrze;
    }

    public BigDecimal getZcze() {
        return zcze==null?BigDecimal.ZERO:zcze;
    }

    public void setZcze(BigDecimal zcze) {
        this.zcze = zcze;
    }

    public int getBdgrs() {
        return bdgrs;
    }

    public void setBdgrs(int bdgrs) {
        this.bdgrs = bdgrs;
    }

    public int getWdgrs() {
        return wdgrs;
    }

    public void setWdgrs(int wdgrs) {
        this.wdgrs = wdgrs;
    }

    public int getDwss() {
        return dwss;
    }

    public void setDwss(int dwss) {
        this.dwss = dwss;
    }

    public int getBc() {
        return bc;
    }

    public void setBc(int bc) {
        this.bc = bc;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public BigDecimal getZzs() {
        return zzs==null?BigDecimal.ZERO:zzs;
    }

    public void setZzs(BigDecimal zzs) {
        this.zzs = zzs;
    }

    public BigDecimal getCswhjss() {
        return cswhjss==null?BigDecimal.ZERO:cswhjss;
    }

    public void setCswhjss(BigDecimal cswhjss) {
        this.cswhjss = cswhjss;
    }

    public BigDecimal getJyfj() {
        return jyfj==null?BigDecimal.ZERO:jyfj;
    }

    public void setJyfj(BigDecimal jyfj) {
        this.jyfj = jyfj;
    }

    public BigDecimal getQysds() {
        return qysds==null?BigDecimal.ZERO:qysds;
    }

    public void setQysds(BigDecimal qysds) {
        this.qysds = qysds;
    }

    public BigDecimal getTdzzs() {
        return tdzzs==null?BigDecimal.ZERO:tdzzs;
    }

    public void setTdzzs(BigDecimal tdzzs) {
        this.tdzzs = tdzzs;
    }

    public BigDecimal getTdsys() {
        return tdsys==null?BigDecimal.ZERO:tdsys;
    }

    public void setTdsys(BigDecimal tdsys) {
        this.tdsys = tdsys;
    }

    public BigDecimal getFcs() {
        return fcs==null?BigDecimal.ZERO:fcs;
    }

    public void setFcs(BigDecimal fcs) {
        this.fcs = fcs;
    }

    public BigDecimal getYhs() {
        return yhs==null?BigDecimal.ZERO:yhs;
    }

    public void setYhs(BigDecimal yhs) {
        this.yhs = yhs;
    }

    public BigDecimal getGrsds() {
        return grsds==null?BigDecimal.ZERO:grsds;
    }

    public void setGrsds(BigDecimal grsds) {
        this.grsds = grsds;
    }

    public BigDecimal getHj() {
        return hj==null?BigDecimal.ZERO:hj;
    }

    public void setHj(BigDecimal hj) {
        this.hj = hj;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getJyzt() {
        return jyzt==null?0:jyzt;
    }

    public void setJyzt(Integer jyzt) {
        this.jyzt = jyzt;
    }

    public String getJyztText() {
        return JyztEnum.getName(getJyzt());//jyztText==null?"":jyztText
    }

    public void setJyztText(String jyztText) {
        this.jyztText = jyztText;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
