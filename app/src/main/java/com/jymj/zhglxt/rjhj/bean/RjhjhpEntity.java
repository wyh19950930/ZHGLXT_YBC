package com.jymj.zhglxt.rjhj.bean;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2020/10/27.
 */
public class RjhjhpEntity {
    private Integer gid;
    private String code;
    private String xzqmc;
    private String type;
    private String name;
    private BigDecimal area;//亩
    private String geom;
    private String tc;
    private String zhen;
    private String zhencode;
    private String bjry;	//	保洁人员
    private String bjdz;//保洁队长
    private String bjlx;//保洁联系
    private String zrbm;//责任部门
    private String jgzt;//监管主体
    private String jglx;//监管联系

    public String getBjdz() {
        return bjdz==null?"":bjdz;
    }

    public void setBjdz(String bjdz) {
        this.bjdz = bjdz;
    }

    public String getBjlx() {
        return bjlx==null?"":bjlx;
    }

    public void setBjlx(String bjlx) {
        this.bjlx = bjlx;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTc() {
        return tc==null?"":tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getZhen() {
        return zhen==null?"":zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getZhencode() {
        return zhencode==null?"":zhencode;
    }

    public void setZhencode(String zhencode) {
        this.zhencode = zhencode;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getType() {
        return type==null?"":type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area==null?BigDecimal.ZERO:area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getGeom() {
        return geom==null?"":geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getBjry() {
        return bjry==null?"":bjry;
    }

    public void setBjry(String bjry) {
        this.bjry = bjry;
    }

    public String getZrbm() {
        return zrbm==null?"":zrbm;
    }

    public void setZrbm(String zrbm) {
        this.zrbm = zrbm;
    }

    public String getJgzt() {
        return jgzt==null?"":jgzt;
    }

    public void setJgzt(String jgzt) {
        this.jgzt = jgzt;
    }

    public String getJglx() {
        return jglx==null?"":jglx;
    }

    public void setJglx(String jglx) {
        this.jglx = jglx;
    }
}
