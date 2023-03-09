package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.rjhj.bean.yl.enums.BuStracEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.BuildageEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.FwytEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.IsillegalEnum;

import java.io.Serializable;

/**
 * Created by ljj on 2017/3/16.
 */
public class FWEntity implements Serializable {
    private int gid;
    private double zdmj;
    //建筑层数
    private int jzcs;
    private double jzmj;
    private String remark;
    private String xzqmc;
    //建筑物结构（钢结构，砖混，木制），
    private Integer jzwjg;
    private String jzwjgText;
    //建成年代
    private Integer jcnd;
    private String jcndText;
    //房屋用途
    private Integer fwyt;
    private String fwytText;
    //是否违建，
    private Integer isillegal;
    private String isillegalText;
    private String center;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public double getZdmj() {
        return zdmj;
    }

    public void setZdmj(double zdmj) {
        this.zdmj = zdmj;
    }

    public int getJzcs() {
        return jzcs;
    }

    public void setJzcs(int jzcs) {
        this.jzcs = jzcs;
    }

    public double getJzmj() {
        return jzmj;
    }

    public void setJzmj(double jzmj) {
        this.jzmj = jzmj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer getJzwjg() {
        return jzwjg;
    }

    public void setJzwjg(int jzwjg) {
        this.jzwjg = jzwjg;
    }

    public Integer getIsillegal() {
        return isillegal;
    }

    public void setIsillegal(int isillegal) {
        this.isillegal = isillegal;
    }

    public Integer getJcnd() {
        return jcnd;
    }

    public void setJcnd(int jcnd) {
        this.jcnd = jcnd;
    }

    public Integer getFwyt() {
        return fwyt;
    }

    public void setFwyt(int fwyt) {
        this.fwyt = fwyt;
    }

    public String getJzwjgText() {
        if (getJzwjg()==null)
            return "";
        return BuStracEnum.getName(getJzwjg());
    }

    public String getIsillegalText() {
        if (getIsillegal()==null)
            return null;
        return IsillegalEnum.getName(getIsillegal());
    }

    public String getJcndText() {
        if (getJcnd()==null)
            return "";
        return BuildageEnum.getName(getJcnd());
    }
    public String getFwytText() {
        if (getFwyt()==null)
            return "";
        return FwytEnum.getName(getFwyt());
    }


}
