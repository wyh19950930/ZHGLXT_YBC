package com.jymj.zhglxt.zjd.bean.cygl;

import com.jymj.zhglxt.bean.enums.IsTrueEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ${lc} on 2021/11/2.
 */
public class EnterpriseBuilding implements Serializable {
    private static final long serialVersionUID = 6032889533501410059L;

    private Long id;
    private Integer qyId;//	enterprise_info 表id
    private String fwcqdw;//	房屋产权单位
    private int fqjs;//	是否分期建设  //String
    private String fqjsText;//	是否分期建设
    private BigDecimal yjjzmj;//	已建建筑面积
    private BigDecimal kzjzmj;//	其中：空置建筑面积（平方米）
    private BigDecimal xzlymj;//	闲置楼宇面积（平方米）
    private BigDecimal kzcfjzmj;//	空置厂房建筑面积（平方米）
    private String kzcfjsqk;//	空置厂房建设情况（结构层高及承重）
    private BigDecimal wjjzmj;//	未建建筑面积
    private BigDecimal fwcqzmj;//	房屋产权证面积
    private Integer zrqk;//	转让情况
    private String zrqkText;//	转让情况
    private String dxkj;//	有无地下空间
    private String createDate;//Date 创建时间
    private Integer sccz;//首层承重
    private String scczText;//首层承重
    private Integer sccg;//首层层高
    private String sccgText;//首层层高
    private Integer scmj;//首层面积
    private String scmjText;//首层面积

    public Integer getSccz() {
        return sccz;
    }

    public void setSccz(Integer sccz) {
        this.sccz = sccz;
    }

    public String getScczText() {
        return scczText==null?"":scczText;
    }

    public void setScczText(String scczText) {
        this.scczText = scczText;
    }

    public Integer getSccg() {
        return sccg;
    }

    public void setSccg(Integer sccg) {
        this.sccg = sccg;
    }

    public String getSccgText() {
        return sccgText==null?"":sccgText;
    }

    public void setSccgText(String sccgText) {
        this.sccgText = sccgText;
    }

    public Integer getScmj() {
        return scmj;
    }

    public void setScmj(Integer scmj) {
        this.scmj = scmj;
    }

    public String getScmjText() {
        return scmjText==null?"":scmjText;
    }

    public void setScmjText(String scmjText) {
        this.scmjText = scmjText;
    }

    public BigDecimal getFwcqzmj() {
        return fwcqzmj==null?BigDecimal.ZERO:fwcqzmj;
    }

    public void setFwcqzmj(BigDecimal fwcqzmj) {
        this.fwcqzmj = fwcqzmj;
    }

    public String getFqjsText() {//未选中
        return IsTrueEnum.getName(getFqjs());//fqjsText==null?"":fqjsText
    }

    public void setFqjsText(String fqjsText) {
        this.fqjsText = fqjsText;
    }

    public String getZrqkText() {//未选中
        return IsTrueEnum.getName(getZrqk());//zrqkText==null?"":zrqkText
    }

    public void setZrqkText(String zrqkText) {
        this.zrqkText = zrqkText;
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

    public String getFwcqdw() {
        return fwcqdw==null?"":fwcqdw;
    }

    public void setFwcqdw(String fwcqdw) {
        this.fwcqdw = fwcqdw;
    }

    public int getFqjs() {
        return fqjs;
    }

    public void setFqjs(int fqjs) {
        this.fqjs = fqjs;
    }

    public BigDecimal getYjjzmj() {
        return yjjzmj==null?BigDecimal.ZERO:yjjzmj;
    }

    public void setYjjzmj(BigDecimal yjjzmj) {
        this.yjjzmj = yjjzmj;
    }

    public BigDecimal getKzjzmj() {
        return kzjzmj==null?BigDecimal.ZERO:kzjzmj;
    }

    public void setKzjzmj(BigDecimal kzjzmj) {
        this.kzjzmj = kzjzmj;
    }

    public BigDecimal getXzlymj() {
        return xzlymj==null?BigDecimal.ZERO:xzlymj;
    }

    public void setXzlymj(BigDecimal xzlymj) {
        this.xzlymj = xzlymj;
    }

    public BigDecimal getKzcfjzmj() {
        return kzcfjzmj==null?BigDecimal.ZERO:kzcfjzmj;
    }

    public void setKzcfjzmj(BigDecimal kzcfjzmj) {
        this.kzcfjzmj = kzcfjzmj;
    }

    public String getKzcfjsqk() {
        return kzcfjsqk==null?"":kzcfjsqk;
    }

    public void setKzcfjsqk(String kzcfjsqk) {
        this.kzcfjsqk = kzcfjsqk;
    }

    public BigDecimal getWjjzmj() {
        return wjjzmj==null?BigDecimal.ZERO:wjjzmj;
    }

    public void setWjjzmj(BigDecimal wjjzmj) {
        this.wjjzmj = wjjzmj;
    }

    public Integer getZrqk() {
        return zrqk==null?0:zrqk;
    }

    public void setZrqk(Integer zrqk) {
        this.zrqk = zrqk;
    }

    public String getDxkj() {
        return dxkj==null?"":dxkj;
    }

    public void setDxkj(String dxkj) {
        this.dxkj = dxkj;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
