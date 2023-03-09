package com.jymj.zhglxt.zjd.bean.cygl;


import com.jymj.zhglxt.bean.enums.IndustryEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.RegistraEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ${lc} on 2021/8/23.
 */
public class EnterpriseEntity implements Serializable {
    private static final long serialVersionUID = -9109209070695673421L;

    private Long id;
    private String bh;
    private Integer qyId;//	enterprise_basis 表id
    private Integer qylx;//	企业类型
    private String qylxText;//	企业类型
    private Integer gxqy;//	高新企业
    private String gxqyText;//	高新企业
    private Integer czqk;//	是否转租 1是 0否
    private String	zcdz;//	注册地址
    private String	sjjydz;//	实际经营地址
    private String	zczj;//	注册资金
    private String zcdate;//Date	营业执照注册日期
    private String	rzdate;//Date	入驻日期
    private String	yysr;//	营业收入
    private String	cz;//	产值
    private String	swzcdz;//	税务登记证注册地
    private BigDecimal lrze;//	利润总额
    private BigDecimal	zcze;//	资产总额
    private int bdgrs;//	本地工人数量
    private int wdgrs;//	外地工人数量
    private String	dwss;//	就业人口居住区域单位宿舍
    private String	bc;//	就业人口居住区域本村
    private String	qt;//	就业人口居住区域其他
    private String	xzlxr;// 行政联系人
    private String	lxdh;//	联系电话
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
    private String	remark;//	备注
    private Integer parent;//	父级 1  子级 0
    private String	qyname;//	企业名称
    private String createDate;//Date  创建时间
    private Integer jyzt;//经营状态
    private String jyztText;//经营状态
    private boolean isShow = true;//是否展开
    private Integer num;
    private Integer hylx;//行业类型
    private String hylxText;
    private Integer zclx;//注册类型
    private String zclxText;
    private EnterpriseBusiness enterpriseBusinesse;

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public EnterpriseBusiness getEnterpriseBusinesse() {
        if (enterpriseBusinesse==null){
            enterpriseBusinesse = new EnterpriseBusiness();
        }
        return enterpriseBusinesse;
    }

    public void setEnterpriseBusinesse(EnterpriseBusiness enterpriseBusinesse) {
        this.enterpriseBusinesse = enterpriseBusinesse;
    }

    public Integer getHylx() {
        return hylx==null?0:hylx;
    }

    public void setHylx(Integer hylx) {
        this.hylx = hylx;
    }

    public String getHylxText() {
        return IndustryEnum.getName(getHylx());//hylxText==null?"":hylxText
    }

    public void setHylxText(String hylxText) {
        this.hylxText = hylxText;
    }

    public Integer getZclx() {
        return zclx==null?0:zclx;
    }

    public void setZclx(Integer zclx) {
        this.zclx = zclx;
    }

    public String getZclxText() {
        return RegistraEnum.getName(getZclx());//zclxText==null?"":zclxText
    }

    public void setZclxText(String zclxText) {
        this.zclxText = zclxText;
    }

    public Integer getNum() {
        return num==null?0:num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getJyzt() {
        return jyzt==null?0:jyzt;//==null?1:jyzt
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

    public Integer getQylx() {
        return qylx==null?0:qylx;
    }

    public void setQylx(Integer qylx) {
        this.qylx = qylx;
    }

    public String getQylxText() {
        return EnterpriseTypeEnum.getName(getQylx());//qylxText==null?"":qylxText
    }

    public void setQylxText(String qylxText) {
        this.qylxText = qylxText;
    }

    public Integer getGxqy() {
        return gxqy==null?0:gxqy;
    }

    public void setGxqy(Integer gxqy) {
        this.gxqy = gxqy;
    }
//EnterpriseGxEnum.getName(gxqy)
    public String getGxqyText() {
        return EnterpriseGxEnum.getName(getGxqy());//gxqyText
    }

    public void setGxqyText(String gxqyText) {
        this.gxqyText = gxqyText;
    }

    public Integer getCzqk() {
        return czqk==null?0:czqk;
    }

    public void setCzqk(Integer czqk) {
        this.czqk = czqk;
    }

    public String getZcdz() {
        return zcdz==null?"":zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz;
    }

    public String getSjjydz() {
        return sjjydz==null?"":sjjydz;
    }

    public void setSjjydz(String sjjydz) {
        this.sjjydz = sjjydz;
    }

    public String getZczj() {
        return zczj==null?"":zczj;
    }

    public void setZczj(String zczj) {
        this.zczj = zczj;
    }

    public String getZcdate() {
        return zcdate==null?"":zcdate;
    }

    public void setZcdate(String zcdate) {
        this.zcdate = zcdate;
    }

    public String getRzdate() {
        return rzdate==null?"":rzdate;
    }

    public void setRzdate(String rzdate) {
        this.rzdate = rzdate;
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

    public String getSwzcdz() {
        return swzcdz==null?"":swzcdz;
    }

    public void setSwzcdz(String swzcdz) {
        this.swzcdz = swzcdz;
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

    public String getDwss() {
        return dwss==null?"":dwss;
    }

    public void setDwss(String dwss) {
        this.dwss = dwss;
    }

    public String getBc() {
        return bc==null?"":bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getQt() {
        return qt==null?"":qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getXzlxr() {
        return xzlxr==null?"":xzlxr;
    }

    public void setXzlxr(String xzlxr) {
        this.xzlxr = xzlxr;
    }

    public String getLxdh() {
        return lxdh==null?"":lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
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

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getQyname() {
        return qyname==null?"":qyname;
    }

    public void setQyname(String qyname) {
        this.qyname = qyname;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
