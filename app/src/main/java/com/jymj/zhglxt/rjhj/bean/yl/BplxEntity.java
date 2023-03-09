package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ${lc} on 2020/10/27.
 */
public class BplxEntity {
    private Integer id;
    private String xzqmc;
    private String code;
    private String bpld;	//	包片领导
    private String bplx;	//	包片联系
    private String bcgb;	//	包村干部
    private String bclx;	//	包村联系
    private String hjzgy;	//	环境专管员
    private String zglx;	//	专管联系
    private String xcry;	//	巡查人员
    private String xclx;	//	巡查联系
    private String department;	//	包村干部所在科室

    private String name;
    private Integer counts;

    public String getDepartment() {
        return department==null?"":department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getBpld() {
        return bpld==null?"":bpld;
    }

    public void setBpld(String bpld) {
        this.bpld = bpld;
    }

    public String getBplx() {
        return bplx==null?"":bplx;
    }

    public void setBplx(String bplx) {
        this.bplx = bplx;
    }

    public String getBcgb() {
        return bcgb==null?"":bcgb;
    }

    public void setBcgb(String bcgb) {
        this.bcgb = bcgb;
    }

    public String getBclx() {
        return bclx==null?"":bclx;
    }

    public void setBclx(String bclx) {
        this.bclx = bclx;
    }

    public String getHjzgy() {
        return hjzgy==null?"":hjzgy;
    }

    public void setHjzgy(String hjzgy) {
        this.hjzgy = hjzgy;
    }

    public String getZglx() {
        return zglx==null?"":zglx;
    }

    public void setZglx(String zglx) {
        this.zglx = zglx;
    }

    public String getXcry() {
        return xcry==null?"":xcry;
    }

    public void setXcry(String xcry) {
        this.xcry = xcry;
    }

    public String getXclx() {
        return xclx==null?"":xclx;
    }

    public void setXclx(String xclx) {
        this.xclx = xclx;
    }
}
