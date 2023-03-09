package com.jymj.zhglxt.zjd.bean.cygl;


import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2021/11/10.
 */
public class YztNyyqEntity implements Serializable {
    private Long gid;
    private String xmmc;//	项目名称
    private String lx;//	类型
    private String dwmc;//	单位名称
    private String hy;//	行业
    private String zhen;//	所在镇
    private String xzqmc;//	所在村
    private String qrbh;//	确认编号
    private BigDecimal ydmj;//	用地面积亩
    private BigDecimal scss;//	生产设施亩
    private BigDecimal fsss;//	附属设施㎡
    private String lxr;//联系人
    private String	phone;//电话
    private String geometry;
    private String center;

    private NydHtEntity nydHtEntitie;//合同信息
    private List<NydHtFileEntity> nydHtFileEntities;//合同文件
    private List<NydZjEntity> nydZjEntities;//租金信息
    private List<TdlyEntity> tdlyEntities;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getZhen() {
        return zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getQrbh() {
        return qrbh;
    }

    public void setQrbh(String qrbh) {
        this.qrbh = qrbh;
    }

    public BigDecimal getYdmj() {
        return ydmj;
    }

    public void setYdmj(BigDecimal ydmj) {
        this.ydmj = ydmj;
    }

    public BigDecimal getScss() {
        return scss;
    }

    public void setScss(BigDecimal scss) {
        this.scss = scss;
    }

    public BigDecimal getFsss() {
        return fsss;
    }

    public void setFsss(BigDecimal fsss) {
        this.fsss = fsss;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public NydHtEntity getNydHtEntitie() {
        return nydHtEntitie == null?new NydHtEntity():nydHtEntitie;
    }

    public void setNydHtEntitie(NydHtEntity nydHtEntitie) {
        this.nydHtEntitie = nydHtEntitie;
    }

    public List<NydHtFileEntity> getNydHtFileEntities() {
        return nydHtFileEntities==null?new ArrayList<>():nydHtFileEntities;
    }

    public void setNydHtFileEntities(List<NydHtFileEntity> nydHtFileEntities) {
        this.nydHtFileEntities = nydHtFileEntities;
    }

    public List<NydZjEntity> getNydZjEntities() {
        return nydZjEntities==null?new ArrayList<>():nydZjEntities;
    }

    public void setNydZjEntities(List<NydZjEntity> nydZjEntities) {
        this.nydZjEntities = nydZjEntities;
    }

    public List<TdlyEntity> getTdlyEntities() {
        return tdlyEntities==null?new ArrayList<>():tdlyEntities;
    }

    public void setTdlyEntities(List<TdlyEntity> tdlyEntities) {
        this.tdlyEntities = tdlyEntities;
    }
}
