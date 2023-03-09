package com.jymj.zhglxt.rjhj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2020/11/16.
 */
public class PointRecordEntity {
    private Integer id;
    private String location;//	点位
    private Integer name;//	名称
    private Integer lrr;//	录入人
    private String lrrtext;//	录入人
    private String kfsj;//	开放时间
    private String remark;//	备注
    private String wz;//	村名
    private String bh ;//	编号
    private String xzqmc;
    private String code;

    private String zhen;
    private BigDecimal area;//面积
    private Integer dws;//蹲位数
    private String category;//类别
    private String jrwsgw;//是否接入污水管网
    private String jcny;//建成年月
    private BigDecimal jscb;//建设成本(万元)
    private String yxqk;//运行情况  (开放/未开放)
    private String kfrq;//开放日期
    private String kfsc;//开放时长
    private String yssj;//验收时间
    private String gczt;//公厕状态  在施/未完工/完工
    private String gcsx;//公厕属性  新建/改造
    private List<RenovatedFile> files;

    public String getYssj() {
        return yssj == null ? "" : yssj;
    }

    public void setYssj(String yssj) {
        this.yssj = yssj;
    }

    public String getGczt() {
        return gczt == null ? "" : gczt;
    }

    public void setGczt(String gczt) {
        this.gczt = gczt;
    }

    public String getGcsx() {
        return gcsx == null ? "" : gcsx;
    }

    public void setGcsx(String gcsx) {
        this.gcsx = gcsx;
    }

    public String getZhen() {
        return zhen == null ? "" : zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public BigDecimal getArea() {
        return area == null?new BigDecimal(0):area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getDws() {
        return dws == null?0:dws;
    }

    public void setDws(Integer dws) {
        this.dws = dws;
    }

    public String getCategory() {
        return category == null?"":category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJrwsgw() {
        return jrwsgw== null?"":jrwsgw;
    }

    public void setJrwsgw(String jrwsgw) {
        this.jrwsgw = jrwsgw;
    }

    public String getJcny() {
        return jcny== null?"":jcny;
    }

    public void setJcny(String jcny) {
        this.jcny = jcny;
    }

    public BigDecimal getJscb() {
        return jscb== null?new BigDecimal(0):jscb;
    }

    public void setJscb(BigDecimal jscb) {
        this.jscb = jscb;
    }

    public String getYxqk() {
        return yxqk== null?"":yxqk;
    }

    public void setYxqk(String yxqk) {
        this.yxqk = yxqk;
    }

    public String getKfrq() {
        return kfrq== null?"":kfrq;
    }

    public void setKfrq(String kfrq) {
        this.kfrq = kfrq;
    }

    public String getKfsc() {
        return kfsc== null?"":kfsc;
    }

    public void setKfsc(String kfsc) {
        this.kfsc = kfsc;
    }

    public String getWz() {
        return wz==null?"":wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public Integer getLrr() {
        return lrr;
    }

    public void setLrr(Integer lrr) {
        this.lrr = lrr;
    }

    public String getLrrtext() {
        return lrrtext==null?"":lrrtext;
    }

    public void setLrrtext(String lrrtext) {
        this.lrrtext = lrrtext;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id==null?-1:id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getKfsj() {
        return kfsj==null?"":kfsj;
    }

    public void setKfsj(String kfsj) {
        this.kfsj = kfsj;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*public List<RenovatedFile> getRenovatedFiles() {
        return files==null?new ArrayList<>():files;
    }

    public void setRenovatedFiles(List<RenovatedFile> files) {
        this.files = files;
    }*/

    public List<RenovatedFile> getFiles() {
        return files==null?new ArrayList<>():files;
    }

    public void setFiles(List<RenovatedFile> files) {
        this.files = files;
    }
}
