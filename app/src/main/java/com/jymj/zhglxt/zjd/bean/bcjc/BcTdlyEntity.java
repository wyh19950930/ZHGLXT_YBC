package com.jymj.zhglxt.zjd.bean.bcjc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/7/28.土地利用
 */
public class BcTdlyEntity {
    private Long id;
    private String xzqmc;
    private String code;
    private String nydmj;//	农用地占地面积(亩)
    private String gydmj;//	农用地国有地(亩)
    private String nydqd;//	农用地确权确地面积
    private String nydql;//	农用地确权确利面积
    private String nydqg;//	农用地确权确股面积
    private String gdmj;//	耕地占地面积(亩)
    private String gdzzmj;//	耕地自种面积
    private String gdlzmj;//	耕地流转面积
    private String gdqtmj;//	耕地其他面积
    private String gdjg;//	耕地平均流转（元/亩）或出租（万元/宗）价格
    private String gdqd;//	耕地确权确地面积
    private String gdql;//	耕地确权确利面积
    private String gdqg;//	耕地确权确股面积
    private String gdbz;//	耕地备注
    private String ydmj;//	园地占地面积(亩)
    private String ydzzmj;//	园地自种面积
    private String ydlzmj;//	园地流转面积
    private String ydqtmj;//	园地其他面积
    private String ydjg;//	园地平均流转（元/亩）或出租（万元/宗）价格
    private String ydbz;//	园地备注
    private String ydqd;//	园地确权确地面积
    private String ydql;//	园地确权确利面积
    private String ydqg;//	园地确权确股面积
    private String ldmj;//	林地占地面积(亩)
    private String ldwjy;//	林地无经营面积
    private String ldlxzyz;//	林地林下种养殖面积
    private String ldjg;//	林地平均流转（元/亩）或出租（万元/宗）价格
    private String ldbz;//	林地备注
    private String ldqd;//	林地确权确地面积
    private String ldql;//	林地确权确利面积
    private String ldqg;//	林地确权确股面积
    private String nyjsmj;//	农业设施建设面积
    private String nyjssyqk;//	设施农用地使用情况
    private String nyjszzmj;//	农业设施建设自种面积
    private String nyjslzmj;//	农业设施建设流转面积
    private String nyjsqtmj;//	农业设施建设其他面积
    private String nyjsjg;//	农业设施建设平均流转（元/亩）或出租（万元/宗）价格
    private String nyjsbz;//	农业设施建设备注
    private String qtnydmj;//	其他农用地面积
    private String qtnydsyqk;//	其他农用地使用情况
    private String qtnydjg;//	其他农用地平均流转（元/亩）或出租（万元/宗）价格
    private String qtnydbz;//	其他农用地备注
    private String jsydmj;//	建设用地占地面积
    private String jsydsyqk;//	建设用地使用情况
    private String jsydjg;//	建设用地平均流转（元/亩）或出租（万元/宗）价格
    private String jsydpf;//	建设用地 规划批复使用面积
    private String jzydmj;//	居住用地占地面积
    private String jzydzz;//	居住用地自住
    private String jzydcz;//	居住用地出租
    private String jzydqt;//	居住用地其他
    private String jzydjg;//	居住用地平均流转（元/亩）或出租（万元/宗）价格
    private String jzydms;//	居住用地民宿
    private String ggydmj;//	公共管理与公共服务用地占地面积
    private String ggydsyqk;//	公共管理与公共服务用地使用情况
    private String ggydzz;//公共管理与公共服务用地自住
    private String ggydcz;//公共管理与公共服务用地出租
    private String ggydqt;//公共管理与公共服务用地其他
    private String ggydlzjg;//公共管理与公共服务用地平均流转（元/亩）或出租（万元/宗）价格
    private String ggydbz;//	公共管理与公共服务用地备注




    private String jtydmj;//	集体经营性建设用地占地面积
    private String jtydsyqk;//	集体经营性建设用地使用情况

    private String jtydzz;//集体经营性建设用地自住
    private String jtydcz;//集体经营性建设用地出租
    private String jtydqt;//集体经营性建设用地其他
    private String jtydjg;//	集体经营性建设用地平均流转（元/亩）或出租（万元/宗）价格
    private String jtydbz;//	集体经营性建设用地备注
    private String tdzmj;//	土地总面积
    private String gymj;//	国有面积
    private String yjjbnt;//	基本永久农田
    private String pyzl;//	平原造林
    private String jtjy;//	家庭经营
    private String jtjy1;//	集体经营
    private String dhcb;//	大户承包
    private String dwzl;//	对外租赁
    private String qtmj;//	其他面积
    private String zjmj;//	总计面积
//    private String createTime;//Date
//    private String updateTime;//Date
    private String years;//	年份
    private Long userId;
    private String cunname;//填写名字
    private String zhenname;//审核名字
    private String qvname;//审批名字
    private String shtime;//审核时间
    private String sptime;//审批时间
    private Integer process;//流程  1 村填写  2 镇审核  3 区审批
    private List<BcrejectedEntity> bcrejectedEntities;
    private String xzq;
    private String zhen;

    public String getXzq() {
        return xzq==null?"":xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getCunname() {
        return cunname==null?"":cunname;
    }

    public void setCunname(String cunname) {
        this.cunname = cunname;
    }

    public String getZhenname() {
        return zhenname==null?"":zhenname;
    }

    public void setZhenname(String zhenname) {
        this.zhenname = zhenname;
    }

    public String getQvname() {
        return qvname==null?"":qvname;
    }

    public void setQvname(String qvname) {
        this.qvname = qvname;
    }

    public String getShtime() {
        return shtime==null?"":shtime;
    }

    public void setShtime(String shtime) {
        this.shtime = shtime;
    }

    public String getSptime() {
        return sptime==null?"":sptime;
    }

    public void setSptime(String sptime) {
        this.sptime = sptime;
    }

    public Integer getProcess() {
        return process==null?0:process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getGymj() {
        return gymj==null?"0":gymj;
    }

    public void setGymj(String gymj) {
        this.gymj = gymj;
    }

    public String getNyjssyqk() {
        return nyjssyqk==null?"--":nyjssyqk;
    }

    public void setNyjssyqk(String nyjssyqk) {
        this.nyjssyqk = nyjssyqk;
    }

    public String getLdbz() {
        return ldbz==null?"0":ldbz;
    }

    public void setLdbz(String ldbz) {
        this.ldbz = ldbz;
    }

    public String getYdbz() {
        return ydbz==null?"0":ydbz;
    }

    public void setYdbz(String ydbz) {
        this.ydbz = ydbz;
    }

    public String getGdbz() {
        return gdbz==null?"0":gdbz;
    }

    public void setGdbz(String gdbz) {
        this.gdbz = gdbz;
    }

    public String getNydqd() {
        return nydqd==null?"0":nydqd;
    }

    public void setNydqd(String nydqd) {
        this.nydqd = nydqd;
    }

    public String getNydql() {
        return nydql==null?"0":nydql;
    }

    public void setNydql(String nydql) {
        this.nydql = nydql;
    }

    public String getNydqg() {
        return nydqg==null?"0":nydqg;
    }

    public void setNydqg(String nydqg) {
        this.nydqg = nydqg;
    }

    public Long getId() {
        return id == null?0L:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNydmj() {
        return nydmj==null?"0":nydmj;
    }

    public void setNydmj(String nydmj) {
        this.nydmj = nydmj;
    }

    public String getGydmj() {
        return gydmj==null?"":gydmj;
    }

    public void setGydmj(String gydmj) {
        this.gydmj = gydmj;
    }

    public String getGdmj() {
        return gdmj==null?"0":gdmj;
    }

    public void setGdmj(String gdmj) {
        this.gdmj = gdmj;
    }

    public String getGdzzmj() {
        return gdzzmj==null?"0":gdzzmj;
    }

    public void setGdzzmj(String gdzzmj) {
        this.gdzzmj = gdzzmj;
    }

    public String getGdlzmj() {
        return gdlzmj==null?"0":gdlzmj;
    }

    public void setGdlzmj(String gdlzmj) {
        this.gdlzmj = gdlzmj;
    }

    public String getGdqtmj() {
        return gdqtmj==null?"0":gdqtmj;
    }

    public void setGdqtmj(String gdqtmj) {
        this.gdqtmj = gdqtmj;
    }

    public String getGdjg() {
        return gdjg==null?"0":gdjg;
    }

    public void setGdjg(String gdjg) {
        this.gdjg = gdjg;
    }

    public String getGdqd() {
        return gdqd==null?"":gdqd;
    }

    public void setGdqd(String gdqd) {
        this.gdqd = gdqd;
    }

    public String getGdql() {
        return gdql==null?"":gdql;
    }

    public void setGdql(String gdql) {
        this.gdql = gdql;
    }

    public String getGdqg() {
        return gdqg==null?"":gdqg;
    }

    public void setGdqg(String gdqg) {
        this.gdqg = gdqg;
    }

    public String getYdmj() {
        return ydmj==null?"0":ydmj;
    }

    public void setYdmj(String ydmj) {
        this.ydmj = ydmj;
    }

    public String getYdzzmj() {
        return ydzzmj==null?"0":ydzzmj;
    }

    public void setYdzzmj(String ydzzmj) {
        this.ydzzmj = ydzzmj;
    }

    public String getYdlzmj() {
        return ydlzmj==null?"0":ydlzmj;
    }

    public void setYdlzmj(String ydlzmj) {
        this.ydlzmj = ydlzmj;
    }

    public String getYdqtmj() {
        return ydqtmj==null?"0":ydqtmj;
    }

    public void setYdqtmj(String ydqtmj) {
        this.ydqtmj = ydqtmj;
    }

    public String getYdjg() {
        return ydjg==null?"0":ydjg;
    }

    public void setYdjg(String ydjg) {
        this.ydjg = ydjg;
    }

    public String getYdqd() {
        return ydqd==null?"":ydqd;
    }

    public void setYdqd(String ydqd) {
        this.ydqd = ydqd;
    }

    public String getYdql() {
        return ydql==null?"":ydql;
    }

    public void setYdql(String ydql) {
        this.ydql = ydql;
    }

    public String getYdqg() {
        return ydqg==null?"":ydqg;
    }

    public void setYdqg(String ydqg) {
        this.ydqg = ydqg;
    }

    public String getLdmj() {
        return ldmj==null?"0":ldmj;
    }

    public void setLdmj(String ldmj) {
        this.ldmj = ldmj;
    }

    public String getLdwjy() {
        return ldwjy==null?"0":ldwjy;
    }

    public void setLdwjy(String ldwjy) {
        this.ldwjy = ldwjy;
    }

    public String getLdlxzyz() {
        return ldlxzyz==null?"0":ldlxzyz;
    }

    public void setLdlxzyz(String ldlxzyz) {
        this.ldlxzyz = ldlxzyz;
    }

    public String getLdjg() {
        return ldjg==null?"0":ldjg;
    }

    public void setLdjg(String ldjg) {
        this.ldjg = ldjg;
    }

    public String getLdqd() {
        return ldqd==null?"":ldqd;
    }

    public void setLdqd(String ldqd) {
        this.ldqd = ldqd;
    }

    public String getLdql() {
        return ldql==null?"":ldql;
    }

    public void setLdql(String ldql) {
        this.ldql = ldql;
    }

    public String getLdqg() {
        return ldqg==null?"":ldqg;
    }

    public void setLdqg(String ldqg) {
        this.ldqg = ldqg;
    }

    public String getNyjsmj() {
        return nyjsmj==null?"0":nyjsmj;
    }

    public void setNyjsmj(String nyjsmj) {
        this.nyjsmj = nyjsmj;
    }

    public String getNyjszzmj() {
        return nyjszzmj==null?"":nyjszzmj;
    }

    public void setNyjszzmj(String nyjszzmj) {
        this.nyjszzmj = nyjszzmj;
    }

    public String getNyjslzmj() {
        return nyjslzmj==null?"":nyjslzmj;
    }

    public void setNyjslzmj(String nyjslzmj) {
        this.nyjslzmj = nyjslzmj;
    }

    public String getNyjsqtmj() {
        return nyjsqtmj==null?"":nyjsqtmj;
    }

    public void setNyjsqtmj(String nyjsqtmj) {
        this.nyjsqtmj = nyjsqtmj;
    }

    public String getNyjsjg() {
        return nyjsjg==null?"--":nyjsjg;
    }

    public void setNyjsjg(String nyjsjg) {
        this.nyjsjg = nyjsjg;
    }

    public String getNyjsbz() {
        return nyjsbz==null?"0":nyjsbz;
    }

    public void setNyjsbz(String nyjsbz) {
        this.nyjsbz = nyjsbz;
    }

    public String getQtnydmj() {
        return qtnydmj==null?"0":qtnydmj;
    }

    public void setQtnydmj(String qtnydmj) {
        this.qtnydmj = qtnydmj;
    }

    public String getQtnydsyqk() {
        return qtnydsyqk==null?"--":qtnydsyqk;
    }

    public void setQtnydsyqk(String qtnydsyqk) {
        this.qtnydsyqk = qtnydsyqk;
    }

    public String getQtnydjg() {
        return qtnydjg==null?"--":qtnydjg;
    }

    public void setQtnydjg(String qtnydjg) {
        this.qtnydjg = qtnydjg;
    }

    public String getQtnydbz() {
        return qtnydbz==null?"0":qtnydbz;
    }

    public void setQtnydbz(String qtnydbz) {
        this.qtnydbz = qtnydbz;
    }

    public String getJsydmj() {
        return jsydmj==null?"0":jsydmj;
    }

    public void setJsydmj(String jsydmj) {
        this.jsydmj = jsydmj;
    }

    public String getJsydsyqk() {
        return jsydsyqk==null?"":jsydsyqk;
    }

    public void setJsydsyqk(String jsydsyqk) {
        this.jsydsyqk = jsydsyqk;
    }

    public String getJsydjg() {
        return jsydjg==null?"0":jsydjg;
    }

    public void setJsydjg(String jsydjg) {
        this.jsydjg = jsydjg;
    }

    public String getJsydpf() {
        return jsydpf==null?"0":jsydpf;
    }

    public void setJsydpf(String jsydpf) {
        this.jsydpf = jsydpf;
    }

    public String getJzydmj() {
        return jzydmj==null?"0":jzydmj;
    }

    public void setJzydmj(String jzydmj) {
        this.jzydmj = jzydmj;
    }

    public String getJzydzz() {
        return jzydzz==null?"0":jzydzz;
    }

    public void setJzydzz(String jzydzz) {
        this.jzydzz = jzydzz;
    }

    public String getJzydcz() {
        return jzydcz==null?"0":jzydcz;
    }

    public void setJzydcz(String jzydcz) {
        this.jzydcz = jzydcz;
    }

    public String getJzydqt() {
        return jzydqt==null?"0":jzydqt;
    }

    public void setJzydqt(String jzydqt) {
        this.jzydqt = jzydqt;
    }

    public String getJzydjg() {
        return jzydjg==null?"0":jzydjg;
    }

    public void setJzydjg(String jzydjg) {
        this.jzydjg = jzydjg;
    }

    public String getJzydms() {
        return jzydms==null?"0":jzydms;
    }

    public void setJzydms(String jzydms) {
        this.jzydms = jzydms;
    }

    public String getGgydmj() {
        return ggydmj==null?"0":ggydmj;
    }

    public void setGgydmj(String ggydmj) {
        this.ggydmj = ggydmj;
    }

    public String getGgydsyqk() {
        return ggydsyqk==null?"0":ggydsyqk;
    }

    public void setGgydsyqk(String ggydsyqk) {
        this.ggydsyqk = ggydsyqk;
    }

    public String getGgydbz() {
        return ggydbz==null?"0":ggydbz;
    }

    public void setGgydbz(String ggydbz) {
        this.ggydbz = ggydbz;
    }

    public String getGgydzz() {
        return ggydzz==null?"0":ggydzz;
    }

    public void setGgydzz(String ggydzz) {
        this.ggydzz = ggydzz;
    }

    public String getGgydcz() {
        return ggydcz==null?"0":ggydcz;
    }

    public void setGgydcz(String ggydcz) {
        this.ggydcz = ggydcz;
    }

    public String getGgydqt() {
        return ggydqt==null?"0":ggydqt;
    }

    public void setGgydqt(String ggydqt) {
        this.ggydqt = ggydqt;
    }

    public String getGgydlzjg() {
        return ggydlzjg==null?"0":ggydlzjg;
    }

    public void setGgydlzjg(String ggydlzjg) {
        this.ggydlzjg = ggydlzjg;
    }

    public String getJtydzz() {
        return jtydzz==null?"0":jtydzz;
    }

    public void setJtydzz(String jtydzz) {
        this.jtydzz = jtydzz;
    }

    public String getJtydcz() {
        return jtydcz==null?"0":jtydcz;
    }

    public void setJtydcz(String jtydcz) {
        this.jtydcz = jtydcz;
    }

    public String getJtydqt() {
        return jtydqt==null?"0":jtydqt;
    }

    public void setJtydqt(String jtydqt) {
        this.jtydqt = jtydqt;
    }

    public String getJtydmj() {
        return jtydmj==null?"0":jtydmj;
    }

    public void setJtydmj(String jtydmj) {
        this.jtydmj = jtydmj;
    }

    public String getJtydsyqk() {
        return jtydsyqk==null?"0":jtydsyqk;
    }

    public void setJtydsyqk(String jtydsyqk) {
        this.jtydsyqk = jtydsyqk;
    }

    public String getJtydjg() {
        return jtydjg==null?"0":jtydjg;
    }

    public void setJtydjg(String jtydjg) {
        this.jtydjg = jtydjg;
    }

    public String getJtydbz() {
        return jtydbz==null?"0":jtydbz;
    }

    public void setJtydbz(String jtydbz) {
        this.jtydbz = jtydbz;
    }

    public String getTdzmj() {
        return tdzmj==null?"0":tdzmj;
    }

    public void setTdzmj(String tdzmj) {
        this.tdzmj = tdzmj;
    }

    public String getYjjbnt() {
        return yjjbnt==null?"0":yjjbnt;
    }

    public void setYjjbnt(String yjjbnt) {
        this.yjjbnt = yjjbnt;
    }

    public String getPyzl() {
        return pyzl==null?"0":pyzl;
    }

    public void setPyzl(String pyzl) {
        this.pyzl = pyzl;
    }

    public String getJtjy() {
        return jtjy==null?"0":jtjy;
    }

    public void setJtjy(String jtjy) {
        this.jtjy = jtjy;
    }

    public String getJtjy1() {
        return jtjy1==null?"0":jtjy1;
    }

    public void setJtjy1(String jtjy1) {
        this.jtjy1 = jtjy1;
    }

    public String getDhcb() {
        return dhcb==null?"0":dhcb;
    }

    public void setDhcb(String dhcb) {
        this.dhcb = dhcb;
    }

    public String getDwzl() {
        return dwzl==null?"0":dwzl;
    }

    public void setDwzl(String dwzl) {
        this.dwzl = dwzl;
    }

    public String getQtmj() {
        return qtmj==null?"0":qtmj;
    }

    public void setQtmj(String qtmj) {
        this.qtmj = qtmj;
    }

    public String getZjmj() {
        return zjmj==null?"":zjmj;
    }

    public void setZjmj(String zjmj) {
        this.zjmj = zjmj;
    }
/*
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
    }*/

    public String getYears() {
        return years==null?"":years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<BcrejectedEntity> getBcrejectedEntities() {
        if (bcrejectedEntities==null){
            bcrejectedEntities = new ArrayList<>();
        }
        return bcrejectedEntities;
    }

    public void setBcrejectedEntities(List<BcrejectedEntity> bcrejectedEntities) {
        this.bcrejectedEntities = bcrejectedEntities;
    }
}
